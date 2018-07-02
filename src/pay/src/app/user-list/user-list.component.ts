import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { Router, NavigationEnd } from '@angular/router';
import { NzMessageService,NzModalService} from 'ng-zorro-antd';
import {FormBuilder,FormGroup,Validators,FormControl} from '@angular/forms';
import {Md5} from "ts-md5/dist/md5";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  data = [];
  endpoint:string = 'login/getUserList?';
  params:string= '';
  admin:boolean;
  currentModal;
  validateForm: FormGroup;
  isConfirmLoading = false;

  constructor(private appService:AppService,private router:Router,private msg: NzMessageService,private modalService: NzModalService,private fb: FormBuilder) { }

  addSchool() {
    this.router.navigate(['/home/Manager']);
  }

   del(data) {
     let params={
       phone : data.phone
     }
     this.appService.get('login/deleteUser?',params).subscribe(data =>{
       if(data.json().suc){
         this.msg.success(data.json().msg);
         this.getUserList();
       }else{
         this.msg.error(data.json().msg);
       }
     })
   }

   getUserList():void {
     this.appService.get(this.endpoint,this.params).subscribe(data =>{
       if(data.json().suc){
         if(data.json().data != null){
           let datas = data.json().data;
             datas.forEach((item:any) =>{
               if(item.role=='user'){
                 item.role='学校账户'
               }
               if(item.role=='admin'){
                   item.role='管理员'
               }
               if(item.role=='agentUser'){
                   item.role='代理商'
               }
             })
            this.data = datas
         }else {
           this.data = [];
         }

       }else{
         this.msg.error(data.json().msg);
       }
     })
  }

  //新增代理商账户
  addAgent(titleTpl, contentTpl, footerTpl) {
   this.currentModal = this.modalService.open({
     title       : titleTpl,
     content     : contentTpl,
     footer      : footerTpl,
     maskClosable: false,
     onOk() {
       console.log('Click ok');
     }
   });
   // 初始化表单内容；
   this.validateForm = this.fb.group({
    phone             : [ null, [ Validators.required,this.phoneLenthValidator ] ],
    password          : [ null, [ Validators.required ] ],
    checkPassword     : [ null, [ Validators.required, this.confirmationValidator ] ],
    agentName         : [ null, [ Validators.required ] ]
  });
 }

 // 关闭modal submit
 handleOk(e) {
   for (const i in this.validateForm.controls) {
    this.validateForm.controls[ i ].markAsDirty();
  }

  if(this.validateForm.valid){
    this.isConfirmLoading = true;
    let phone = this.validateForm.value.phone
    let password = Md5.hashStr(this.validateForm.value.password)
    let agentName = this.validateForm.value.agentName
    this.addChildAccount (phone,password,agentName)
  }
 }


 updateConfirmValidator() {
  /** wait for refresh value */
  setTimeout(_ => {
    this.validateForm.controls[ 'checkPassword' ].updateValueAndValidity();
  });
 }
 //

 confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
  if (!control.value) {
    return { required: true };
  } else if (control.value !== this.validateForm.controls[ 'password' ].value) {
    return { confirm: true, error: true };
  }
 };

 phoneLenthValidator = (control: FormControl): { [s: string]: boolean } => {
  if (!control.value) {
    return { required: true };
  } else if (control.value.length>36) {
    return { confirm: true, error: true };
  }else if(/[\u4E00-\u9FA5]/g.test(control.value)){
    return{limitChinese:true,error:true};
  }
 };

 getFormControl(name) {
  return this.validateForm.controls[ name ];
 }

 addChildAccount (phone,password,agentName) {
   this.appService.get('addAgentUser',{
     phone:phone,
     password:password,
     agentName:agentName
   }).subscribe(data => {
     this.isConfirmLoading = false;
      if(data.json().suc){
       this.msg.success(data.json().msg);
       this.currentModal.destroy('onOk');
       this.isConfirmLoading = false;
       this.currentModal = null;
       this.getUserList();
     }else{
       this.msg.error(data.json().msg);
     }
  });
 }


  ngOnInit() {
    this.getUserList();
    let role = sessionStorage.getItem('role');
    if(role=='admin'){
      this.admin = true;
    }else {
      this.admin = false;
    }


  }

}

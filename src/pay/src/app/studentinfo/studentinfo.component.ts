import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {AppService} from '../app.service';
import { NzModalService,NzMessageService} from 'ng-zorro-antd';
import {FormBuilder,FormGroup,Validators,FormControl} from '@angular/forms';
import {Md5} from "ts-md5/dist/md5";

@Component({
  selector: 'app-studentinfo',
  templateUrl: './studentinfo.component.html',
  styleUrls: ['./studentinfo.component.scss']
})
export class StudentinfoComponent implements OnInit {
  data = [];
  endpoint:string = 'login/getUserList?';
  params:string= '';

  currentModal;
  validateForm: FormGroup;
  isConfirmLoading = false;
  phone:string;
constructor(private router:Router,private appService:AppService,private modalService: NzModalService,private fb: FormBuilder,private _message: NzMessageService) { }

  // checkDetail(data) {
  //     console.log(data)
  //      this.router.navigate(['/home/fixStudentInfo'],{queryParams:{'data':data}});
  //   }
 //获取子账户
 getUserList():void {
   this.appService.get(this.endpoint,this.params).subscribe(data =>{
     if(data.json().suc){
       if(data.json().data != null){
         this.data = data.json().data;
       }else {
         this.data = [];
       }

     }else{
       this._message.error(data.json().msg);
     }
   })
}

// 删除子账户
del(data) {
  let params={
    phone : data.phone
  }
  this.appService.get('login/deleteUser?',params).subscribe(data =>{
    if(data.json().suc){
      this._message.success(data.json().msg);
      this.getUserList();
    }else{
      this._message.error(data.json().msg);
    }
  })
}


   //新增子账户
   showModalForTemplate(titleTpl, contentTpl, footerTpl) {
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
     checkPassword     : [ null, [ Validators.required, this.confirmationValidator ] ]
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
     let surePassword = Md5.hashStr(this.validateForm.value.checkPassword)
     this.addChildAccount (phone,password,surePassword)
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

addChildAccount (phone,password,surePassword) {
    this.appService.get('addChildAccount',{
      phone:phone,
      password:password,
      surePassword:surePassword,
      parentAccount:this.phone
    }).subscribe(data => {
      this.isConfirmLoading = false;
       if(data.json().suc){
        this._message.success(data.json().msg);
        this.currentModal.destroy('onOk');
        this.isConfirmLoading = false;
        this.currentModal = null;
        this.getUserList();
      }else{
        this._message.error(data.json().msg);
      }
   });
}

  ngOnInit() {
    this.getUserList();
    this.phone = sessionStorage.getItem('phone');
  }

}

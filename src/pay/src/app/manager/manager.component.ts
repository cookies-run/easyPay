import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { NzMessageService,NzModalService} from 'ng-zorro-antd';
import {Md5} from "ts-md5/dist/md5";
import { Router, NavigationEnd } from '@angular/router';


import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from '@angular/forms';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.scss']
})
export class ManagerComponent implements OnInit {
  validateForm: FormGroup;
  endpoint='login/register?';
  params:any = {
    phone:'',
    password:'',
    parentAccount:'',
    schoolName:''
  };

  _submitForm() {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
    }
    // 要上传的值;
    console.log(this.validateForm.value);
    this.params.phone = this.validateForm.value.phoneNumber;
    this.params.password=Md5.hashStr(this.validateForm.value.password);
    this.params.schoolName=this.validateForm.value.schoolName

    this.register(this.endpoint,this.params);
  }

  constructor(private fb: FormBuilder,private appService:AppService,private msg: NzMessageService,private router:Router,private confirmServ: NzModalService) {
  }

  register(endpoint,params):void {
    this.appService.get(endpoint,params).subscribe(data =>{
      if(data.json().suc){
        this.msg.success('注册成功');
        this.showConfirm ();
      }else{

      }
    })
  }

  restdata() {
    this.validateForm = this.fb.group({
      password         : [ null, [ Validators.required ] ],
      checkPassword    : [ null, [ Validators.required, this.confirmationValidator ] ],
      phoneNumber      : [ null, [ Validators.required ] ],
      schoolName       : [ null, [ Validators.required ] ]
    });
  }

  goback() {
    this.router.navigate(['/home/UserList']);
  }

  phoneLenthValidator = (control: FormControl): { [s: string]: boolean } => {
   if (!control.value) {
     return { required: true };
   } else if (control.value.length>36) {
     return { confirm: true, error: true };
   }else if(/[\u4E00-\u9FA5]/g.test(control.value)){
     return{limitChinese:true,error:true};
   }
  };

  showConfirm = () => {
    let hh = this;
    this.confirmServ.confirm({
      title  : '您是否继续添加用户',
      content: '点取消返回用户列表',
      showConfirmLoading: true,
      onOk() {
        hh.restdata();

      },
      onCancel() {
        hh.goback();
      }
    });
  };


  updateConfirmValidator() {
    /** wait for refresh value */
    setTimeout(_ => {
      this.validateForm.controls[ 'checkPassword' ].updateValueAndValidity();
    });
  }

  confirmationValidator = (control: FormControl): { [s: string]: boolean } => {
    if (!control.value) {
      return { required: true };
    } else if (control.value !== this.validateForm.controls[ 'password' ].value) {
      return { confirm: true, error: true };
    }
  };


  ngOnInit() {
    let role = sessionStorage.getItem('role');
    if(role && role =='agentUser'){
        this.params.parentAccount = sessionStorage.getItem('phone');
    }else {
       this.params.parentAccount = 'null'
    }


    this.validateForm = this.fb.group({
      password         : [ null, [ Validators.required ] ],
      checkPassword    : [ null, [ Validators.required, this.confirmationValidator ] ],
      phoneNumber      : [ null, [ Validators.required,this.phoneLenthValidator] ],
      schoolName       : [ null, [ Validators.required ] ]
    });

  }

  getFormControl(name) {
    return this.validateForm.controls[ name ];
  }
}

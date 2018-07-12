import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd,ActivatedRoute} from '@angular/router';
import {AppService} from '../app.service';
import { NzModalService,NzMessageService} from 'ng-zorro-antd';
import {FormBuilder,FormGroup,Validators,FormControl} from '@angular/forms';
import {Md5} from "ts-md5/dist/md5";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {
  public params:any = {}
  public endpoint:string = 'login/logout?';
  currentModal;
  isConfirmLoading = false;
  admin:boolean;
  phone:string;
  isCollapsed:boolean = false;
   validateForm: FormGroup;
   childUser:boolean;
   constructor(private router:Router,private appService:AppService,private modalService: NzModalService,private fb: FormBuilder,private _message: NzMessageService){

   }


   goPage(n:string) {
     this.router.navigate([`/home/${n}`]);
   }

  logout() {
    sessionStorage.clear();
    //后台退出
    this.logOut(this.endpoint,this.params);
    this.router.navigate([`/login`]);
  }

  logOut(endpoint,params): void {
    this.appService.get(endpoint,params).subscribe(data => {

    });
  }

  // 修改密码
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
  }

   // 关闭modal submit
   handleOk(e) {
     for (const i in this.validateForm.controls) {
      this.validateForm.controls[ i ].markAsDirty();
    }

    if(this.validateForm.valid){
      this.isConfirmLoading = true;
      let password = Md5.hashStr(this.validateForm.value.oldPassword)
      let newPassword = Md5.hashStr(this.validateForm.value.password)
      let surePassword = Md5.hashStr(this.validateForm.value.checkPassword)
      this.changePassword (password,newPassword,surePassword)
    }
  }


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

  getCaptcha(e: MouseEvent) {
    e.preventDefault();
  }

  getFormControl(name) {
    return this.validateForm.controls[ name ];
  }

 changePassword (password,newPassword,surePassword) {
     this.appService.get('editUserPwd',{
       phone:this.phone,
       password:password,
       newPassword:newPassword,
       surePassword:surePassword
     }).subscribe(data => {
       this.isConfirmLoading = false;
        if(data.json().suc){
         this._message.success(data.json().msg);
         this.currentModal.destroy('onOk');
         this.isConfirmLoading = false;
         this.currentModal = null;
       }else{
         this._message.error(data.json().msg);
       }
    });
 }

  ngOnInit() {
    let role = sessionStorage.getItem('role');
    this.phone = sessionStorage.getItem('phone');
    if(role=='admin' || role =='agentUser'){
      this.admin = true;
    }else {
      this.admin = false;
    }

    if(role){
      if(role=='childUser'){
        this.childUser = true;
        this.router.navigate(['/home/PengdingBills']);
      }else {
        this.childUser = false;
          this.router.navigate(['/home/checkPower']);
      }
    }else {
      this.childUser = false;
    }


     this.validateForm = this.fb.group({
      oldPassword       : [ null, [ Validators.required ] ],
      password          : [ null, [ Validators.required ] ],
      checkPassword     : [ null, [ Validators.required, this.confirmationValidator ] ]
    });
  }

}

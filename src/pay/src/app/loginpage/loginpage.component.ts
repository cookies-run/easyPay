import { Component,OnInit,ViewChild,Input } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {Md5} from "ts-md5/dist/md5";
import {AppService} from '../app.service';
import {slideInOutAnimation} from '../app-routingAnimation'
import {FormBuilder,FormGroup,Validators} from '@angular/forms';

@Component({
  selector: 'loginpage',
  templateUrl:'./loginpage.component.html',
  styleUrls:['./loginpage.component.scss'],
  animations:[slideInOutAnimation],
  host: { '[@slideInOutAnimation]': '' }
})
export class LoginComponent {
    public endpoint:string = 'login/loginIn?';
    public hintContent:string = '登录';
    validateForm: FormGroup;

    constructor(private router: Router,private appService:AppService,private fb: FormBuilder) {}

    Login () {
        for (const i in this.validateForm.controls) {
          this.validateForm.controls[ i ].markAsDirty();
        }
        if(this.validateForm.valid){
          this.validateForm.value.password=Md5.hashStr(this.validateForm.value.password);
          this.hintContent = '正在登录'
          this.loginService(this.endpoint,this.validateForm.value);
        }
    }
     loginService(endpoint,params): void {
        this.appService.get(endpoint,params).subscribe(data => {
          var res = data.json().data;
          if(data.json().suc){
            if(res.role){
              sessionStorage.setItem('role',res.role);
            }
            if(res.phone!=null){
              sessionStorage.setItem('phone', res.phone);
            }
            if(res.appauthtoken!=null){
              sessionStorage.setItem('appauthtoken', res.appauthtoken);
            }
            if(res.schoolNo!=null){
              sessionStorage.setItem('schoolNo', res.schoolNo);
            }
            if(res.phone !='admin'){
              this.router.navigate(['/home']);
            }else {
              this.router.navigate(['/home/UserList']);
            }

          }else{
            this.hintContent = '登录失败,请重新尝试'
          }
          });
     }

   ngOnInit(): void {
      sessionStorage.clear();
      this.validateForm = this.fb.group({
          phone: [ null, [ Validators.required ] ],
          password: [ null, [ Validators.required ] ]
      });
     }

}

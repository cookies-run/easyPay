import { Component,OnInit,ViewChild,Input } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {Md5} from "ts-md5/dist/md5";
import {AppService} from '../app.service';
import {slideInOutAnimation} from '../app-routingAnimation'

@Component({
  selector: 'loginpage',
  templateUrl:'./loginpage.component.html',
  styleUrls:['./loginpage.component.scss'],
  animations:[slideInOutAnimation],
  host: { '[@slideInOutAnimation]': '' }
})
export class LoginComponent {
    public params:any = {
        phone :'',
        password:''
    }
    public endpoint:string = 'login/loginIn?';
    public hintContent:string = '';


    constructor(private router: Router,private appService:AppService) {}
    Login () {
       if(this.params.phone ==''){
          this.hintContent ='登录名不能为空';
         return;
       }
       if(this.params.password ==''){
         this.hintContent ='password is invalid';
         return;
       }
       this.params.password=Md5.hashStr(this.params.password);
          this.loginService(this.endpoint,this.params);
         // sessionStorage.setItem('phone', 'this.params.phone');
         // this.router.navigate(['/home/checkPower']);
    }

     loginService(endpoint,params): void {
        this.appService.get(endpoint,params).subscribe(data => {
          var res = data.json().data;
          if(data.json().suc){
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
              this.router.navigate(['/home/checkPower']);
            }else {
              this.router.navigate(['/home/UserList']);
            }

          }else{
            this.hintContent = data.json().msg;
          }
          });
     }

   ngOnInit(): void {
      sessionStorage.clear();
     }

}

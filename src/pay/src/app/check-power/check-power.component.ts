import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { QRCodeModule } from 'angular2-qrcode';
import {NzMessageService} from 'ng-zorro-antd';
import { Router, NavigationEnd } from '@angular/router';


@Component({
  selector: 'app-check-power',
  templateUrl: './check-power.component.html',
  styleUrls: ['./check-power.component.scss']
})
export class CheckPowerComponent implements OnInit {

   current = 0;
   index = 'First-content';
   token:boolean=false;
   schoolNo:boolean = false;
  public params:any = {
    phone :''
  }

  public ali_url:string = "";
  private phone:string = "";
  public endpoint:string = '/authorize/lunchAuthorize?';

  constructor(private appService:AppService,private _message: NzMessageService,private router:Router) {
    this.params.phone = this.phone;
  }

  lunchAuthorize(): void {
    this.appService.get(this.endpoint,this.params).subscribe(data => {
      let res = JSON.parse(data["_body"]);
      this.ali_url = res.url;
    });
  }

  go():void {
     this.router.navigate(['/home/SchoolInfo']);
  }


 pre() {
   this.current -= 1;
   this.changeContent();
 }

 next() {
   this.current += 1;
   this.changeContent();
 }

 done() {
   this._message.success('流程结束！');
 }

 changeContent() {
   this.token = sessionStorage.getItem('appauthtoken')?false:true
   this.schoolNo = sessionStorage.getItem('schoolNo')?false:true
 }

 power() {
    window.location.href= this.ali_url ;
 }

  ngOnInit() {
    this.lunchAuthorize();
    if(sessionStorage.getItem('appauthtoken')){
      this.token = false;
      this.current = 2;
      if(sessionStorage.getItem('schoolNo')){
        this.schoolNo = false;
        this.current = 3;
      }else {
          this.schoolNo = true;
          this.current = 2;
      }
    }else {
        this.token = true;
        this.current = 0;
    }

  }

}

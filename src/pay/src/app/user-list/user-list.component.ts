import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { Router, NavigationEnd } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  data = [];
  endpoint:string = 'login/getUserList?';
  params:string= '';
  constructor(private appService:AppService,private router:Router,private msg: NzMessageService) { }

  add() {
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
           this.data = data.json().data;
         }else {
           this.data = [];
         }

       }else{
         this.msg.error(data.json().msg);
       }
     })
  }

  ngOnInit() {
    this.getUserList();
  }

}

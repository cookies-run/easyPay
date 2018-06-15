import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { Router, NavigationEnd } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-pengding-bills',
  templateUrl: './pengding-bills.component.html',
  styleUrls: ['./pengding-bills.component.scss']
})
export class PengdingBillsComponent implements OnInit {
  bills = [];
  public params:any = {
    phone :''
  }
  public endpoint:string = '';

  constructor(private appService:AppService,private router:Router,private msg: NzMessageService) { }

  getUnSendBill(endpoint,params):void {
    this.appService.get(endpoint,params).subscribe(data =>{
      if(data.json().suc){
        this.bills = data.json().data;
      }else{
        this.msg.error(data.json().msg);
      }
    })
  }

  SendBill(endpoint,params):void {
    this.appService.get(endpoint,params).subscribe(data =>{
      if(data.json().suc){
        this.msg.success(data.json().msg);
      }else{
        this.msg.error(data.json().msg);
      }
    })
  }

  checkDetail(data) {
     this.router.navigate(['/home/BillsDetail'],{queryParams:{'billsinfo':data.id}});
  }
  // 发送账单
  submit(data) {
    this.endpoint = 'send/sendBill?';
    this.params.id = data.id;
    this.SendBill(this.endpoint,this.params);

  }

  ngOnInit() {
    this.params.phone = sessionStorage.getItem('phone');
    this.endpoint = 'bill/getListBill?';
    this.getUnSendBill(this.endpoint, this.params);
  }

}

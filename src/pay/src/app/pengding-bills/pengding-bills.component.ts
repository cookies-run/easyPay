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
  role:string;
  ChildAccount:boolean;
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


 cancel = function () {
    this.msg.info('取消删除账单')
  };

  // 删除
  confirm = (data) => {
      this.appService.get('/billDelete',{
        billId:data.id
      }).subscribe(res =>{
      if(res.json().suc){
        this.msg.success(res.json().msg);
        this.getUnSendBill(this.endpoint, this.params)
      }else{
        this.msg.error(res.json().msg);
      }
    })
  };

  ngOnInit() {
   let role = sessionStorage.getItem('role');
   this.params.phone = sessionStorage.getItem('phone');
    this.endpoint = 'bill/getListBill?';
    if(role=='user'){
      this.ChildAccount = false;
    }
    if(role == 'childUser'){
      this.ChildAccount = true;
    }
    this.getUnSendBill(this.endpoint, this.params);
  }

}

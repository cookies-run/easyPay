import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { Router, NavigationEnd,ActivatedRoute} from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'app-bills-detail',
  templateUrl: './bills-detail.component.html',
  styleUrls: ['./bills-detail.component.scss']
})
export class BillsDetailComponent implements OnInit {
  details = [];
   params:any = {
    id :'',
    pageNo:'',
    pageSize:''
  }
  _current :number =1;
  _pageSize:number =10;

  _total:number;
  _loading = false;


  public endpoint:string = 'bill/getStudentBill?';
  constructor(private appService:AppService,private router:Router,private activatedRoute: ActivatedRoute,private msg: NzMessageService) {}

  ngOnInit() {
    // 上个页面传过来的id
    this.activatedRoute.queryParams.subscribe(queryParams => {
      this.params.id = queryParams['billsinfo']
    });
    //首次获取数据
    this.params.pageNo = this._current
    this.params.pageSize = this._pageSize
    //查询列表
    this._loading = true;
    this.getBillDetail(this.endpoint,this.params);
  }

  reload () {
    this.getBillDetail(this.endpoint,this.params);
  }

 download () {
       let downloadParams = {
          billId : this.params.id
       }
      this.appService.get('billExport',downloadParams).subscribe(data =>{
              if(data.json().suc){
                let name = data.json().data.fileName;
                let path = data.json().data.filePath;
                window.location.href = `/downLoadSchoolBill?&fileName=${name}&filePath=${path}`
              }else {
                  this.msg.error(data.json().msg);
              }
      })
 }

  getBillDetail(endpoint,params):void {
    this.appService.get(endpoint,params).subscribe(data =>{
      this._loading = false;
      if(data.json().suc){
          let datas = data.json().data.list;
          this._total = data.json().data.total;
        for(let i =0;i<datas.length;i++){
           if(datas[i].tradeStatus =='NOT_PAY'){ //处理数据
             datas[i].tradeStatus = '待缴费'
           }
           if(datas[i].tradeStatus =='PAYING'){
              datas[i].tradeStatus = '支付中'
           }
           if(datas[i].tradeStatus =='PAY_SUCCESS'){
              datas[i].tradeStatus = '支付成功，待处理'
           }
           if(datas[i].tradeStatus =='BILLING_SUCCESS'){
              datas[i].tradeStatus = '缴费成功'
           }
           if(datas[i].tradeStatus =='TIMEOUT_CLOSED'){
              datas[i].tradeStatus = '逾期关闭账单'
           }
           if(datas[i].tradeStatus =='ISV_CLOSED'){
              datas[i].tradeStatus = '账单关闭'
           }
          if(datas[i].tradeStatus =='UNKNOWN'){
            datas[i].tradeStatus = '暂未查到'
          }
        }
        this.details = datas
      }else{

      }
    })
  }
  goback() {
    this.router.navigate(['/home/PengdingBills']);
  }
 // 切换当前页面的数据量
   PageSizeResetData(size) {
     this._loading = true;
     this.params.pageNo = this._current;
     this.params.pageSize = size;
     this.getBillDetail(this.endpoint,this.params)
   }
   //切换页面
 PageIndexResetData(page) {
   this._loading = true;
   this.params.pageNo = page;
   this.params.pageSize = this._pageSize;
   this.getBillDetail(this.endpoint,this.params)
 }
}

import { Component, OnInit } from '@angular/core';
import {AppService} from '../app.service';
import { Router, NavigationEnd,ActivatedRoute} from '@angular/router';


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
  constructor(private appService:AppService,private router:Router,private activatedRoute: ActivatedRoute) {}

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
    window.location.href= `billExport?&billId=${this.params.id}`
 }

  getBillDetail(endpoint,params):void {
    this.appService.get(endpoint,params).subscribe(data =>{
      this._loading = false;
      if(data.json().suc){
          let datas = data.json().data.list;
          this._total = data.json().data.total;
        for(let i =0;i<datas.length;i++){
           if(datas[i].orderStatus =='NOT_PAY'){ //处理数据
             datas[i].status = '待缴费'
           }
           if(datas[i].orderStatus =='PAYING'){
              datas[i].status = '支付中'
           }
           if(datas[i].orderStatus =='PAY_SUCCESS'){
              datas[i].status = '支付成功，待处理'
           }
           if(datas[i].orderStatus =='BILLING_SUCCESS'){
              datas[i].status = '缴费成功'
           }
           if(datas[i].orderStatus =='TIMEOUT_CLOSED'){
              datas[i].status = '逾期关闭账单'
           }
           if(datas[i].orderStatus =='ISV_CLOSED'){
              datas[i].status = '账单关闭'
           }
          if(datas[i].orderStatus =='UNKNOWN'){
            datas[i].status = '暂未查到'
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

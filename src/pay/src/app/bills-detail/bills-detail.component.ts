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
    id :''
  }
  public endpoint:string = 'bill/getStudentBill?';
  constructor(private appService:AppService,private router:Router,private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    // 上个页面传过来的id
    this.activatedRoute.queryParams.subscribe(queryParams => {
      this.params.id = queryParams['billsinfo']
    });

    //查询列表
    this.getBillDetail(this.endpoint,this.params);
  }

  getBillDetail(endpoint,params):void {
    this.appService.get(endpoint,params).subscribe(data =>{
      if(data.json().suc){
        this.details = data.json().data;
      }else{

      }
    })
  }
  goback() {
    this.router.navigate(['/home/PengdingBills']);
  }
}

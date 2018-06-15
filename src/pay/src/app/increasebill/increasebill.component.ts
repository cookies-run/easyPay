import { Component, OnInit } from '@angular/core';
import { filter } from 'rxjs/operators/filter';
import { HttpRequest, HttpClient, HttpResponse } from '@angular/common/http';
import { NzMessageService } from 'ng-zorro-antd';
import { UploadFile } from 'ng-zorro-antd';
import {AppService} from '../app.service';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-increasebill',
  templateUrl: './increasebill.component.html',
  styleUrls: ['./increasebill.component.scss']
})
export class IncreasebillComponent implements OnInit {

    public endpoint:string = 'bill/getListBill?';

    uploading = false;
    fileList: UploadFile[] = [];
    data = this.fileList;
    constructor(private http: HttpClient, private msg: NzMessageService,private appService:AppService,private router:Router) {}

    beforeUpload = (file: UploadFile): boolean => {
      this.fileList.push(file);
      return false;
    }

    handleUpload() {
      const formData = new FormData();
      this.fileList.forEach((file: any) => {
        formData.append('file', file);
      });
      this.uploading = true;
      // You can use any AJAX library you like
      const req = new HttpRequest('POST', '/import/importBill', formData, {
        // reportProgress: true
      });
      this.http.request(req).pipe(filter(e => e instanceof HttpResponse)).subscribe((event: any) => {
        if(event.body.suc){
          this.uploading = false;
          this.msg.success(event.body.msg);
        }else{
          this.uploading = false;
          this.msg.error(event.body.msg);
        }
      }, (err) => {
        this.uploading = false;
        this.msg.error('上传失败');
      });
    }

    delet(data) {
       for(let i=0;i<this.fileList.length;i++){
         if(data == this.fileList[i]){
           this.fileList.splice(i,1);
         }
       }
    }


    download() {
         window.location.href= 'http://47.98.236.3/账单.xlsx' ;
    }

    // edit() {
    //    this.msg.success('填写自定义账单');
    // }


    ngOnInit() {

    }
}

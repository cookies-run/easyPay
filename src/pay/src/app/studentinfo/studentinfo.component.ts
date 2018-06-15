import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-studentinfo',
  templateUrl: './studentinfo.component.html',
  styleUrls: ['./studentinfo.component.scss']
})
export class StudentinfoComponent implements OnInit {
  data = [
   {
     key    : '1',
     name   : 'John Brown',
     age    : 32,
     address: 'New York No. 1 Lake Park',
   }, {
     key    : '2',
     name   : 'Jim Green',
     age    : 42,
     address: 'London No. 1 Lake Park',
   }, {
     key    : '3',
     name   : 'Joe Black',
     age    : 32,
     address: 'Sidney No. 1 Lake Park',
   }
 ];

  constructor(private router: Router) { }

    checkDetail(data) {
      console.log(data)
       this.router.navigate(['/home/fixStudentInfo'],{queryParams:{'data':data}});
    }



  ngOnInit() {
  }

}

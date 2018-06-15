import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd,ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-fix-student-info',
  templateUrl: './fix-student-info.component.html',
  styleUrls: ['./fix-student-info.component.scss']
})
export class FixStudentInfoComponent implements OnInit {
    editRow = null;
    tempEditObject = {};
    data = [
      {
        key    : 0,
        name   : 'Edward King 0',
        sex    : '男',
        studentNum    : 32,
        address: 'London, Park Lane no. 0',
      }
    ];
    constructor(private router: Router,private activatedRoute: ActivatedRoute) { }

    edit(data) {
      this.tempEditObject[ data.key ] = { ...data };
      this.editRow = data.key;
    }

    save(data) {
      Object.assign(data, this.tempEditObject[ data.key ]);
      this.editRow = null;
    }

    cancel(data) {
      this.tempEditObject[ data.key ] = {};
      this.editRow = null;
    }

    ngOnInit() {
      this.data.forEach(item => {
        this.tempEditObject[ item.key ] = {};
      })
      this.activatedRoute.queryParams.subscribe(queryParams => {
        console.log(queryParams.studentInfo)
     });
    }

}

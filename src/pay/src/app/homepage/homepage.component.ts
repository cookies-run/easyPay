import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd,ActivatedRoute} from '@angular/router';
import {AppService} from '../app.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss']
})
export class HomepageComponent implements OnInit {
  public params:any = {}
  public endpoint:string = 'login/logout?';

   admin:boolean;
   phone:string;
   isCollapsed:boolean = false;
   constructor(private router:Router,private appService:AppService){

   }


   goPage(n:string) {
     this.router.navigate([`/home/${n}`]);
   }

  logout() {
    sessionStorage.clear();
    //后台退出
    this.logOut(this.endpoint,this.params);
    this.router.navigate([`/login`]);
  }

  logOut(endpoint,params): void {
    this.appService.get(endpoint,params).subscribe(data => {

    });
  }

  ngOnInit() {
    this.phone = sessionStorage.getItem('phone');
    if(this.phone=='admin'){
      this.admin = true;
    }else {
      this.admin = false;
    }
  }

}

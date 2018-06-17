import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HomeRoutingModule } from './home-routing.module'; //路由模块
import {HashLocationStrategy, LocationStrategy} from '@angular/common';// 路由跳转(保证路由能正常跳转)
import { HttpModule} from '@angular/http'; // 服务器请求
import {HttpClientModule}  from'@angular/common/http';
import { AuthGuard } from './auth-guard.service'; //路由守卫
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgZorroAntdModule } from 'ng-zorro-antd';

import {AppService} from './app.service'; // 自定义服务模块
import { HeaderComponent } from './header/header.component';
import { HomepageComponent } from './homepage/homepage.component';
import { CheckPowerComponent } from './check-power/check-power.component';
import { StudentinfoComponent } from './studentinfo/studentinfo.component';
import { IncreasebillComponent } from './increasebill/increasebill.component';
import { HistorybillComponent } from './historybill/historybill.component';
import { AliBackComponent } from './ali-back/ali-back.component';
import { SchoolInfoComponent } from './school-info/school-info.component';
import { QRCodeModule } from 'angular2-qrcode';
import { FixStudentInfoComponent } from './fix-student-info/fix-student-info.component';
import { PengdingBillsComponent } from './pengding-bills/pengding-bills.component';
import { BillsDetailComponent } from './bills-detail/bills-detail.component';
import { ManagerComponent } from './manager/manager.component';
import {UserListComponent} from "./user-list/user-list.component"; //zrrro ui 组件

@NgModule({
  declarations: [
    HomepageComponent,
    HeaderComponent,
    CheckPowerComponent,
    StudentinfoComponent,
    IncreasebillComponent,
    HistorybillComponent,
    AliBackComponent,
    SchoolInfoComponent,
    FixStudentInfoComponent,
    PengdingBillsComponent,
    BillsDetailComponent,
    ManagerComponent,
    UserListComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule,
    CommonModule,
    QRCodeModule,
    HomeRoutingModule,
    NgZorroAntdModule
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, AppService, AuthGuard],

})
export class HomeModule { }

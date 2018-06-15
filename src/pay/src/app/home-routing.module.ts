import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth-guard.service';

import { HomepageComponent } from './homepage/homepage.component';
import { CheckPowerComponent } from './check-power/check-power.component';
import { StudentinfoComponent } from './studentinfo/studentinfo.component';
import { IncreasebillComponent } from './increasebill/increasebill.component';
import { HistorybillComponent } from './historybill/historybill.component';
import { AliBackComponent } from './ali-back/ali-back.component';
import { SchoolInfoComponent } from './school-info/school-info.component';
import { FixStudentInfoComponent } from './fix-student-info/fix-student-info.component';
import { PengdingBillsComponent } from './pengding-bills/pengding-bills.component';
import { BillsDetailComponent } from './bills-detail/bills-detail.component';
import { ManagerComponent } from './manager/manager.component';
import {UserListComponent} from "./user-list/user-list.component";

const homeRoutes: Routes = [
  { path: '', canActivate: [AuthGuard], component: HomepageComponent,
    children: [
      {path: 'checkPower', component: CheckPowerComponent},
      {path: 'addbill', component: IncreasebillComponent},
      {path: 'studInfo', component: StudentinfoComponent},
      {path: 'hisbill', component: HistorybillComponent},
      {path: 'aliback', component: AliBackComponent},
      {path: 'SchoolInfo', component: SchoolInfoComponent},
      {path: 'fixStudentInfo', component: FixStudentInfoComponent},
      {path: 'PengdingBills', component: PengdingBillsComponent},
      {path: 'BillsDetail', component: BillsDetailComponent},
      {path: 'Manager', component: ManagerComponent},
      {path: 'UserList', component: UserListComponent},
    ]}
];

@NgModule({
  imports: [ RouterModule.forChild(homeRoutes) ],
  exports: [ RouterModule ]
})
export class HomeRoutingModule {}

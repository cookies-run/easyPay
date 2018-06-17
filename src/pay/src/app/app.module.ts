import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule }   from '@angular/forms';
import { AppRoutingModule } from './app-routing.module'; //路由模块
import {HashLocationStrategy, LocationStrategy} from '@angular/common';// 路由跳转(保证路由能正常跳转)
import { HttpModule} from '@angular/http'; // 服务器请求
import { BrowserAnimationsModule } from '@angular/platform-browser/animations'; //动画；
import {HttpClientModule} from'@angular/common/http'
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


import { AppComponent } from './app.component';
import {AppService} from './app.service'; // 自定义服务模块
import { LoginComponent } from './loginpage/loginpage.component';
import { QRCodeModule } from 'angular2-qrcode';
import { NgZorroAntdModule } from 'ng-zorro-antd';

@NgModule({
  declarations:[
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule,
    BrowserAnimationsModule,
    QRCodeModule,
    AppRoutingModule,
    CommonModule,
    NgZorroAntdModule.forRoot()
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}, AppService],
  bootstrap: [AppComponent]
})
export class AppModule { }

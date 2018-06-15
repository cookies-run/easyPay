//从angular animations module 导入需要的动画方法
import {trigger,state,animate,transition,style} from '@angular/animations';
export const slideInOutAnimation=
  //触发器名称，附加这个动画到元素上使用[@triggerName]语法
  trigger('slideInOutAnimation',[
    state('*', style({ position: 'fixed', width:'100%',height:'100%' })),
    transition(':enter', [
      style({ transform: 'translateX(100%)' }),
      animate('0.5s ease-in-out', style({ transform: 'translateX(0%)'}))
    ]),
    transition(':leave', [
      style({ transform: 'translateX(0%)'}),
      animate('0.5s ease-in-out', style({ transform: 'translateX(-100%)'}))
    ])
  ])

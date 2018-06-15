import { Component,OnInit,ViewChild,Input} from '@angular/core';
import { TabsetComponent } from 'ngx-bootstrap';
import {trigger,state,style,animate,transition} from '@angular/animations';

 
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  animations:[
    trigger ('headerAnimation',[
    	// state('in', style({transform: 'translateX(0)'})),
        // transition('void => *', [
        //    animate('1s 0.1s ease-out',style({transform: 'translateX(-100%)'}))
        // ]),
	    transition('* => void', [
	      animate('1s 0.1s ease-out', style({transform: 'translateX(100%)'}))
	    ])
    	]) 
  ]
})
export class HeaderComponent implements OnInit {
  heroImageUrl = 'assets/img/hello.jpg';
  title = 'sbsbsb';
  cpp=false; 
   @Input() list : string;

   constructor() {}
  
  zlp () {
  	 alert('hahah');
  }

  ngOnInit(): void {
     
     }
    


}

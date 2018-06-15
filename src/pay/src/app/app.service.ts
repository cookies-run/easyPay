import { Injectable } from '@angular/core';
import {Http,RequestOptions,URLSearchParams} from '@angular/http';
import 'rxjs/add/operator/map';


@Injectable()
export class AppService {

   private Url = "";
   constructor(private http:Http){}

   get(endpoint:string,user?:any,options?:RequestOptions){

      if (!options) {
      options = new RequestOptions();
    }

    // Support easy query params for GET requests
    if (user) {
      let p = new URLSearchParams();
      for (let k in user) {
        p.set(k, user[k]);
      }
      // Set the search field if we have params and don't already have
      // a search field set in options.
      options.search = !options.search && p || options.search;

    }
       console.log(options);
     return this.http.get(`${this.Url}${endpoint}`,options);

  }

  post(url:string,data:any){
     return this.http.post(url,data);
  }


}

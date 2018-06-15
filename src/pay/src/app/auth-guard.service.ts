import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (sessionStorage.getItem('phone') !='') {
      return true;
    }
    let url: string = state.url;
    sessionStorage.setItem('redirectUrl', url);
    this.router.navigate(['/login']);
    return false;
  }

}

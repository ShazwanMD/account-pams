import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from '../../../../services/authentication.service';

@Injectable()
export class AuthenticationGuard implements CanActivate {
  constructor(private router: Router,
              private authnService: AuthenticationService) {
  }

  canActivate(): boolean {
    if (this.authnService.isLoggedIn()) {
      return true;
    } else {
      this.router.navigate(['/login']);
      // todo: capture attempted URL for redirecting
      // this.router.routerState.snapshot.url;
      // todo: this.authnService.redirectUrl = state. url
      return false;
    }
  }
}

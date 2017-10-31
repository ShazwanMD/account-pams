import { Module } from './../../../shared/model/system/module.interface';
import { AuthenticatedUser } from './../../../shared/model/identity/authenticated-user.interface';
import { SystemService } from './../../../../services/system.service';
import { AuthorizationService } from './../../../../services/authorization.service';
import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {AuthenticationService} from '../../../../services/authentication.service';
@Injectable()
export class AuthenticationGuard implements CanActivate {
  constructor(private router: Router,
              private authnService: AuthenticationService,
              private authzService: AuthorizationService,
              private systemService: SystemService) {
  }
  canActivate(): boolean {
    if (this.authnService.isLoggedIn()) {
  
      this.populateUser();
      this.populatePermission();
  
      return true;
    } else {
      this.router.navigate(['/login']);
      // todo: capture attempted URL for redirecting
      // this.router.routerState.snapshot.url;
      // todo: this.authnService.redirectUrl = state. url
      return false;
    }
  }
  populateUser(): void {
    console.log('populate user');
    this.systemService.findAuthenticatedUser()
      .map((user: AuthenticatedUser) => {
        this.authnService.authenticatedUser = user;
        console.log('user: ' + JSON.stringify(user));
      })
      .toPromise();
  }
  populatePermission(): void {
    console.log('populate permission');
    this.authnService.roles.forEach((role: string) => {
      this.authzService.attachRole(role);
    });
    this.systemService.findAuthorizedModules()
      .map((modules: Module[]) => {
        // load authorized modules
        for (let module of modules) {
          console.log('module: ' + module.code);
          this.authzService.addAbility('ROLE_USER', 'VIEW_' + module.code);
        }
      })
      .toPromise();
  }
}
import {Injectable} from '@angular/core';
import {CanActivate} from '@angular/router';
import {AuthorizationService} from '../../../../services/authorization.service';

@Injectable()
export class AuthorizationGuard implements CanActivate {
  constructor(private authzService: AuthorizationService) {
  }

  canActivate(): boolean {
    return true;
  }
}

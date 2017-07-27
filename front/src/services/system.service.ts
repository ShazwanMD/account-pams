import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {Module} from '../app/shared/model/system/module.interface';
import {AuthenticatedUser} from '../app/shared/model/identity/authenticated-user.interface';

@Injectable()
export class SystemService {

  private SYSTEM_API: string = environment.endpoint + '/api/system';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // MODULES
  // ====================================================================================================

  findAuthorizedModules(): Observable<Module[]> {
    return this._http.get(this.SYSTEM_API + '/modules/authorized')
      .map((res: Response) => <Module[]>res.json());
  }

  findAuthenticatedUser(): Observable<AuthenticatedUser> {
    return this._http.get(this.SYSTEM_API + '/authenticatedUser')
      .map((res: Response) => <AuthenticatedUser>res.json());
  }
}

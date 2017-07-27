import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/filter';
import {Store, Action, Dispatcher} from '@ngrx/store';
import {Http, Response} from '@angular/http';
import {Headers} from '@angular/http';
import {JwtHelper, tokenNotExpired} from 'angular2-jwt';
import {environment} from '../environments/environment';
import {AuthenticatedUser} from '../app/shared/model/identity/authenticated-user.interface';
import {ApplicationState} from '../app/app.module';
import {Router} from '@angular/router';

/**
 */
@Injectable()
export class AuthenticationService {

  private _roles: string[];
  private _authenticatedUser: AuthenticatedUser;
  private jwtHelper: JwtHelper = new JwtHelper();
  public token: string;
  public parsedToken: any;

  constructor(private http: Http,
              private router: Router,
              private store: Store<ApplicationState>) {
    let currentUser: any = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
    console.log('ctor:currentUser: ' + currentUser);
    console.log('ctor:token: ' + this.token);

    // look at claims
    if (currentUser) {
      this.parseRoles();
    }
  }

  login(username: string, password: string): Observable<boolean> {
    return this.http.post(
      environment.endpoint + '/api/authentication/login',
      JSON.stringify({username: username, password: password}),
      {headers: new Headers({'Content-Type': 'application/json'})})
      .map((res: Response) => {
        // login successful if there's a jwt token in the res
        let token: any = res.json() && res.json().token;
        if (token) {
          this.token = token;
          console.log('token: ' + this.token);

          // store username and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify({username: username, token: token}));

          // look at claims
          this.parseRoles();

          // return true to indicate successful login
          return true;
        } else {
          // return false to indicate failed login
          return false;
        }
      })
      ;
  }

  logout(): void {
    // clear token remove user from local
    // storage to log user out
    this.token = undefined;
    localStorage.removeItem('currentUser');
    localStorage.clear();
    this.authenticatedUser = undefined;
    // todo: clear store by dispatching init
    // todo:
    // this.store.dispatch(<Action>{type: Dispatcher.INIT});
  }

  checkCredentials() {
    if (localStorage.getItem('user') === null) {
      this.router.navigate(['Login']);
    }
  }

  parseRoles(): void {
    this.parsedToken = this.jwtHelper.decodeToken(this.token);
    this._roles = this.parsedToken.role.split(',');
    console.log('role: ' + this._roles);
  }

  isLoggedIn(): boolean {
    let currentUser: any = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
    return this.token ? !this.jwtHelper.isTokenExpired(this.token) : false;
  }

  currentUsername(): any {
    return JSON.parse(localStorage.getItem('currentUser'));
  }

  get roles(): string[] {
    return this._roles;
  }

  get authenticatedUser(): AuthenticatedUser {
    return this._authenticatedUser;
  }

  set authenticatedUser(value: AuthenticatedUser) {
    this._authenticatedUser = value;
  }
}

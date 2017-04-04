import { Injectable } from '@angular/core';
import {Response, Http} from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import {Account} from "../app/account/accounts/account.interface";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";

@Injectable()
export class AccountService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // ACCOUNT
  // ====================================================================================================

  findAccounts(): Observable<Account[]> {
    console.log("findAccounts");
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/accounts')
      .map((res: Response) => <Account[]>res.json());
  }

  findAccountByCode(code: string): Observable<Account> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    console.log("encoded uri: " + encodeURI (code))
    return this.http.get(environment.endpoint + '/api/account/accounts/' + encodeURI (code))
      .map((res: Response) => <Account>res.json());
  }

  saveAccount(account: Account): Observable<Boolean> {
    return this.http.post(environment.endpoint + '/api/account/accounts', JSON.stringify(account))
      .flatMap(data => Observable.of(true));
  }

  updateAccount(account: Account): Observable<Boolean> {
    return this.http.put(environment.endpoint + '/api/account/accounts', JSON.stringify(account))
      .flatMap(data => Observable.of(true));
  }
}

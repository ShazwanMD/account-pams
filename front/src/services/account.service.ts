import { Injectable } from '@angular/core';
import {Response, Http} from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import {Account} from "../app/account/accounts/account.interface";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {ChargeCode} from "../app/account/charge-codes/charge-code.interface";
import {FeeSchedule} from '../app/account/accounts/fee-schedule.interface';
import {AccountTransaction} from "../app/account/accounts/account-transaction.interface";

@Injectable()
export class AccountService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

    // ====================================================================================================
  // Fee Schedule
  // ====================================================================================================
  findFeeSchedules(): Observable<FeeSchedule[]> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/feeSchedules')
      .map((res: Response) => <FeeSchedule[]>res.json());
  }

  findFeeScheduleBySchedule(Schedule: string): Observable<FeeSchedule> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/feeSchedules/' + Schedule)
      .map((res: Response) => <FeeSchedule>res.json());
  }

  // ====================================================================================================
  // CHARGE CODE
  // ====================================================================================================
  findChargeCodes(): Observable<ChargeCode[]> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/chargeCodes')
      .map((res: Response) => <ChargeCode[]>res.json());
  }

  findChargeCodeByCode(code: string): Observable<ChargeCode> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/chargeCodes/' + code)
      .map((res: Response) => <ChargeCode>res.json());
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

  findAccountTransactions(account:Account): Observable<AccountTransaction[]> {
    console.log("findAccountTransactions");
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/accounts/' + account.code + "/accountTransactions")
      .map((res: Response) => <AccountTransaction[]>res.json());
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

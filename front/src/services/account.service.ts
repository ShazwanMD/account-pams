import {Injectable} from '@angular/core';
import {Response, Http, RequestOptions, Headers} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Account} from "../app/account/accounts/account.interface";
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {ChargeCode} from "../app/account/charge-codes/charge-code.interface";
import {FeeSchedule} from '../app/account/fee-schedules/fee-schedule.interface';
import {AccountTransaction} from "../app/account/accounts/account-transaction.interface";
import {AcademicSession} from "../app/account/academic-sessions/academic-session.interface";
import {FeeScheduleItem} from "../app/account/fee-schedules/fee-schedule-item.interface";

@Injectable()
export class AccountService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // FEE SCHEDULE
  // ====================================================================================================
  findFeeSchedules(): Observable<FeeSchedule[]> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/feeSchedules')
      .map((res: Response) => <FeeSchedule[]>res.json());
  }

  findFeeScheduleByCode(code: string): Observable<FeeSchedule> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/feeSchedules/' + code)
      .map((res: Response) => <FeeSchedule>res.json());
  }

  findFeeScheduleItems(feeSchedule: FeeSchedule): Observable<FeeScheduleItem[]> {
    console.log("findFeeScheduleItems");
    return this.http.get(environment.endpoint + '/api/account/feeSchedules/' + feeSchedule.code + "/feeScheduleItems")
      .map((res: Response) => <FeeScheduleItem[]>res.json());
  }

  saveFeeSchedule(feeSchedule: FeeSchedule): Observable<String> {
    console.log("saveFeeSchedule",feeSchedule);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/feeSchedules', JSON.stringify(feeSchedule), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateFeeSchedule(feeSchedule: FeeSchedule): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/account/feeSchedules', JSON.stringify(feeSchedule))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addFeeScheduleItem(feeSchedule: FeeSchedule, item: FeeScheduleItem): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/feeSchedules/' + feeSchedule.code + '/feeScheduleItems', JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
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
  // ACADEMIC SESSION
  // ====================================================================================================

  findAcademicSessions(): Observable<AcademicSession[]> {
    console.log("findAcademicSessions");
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/academicSessions')
      .map((res: Response) => <AcademicSession[]>res.json());
  }

  findAcademicSessionByCode(code: string): Observable<AcademicSession> {
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    console.log("encoded uri: " + encodeURI(code))
    return this.http.get(environment.endpoint + '/api/account/academicSessions/' + encodeURI(code))
      .map((res: Response) => <AcademicSession>res.json());
  }

  saveAcademicSession(academicSession: AcademicSession): Observable<Boolean> {
    return this.http.post(environment.endpoint + '/api/account/academicSessions', JSON.stringify(academicSession))
      .flatMap(data => Observable.of(true));
  }

  updateAcademicSession(academicSession: AcademicSession): Observable<Boolean> {
    return this.http.put(environment.endpoint + '/api/account/academicSessions', JSON.stringify(academicSession))
      .flatMap(data => Observable.of(true));
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
    console.log("encoded uri: " + encodeURI(code))
    return this.http.get(environment.endpoint + '/api/account/accounts/' + encodeURI(code))
      .map((res: Response) => <Account>res.json());
  }

  findAccountTransactions(account: Account): Observable<AccountTransaction[]> {
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

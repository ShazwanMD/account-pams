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
import {AccountCharge} from "../app/account/accounts/account-charge.interface";
import {AccountWaiver} from "../app/account/accounts/account-waiver.interface";
import {AdmissionCharge} from "../app/account/accounts/admission-charge.interface";
import {EnrollmentCharge} from "../app/account/accounts/enrollment-charge.interface";

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
    console.log("saveFeeSchedule", feeSchedule);
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

  updateFeeScheduleItem(feeSchedule: FeeSchedule, item: FeeScheduleItem){
    console.log("saving feeSchedule item" + item.id);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/account/feeSchedules/' + feeSchedule.code + '/feeScheduleItems/' + item.id,  JSON.stringify(item), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteFeeScheduleItem(feeSchedule: FeeSchedule, item: FeeScheduleItem) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/account/feeSchedules/' + feeSchedule.code + '/feeScheduleItems/' + item.id, options)
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

  saveChargeCode(code: ChargeCode): Observable<String> {
    console.log("saving chargecode");
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/chargeCodes', JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateChargeCode(code: ChargeCode){
      console.log("saving chargecode" + code.id);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/account/chargeCodes/' + code.code,  JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeChargeCode(code: ChargeCode) {
      let headers = new Headers({
          'Content-Type': 'application/json',
          //'Authorization': 'Bearer ' + this.authService.token
        });
        let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/account/chargeCodes/' + code.code, options)
      .flatMap((res: Response) => Observable.of(res.text()));
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

  findAccountsByFilter(filter: string): Observable<Account[]> {
    console.log("findAccountsByFilter");
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/accounts/byFilter/' + filter)
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

  findAccountCharges(account: Account): Observable<AccountCharge[]> {
    console.log("findAccountCharges");
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/accounts/' + account.code + "/accountCharges")
      .map((res: Response) => <AccountCharge[]>res.json());
  }

  findAccountWaivers(account: Account): Observable<AccountWaiver[]> {
    console.log("findAccountWaivers :" + account.code);
    // let headers = new Headers({'Authorization': 'Bearer TODO'});
    // let options = new RequestOptions({headers: headers});
    return this.http.get(environment.endpoint + '/api/account/accounts/' + account.code + "/accountWaivers")
      .map((res: Response) => <AccountWaiver[]>res.json());
  }

  saveAccount(account: Account): Observable<Boolean> {
      let headers = new Headers({
          'Content-Type': 'application/json',
          //'Authorization': 'Bearer ' + this.authService.token
        });
        let options = new RequestOptions({headers: headers});
      return this.http.post(environment.endpoint + '/api/account/accounts', JSON.stringify(account), options)
      .flatMap(data => Observable.of(true));
  }

  updateAccount(account: Account): Observable<Boolean> {
    return this.http.put(environment.endpoint + '/api/account/accounts', JSON.stringify(account))
      .flatMap(data => Observable.of(true));
  }

  addAccountCharge(account: Account, charge: AccountCharge): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/accounts/' + account.code + '/accountCharges', JSON.stringify(charge), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addAccountWaiver(account: Account, waiver: AccountWaiver): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/accounts/' + account.code + '/accountWaivers', JSON.stringify(waiver), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addAdmissionCharge(account: Account, charge:AdmissionCharge ): Observable<String> {
    console.log("saving admission charge");
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/accounts/' + account.code + '/admissionCharges', JSON.stringify(charge), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeAdmissionCharge(account: Account, charge: AdmissionCharge): Observable<String> {
        let headers = new Headers({
         'Content-Type': 'application/json',
        //'Authorization': 'Bearer ' + this.authService.token
        });
        let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/application/accounts/' + account.code + '/admisionCharges/' + charge.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addEnrollmentCharge(account: Account, charge:EnrollmentCharge ): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/account/accounts/' + account.code + '/enrollmentCharges', JSON.stringify(charge), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}

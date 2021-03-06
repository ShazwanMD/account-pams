import { Sponsor } from './../app/shared/model/identity/sponsor.interface';
import {Injectable} from '@angular/core';
import {RequestOptions, Response, ResponseContentType, Headers} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Account} from '../app/shared/model/account/account.interface';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {ChargeCode} from '../app/shared/model/account/charge-code.interface';
import {FeeSchedule} from '../app/shared/model/account/fee-schedule.interface';
import {AccountTransaction} from '../app/shared/model/account/account-transaction.interface';
import {AcademicSession} from '../app/shared/model/account/academic-session.interface';
import {FeeScheduleItem} from '../app/shared/model/account/fee-schedule-item.interface';
import {AccountCharge} from '../app/shared/model/account/account-charge.interface';
import {AccountWaiver} from '../app/shared/model/account/account-waiver.interface';
import {AccountActivity} from '../app/shared/model/account/account-activity.interface';
import { AccountSponsorship } from "../app/shared/model/account/account-sponsorship.interface";
import { AccountActivityCharge } from "../app/shared/model/account/account-activity-charge.interface";

@Injectable()
export class AccountService {

  private ACCOUNT_API: string = environment.endpoint + '/api/account';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // FEE SCHEDULE
  // ====================================================================================================
  findFeeSchedules(): Observable<FeeSchedule[]> {
    return this._http.get(this.ACCOUNT_API + '/feeSchedules')
      .map((res: Response) => <FeeSchedule[]>res.json());
  }

  findFeeScheduleByCode(code: string): Observable<FeeSchedule> {
    return this._http.get(this.ACCOUNT_API + '/feeSchedules/' + code)
      .map((res: Response) => <FeeSchedule>res.json());
  }

  findFeeScheduleItems(feeSchedule: FeeSchedule): Observable<FeeScheduleItem[]> {
    console.log('findFeeScheduleItems');
    return this._http.get(this.ACCOUNT_API + '/feeSchedules/' + feeSchedule.code + '/feeScheduleItems')
      .map((res: Response) => <FeeScheduleItem[]>res.json());
  }

  saveFeeSchedule(feeSchedule: FeeSchedule): Observable<String> {
    console.log('saveFeeSchedule', feeSchedule);
    return this._http.post(this.ACCOUNT_API + '/feeSchedules', JSON.stringify(feeSchedule))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateFeeSchedule(feeSchedule: FeeSchedule): Observable<String> {
    console.log('updating feeSchedule' + feeSchedule.code);
    return this._http.put(this.ACCOUNT_API + '/feeSchedules/' + feeSchedule.code, JSON.stringify(feeSchedule))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addFeeScheduleItem(feeSchedule: FeeSchedule, item: FeeScheduleItem): Observable<String> {
    return this._http.post(this.ACCOUNT_API + '/feeSchedules/' + feeSchedule.code + '/feeScheduleItems', JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateFeeScheduleItem(feeSchedule: FeeSchedule, item: FeeScheduleItem): Observable<String> {
    console.log('saving feeSchedule item' + item.id);
    return this._http.put(this.ACCOUNT_API + '/feeSchedules/' + feeSchedule.code + '/feeScheduleItems/' + item.id, JSON.stringify(item))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteFeeScheduleItem(feeSchedule: FeeSchedule, item: FeeScheduleItem) {
    return this._http.delete(this.ACCOUNT_API + '/feeSchedules/' + feeSchedule.code + '/feeScheduleItems/' + item.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  uploadFeeSchedule(file: File): Observable<String> {
    console.log('uploadFeeSchedule');
    let formData: FormData = new FormData();
    formData.append('file', file);
    let headers: Headers = new Headers();
    headers.set('Accept', 'application/json');
    let options: RequestOptions = new RequestOptions({headers: headers});
    return this._http.post(this.ACCOUNT_API + '/feeSchedules/upload', formData, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  downloadFeeSchedule(schedule: FeeSchedule): Observable<File> {
    console.log('downloadFeeSchedule');
    let options: RequestOptions = new RequestOptions({responseType: ResponseContentType.ArrayBuffer});
    return this._http.get(this.ACCOUNT_API + '/feeSchedules/' + schedule.code + '/download', options)
      .map((res: Response) => {
        let type = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
        let filename = schedule.code + '.xlsx';
        return new File([res.arrayBuffer()], filename, {type: type});
      });
  }

  // ====================================================================================================
  // CHARGE CODE
  // ====================================================================================================

  findChargeCodes(): Observable<ChargeCode[]> {
    return this._http.get(this.ACCOUNT_API + '/chargeCodes')
      .map((res: Response) => <ChargeCode[]>res.json());
  }

  findChargeCodeByCode(code: string): Observable<ChargeCode> {
    return this._http.get(this.ACCOUNT_API + '/chargeCodes/' + code)
      .map((res: Response) => <ChargeCode>res.json());
  }

  saveChargeCode(code: ChargeCode): Observable<String> {
    console.log('saving chargecode');
    return this._http.post(this.ACCOUNT_API + '/chargeCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateChargeCode(code: ChargeCode) {
    console.log('saving chargecode' + code.id);
    return this._http.put(this.ACCOUNT_API + '/chargeCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeChargeCode(code: ChargeCode) {
    return this._http.delete(this.ACCOUNT_API + '/chargeCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // ACADEMIC SESSION
  // ====================================================================================================

  findAcademicSessions(): Observable<AcademicSession[]> {
    console.log('findAcademicSessions');
    return this._http.get(this.ACCOUNT_API + '/academicSessions')
      .map((res: Response) => <AcademicSession[]>res.json());
  }

  findAcademicSessionByCode(code: string): Observable<AcademicSession> {
    console.log('encoded uri: ' + encodeURI(code));
    return this._http.get(this.ACCOUNT_API + '/academicSessions/' + encodeURI(code))
      .map((res: Response) => <AcademicSession>res.json());
  }

  saveAcademicSession(academicSession: AcademicSession): Observable<Boolean> {
    return this._http.post(this.ACCOUNT_API + '/academicSessions', JSON.stringify(academicSession))
      .flatMap((data) => Observable.of(true));
  }

  updateAcademicSession(academicSession: AcademicSession): Observable<Boolean> {
    return this._http.put(this.ACCOUNT_API + '/academicSessions', JSON.stringify(academicSession))
      .flatMap((data) => Observable.of(true));
  }

  // ====================================================================================================
  // ACCOUNT
  // ====================================================================================================

  findAccounts(): Observable<Account[]> {
    console.log('findAccounts');
    return this._http.get(this.ACCOUNT_API + '/accounts')
      .map((res: Response) => <Account[]>res.json());
  }

  findAccountsByFilter(filter: string): Observable<Account[]> {
    console.log('findAccountsByFilter');
    return this._http.get(this.ACCOUNT_API + '/accounts/byFilter/' + filter)
      .map((res: Response) => <Account[]>res.json());
  }

  findAccountByCode(code: string): Observable<Account> {
    console.log('encoded uri: ' + encodeURI(code));
    return this._http.get(this.ACCOUNT_API + '/accounts/' + encodeURI(code))
      .map((res: Response) => <Account>res.json());
  }

  findAccountsByActor(): Observable<Account[]> {
    console.log('findAccountsByActor');
    return this._http.get(this.ACCOUNT_API + '/accounts/byActor/student')
      .map((res: Response) => <Account[]>res.json());
  }

  findAccountsByActorSponsor(): Observable<Account[]> {
    console.log('findAccountsByActorSponsor');
    return this._http.get(this.ACCOUNT_API + '/accounts/byActor/sponsor')
      .map((res: Response) => <Account[]>res.json());
  }

  findAccountsByActorStaff(): Observable<Account[]> {
    console.log('findAccountsByActorStaff');
    return this._http.get(this.ACCOUNT_API + '/accounts/byActor/staff')
      .map((res: Response) => <Account[]>res.json());
  }

  findAccountTransactions(account: Account): Observable<AccountTransaction[]> {
    console.log('findAccountTransactions');
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountTransactions')
      .map((res: Response) => <AccountTransaction[]>res.json());
  }

  findAccountChargeActivities(account: Account): Observable<AccountActivityCharge[]> {
      console.log('findAccountActivities :' + account.id);
      return this._http.get(this.ACCOUNT_API + '/accounts/' + account.id + '/accountChargeActivities')
        .map((res: Response) => <AccountActivityCharge[]>res.json());
    }

  // ====================================================================================================
  // ACCOUNT
  // ====================================================================================================
  
  // generic account charges
  findAccountCharges(account: Account): Observable<AccountCharge[]> {
    console.log('findAccountCharges');
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges')
      .map((res: Response) => <AccountCharge[]>res.json());
  }

  findSecurityAccountCharges(account: Account): Observable<AccountCharge[]> {
    console.log('findSecurityAccountCharges');
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges/chargeType/'
      + 'SECURITY')
      .map((res: Response) => <AccountCharge[]>res.json());
  }

  findStudentAffairsAccountCharges(account: Account): Observable<AccountCharge[]> {
    console.log('findStudentAffairsAccountCharges');
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges/chargeType/'
      + 'STUDENT_AFFAIRS')
      .map((res: Response) => <AccountCharge[]>res.json());
  }

  findAdmissionAccountCharges(account: Account): Observable<AccountCharge[]> {
    console.log('findAdmissionAccountCharges');
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges/chargeType/'
      + 'ACADEMIC')
      .map((res: Response) => <AccountCharge[]>res.json());
  }

  findLoanAccountCharges(account: Account): Observable<AccountCharge[]> {
    console.log('findLoanAccountCharges');
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges/chargeType/'
      + 'LOAN')
      .map((res: Response) => <AccountCharge[]>res.json());
  }

  findAccountActivities(account: Account): Observable<AccountActivity[]> {
    console.log('findAccountActivities :' + account.id);
    return this._http.get(this.ACCOUNT_API + '/accounts/' + account.id + '/accountActivities')
      .map((res: Response) => <AccountActivity[]>res.json());
  }

  findAccountActivitiesByAcademicSession(account: Account): Observable<AccountActivity[]> {
      console.log('findAccountActivities :' + account.code);
      return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/academicSessions')
        .map((res: Response) => <AccountActivity[]>res.json());
    }

  findAccountWaivers(account: Account): Observable<AccountWaiver[]> {
      console.log('findAccountWaivers :' + account.code);
      return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/accountWaivers')
        .map((res: Response) => <AccountWaiver[]>res.json());
    }

  saveAccount(account: Account): Observable<Boolean> {
    return this._http.post(this.ACCOUNT_API + '/accounts', JSON.stringify(account))
      .flatMap((data) => Observable.of(true));
  }

  updateAccount(account: Account): Observable<Boolean> {
    return this._http.put(this.ACCOUNT_API + '/accounts', JSON.stringify(account))
      .flatMap((data) => Observable.of(true));
  }

  reviseAccount(account: Account): Observable<String> {
    return this._http.post(this.ACCOUNT_API + '/accounts/' + account.code + '/revise', JSON.stringify(account))
      .flatMap((res) => Observable.of(res.text()));
  }

  addAccountWaiver(account: Account, waiver: AccountWaiver): Observable<String> {
    return this._http.post(this.ACCOUNT_API + '/accounts/' + account.code + '/accountWaivers', JSON.stringify(waiver))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  addAccountCharge(account: Account, charge: AccountCharge): Observable<String> {
    return this._http.post(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges', JSON.stringify(charge))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateAccountCharge(account: Account, charge: AccountCharge): Observable<String> {
    console.log('updating account charge' + charge.referenceNo);
    return this._http.put(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges/' + charge.referenceNo, JSON.stringify(charge))
      .flatMap((res: Response) => Observable.of(res.text()));

  }

  removeAccountCharge(account: Account, charge: AccountCharge): Observable<String> {
    console.log('removing account charge' + charge.referenceNo);
    return this._http.delete(this.ACCOUNT_API + '/accounts/' + account.code + '/accountCharges/' + charge.referenceNo)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  findUnpaidAccountCharges( account: Account ): Observable<AccountCharge[]> {
    console.log( 'findUnpaidAccountCharges' );
    return this._http.get( this.ACCOUNT_API + '/accountCharges/unpaidAccountCharges/' + account.code )
        .map(( res: Response ) => <AccountCharge[]>res.json() );
  }

  findCompletedAccountCharges(): Observable<AccountCharge[]> {
      console.log( 'findCompletedAccountCharges' );
      return this._http.get( this.ACCOUNT_API + '/accountCharges/state/COMPLETED' )
      .map(( res: Response ) => <AccountCharge[]>res.json() );
  }
  
  // ====================================================================================================
  // ACCOUNT - SPONSORSHIP
  // ====================================================================================================

   findAccountSponsorships(account: Account): Observable<AccountSponsorship[]> {
      console.log('findsponsorships xxx :' + account.code);
      return this._http.get(this.ACCOUNT_API + '/accounts/' + account.code + '/sponsorships')
        .map((res: Response) => <AccountSponsorship[]>res.json());
    }

    addAccountSponsorships(account: Account, sponsor: Sponsor, sponsorship: AccountSponsorship): Observable<String> {
      console.log('addsponsorships account code :' + account.code);
    return this._http.post(this.ACCOUNT_API + '/accounts/' + account.code + '/sponsor/' + sponsor.id + '/sponsorships', JSON.stringify(sponsorship))
      .flatMap((res: Response) => Observable.of(res.text()));
   }

  //  saveAccountSponsorships(account: AccountSponsorship): Observable<Boolean> {
  //   return this._http.post(this.ACCOUNT_API + '/accounts', JSON.stringify(account))
  //     .flatMap((data) => Observable.of(true));
  // }

    updateAccountSponsorships(account: Account,  sponsor: Sponsor, sponsorship: AccountSponsorship): Observable<String> {
    console.log('updating account sponsorship' + sponsorship.referenceNo);
    return this._http.put(this.ACCOUNT_API + '/accounts/' + account.code + '/sponsor/' + sponsor.id + '/sponsorships/' + sponsorship.referenceNo, JSON.stringify(sponsorship))
      .flatMap((res: Response) => Observable.of(res.text()));

  }

    removeAccountSponsorships(account: Account,  sponsor: Sponsor, sponsorship: AccountSponsorship): Observable<String> {
    console.log('removing account charge' + sponsorship.referenceNo);
    return this._http.delete(this.ACCOUNT_API + '/accounts/' + account.code + '/sponsor/' + sponsor.id + '/sponsorships/' + sponsorship.referenceNo)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

}

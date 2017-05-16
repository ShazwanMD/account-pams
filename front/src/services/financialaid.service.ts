import { Injectable } from '@angular/core';
import {Response, Http, Headers, RequestOptions} from '@angular/http';
import { HttpInterceptorService } from '@covalent/http';
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {Settlement} from "../app/financialaid/settlements/settlement.interface";
import {SettlementItem} from "../app/financialaid/settlements/settlement-item.interface";
import {WaiverApplicationTask} from "../app/financialaid/waiver-applications/waiver-application-task.interface";
import {WaiverApplication} from "../app/financialaid/waiver-applications/waiver-application.interface";
import {Sponsor} from "../app/identity/sponsor.interface";
import {FacultyCode} from "../app/common/faculty-codes/faculty-code.interface";
import {CohortCode} from "../app/common/cohort-codes/cohort-code.interface";
import {SettlementCreator} from "../app/financialaid/settlements/settlement-creator.interface";

@Injectable()
export class FinancialaidService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // SETTLEMENT
  // ====================================================================================================

  findSettlementByReferenceNo(referenceNo: string): Observable<Settlement> {
    console.log("findSettlementByReferenceNo",referenceNo);
    return this.http.get(environment.endpoint + '/api/financialaid/settlements/' + referenceNo)
      .map((res: Response) => <Settlement>res.json());
  }

  findSettlementById(id: string): Observable<Settlement> {
    console.log("findSettlementById",id);
    return this.http.get(environment.endpoint + '/api/financialaid/settlements/' + id)
      .map((res: Response) => <Settlement>res.json());
  }

  findSettlements(): Observable<Settlement[]> {
    console.log("findSettlements");
    return this.http.get(environment.endpoint + '/api/financialaid/settlements')
      .map((res: Response) => <Settlement[]>res.json());
  }

  findSettlementItems(settlement:Settlement): Observable<SettlementItem[]> {
    console.log("findSettlementItems");
    return this.http.get(environment.endpoint + '/api/financialaid/settlements/' + settlement.referenceNo + "/settlementItems")
      .map((res: Response) => <SettlementItem[]>res.json());
  }

  initSettlement(settlement: Settlement): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/financialaid/settlements/init', JSON.stringify(settlement), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementBySponsor(settlement: Settlement, sponsor:Sponsor): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/financialaid/settlements/initBySponsor/' + sponsor.identityNo, JSON.stringify(settlement), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementByFacultyCode(settlement: Settlement, facultyCode:FacultyCode): Observable<String> {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/financialaid/settlements/initByFacultyCode/' + facultyCode.code, JSON.stringify(settlement), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementByCohortCode(settlement: Settlement, cohortCode:CohortCode): Observable<String> {
    console.log("initSettlementByCohortCode",settlement,cohortCode);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/financialaid/settlements/initByCohortCode/' + cohortCode.code, JSON.stringify(settlement), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementByAcademicSession(settlementCreator:SettlementCreator): Observable<String> {
    console.log("init settlement",settlementCreator);
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/financialaid/settlements/initByAcademicSession/' + settlementCreator.academicSession.code, JSON.stringify(settlementCreator), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  processSettlement(settlement: Settlement): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.post(environment.endpoint + '/api/financialaid/settlements/process', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

  updateSettlement(settlement: Settlement): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.put(environment.endpoint + '/api/financialaid/settlements', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

  // ====================================================================================================
  // WAIVER APPLICATION
  // ====================================================================================================

  findAssignedWaiverApplicationTasks(): Observable<WaiverApplicationTask[]> {
    console.log("findAssignedWaiverApplicationTasks");
    return this.http.get(environment.endpoint + '/api/financialaid/waiverApplications/assignedTasks')
      .map((res: Response) => <WaiverApplicationTask[]>res.json());
  }

  findPooledWaiverApplicationTasks(): Observable<WaiverApplicationTask[]> {
    console.log("findPooledWaiverApplicationTasks");
    return this.http.get(environment.endpoint + '/api/financialaid/waiverApplications/pooledTasks')
      .map((res: Response) => <WaiverApplicationTask[]>res.json());
  }

  findWaiverApplicationTaskByTaskId(taskId: string): Observable<WaiverApplicationTask> {
    console.log("findWaiverApplicationTaskByTaskId");
    return this.http.get(environment.endpoint + '/api/financialaid/waiverApplications/viewTask/' + taskId)
      .map((res: Response) => <WaiverApplicationTask>res.json());
  }

  findWaiverApplicationByReferenceNo(referenceNo: string): Observable<WaiverApplication> {
    return this.http.get(environment.endpoint + '/api/financialaid/waiverApplications/' + referenceNo)
      .map((res: Response) => <WaiverApplication>res.json());
  }

  findWaiverApplicationByTaskId(taskId: string): Observable<WaiverApplication> {
    return this.http.get(environment.endpoint + '/api/financialaid/waiverApplications/' + taskId)
      .map((res: Response) => <WaiverApplication>res.json());
  }

  startWaiverApplicationTask(waiverApplication: WaiverApplication): Observable<Boolean> {
    return Observable.of(true);
// return this.http.post(environment.endpoint + '/api/financialaid/waiverApplications/startTask', JSON.stringify(waiverApplication))
    //   .flatMap(data => Observable.of(true));
  }

  updateWaiverApplication(waiverApplication: WaiverApplication): Observable<Boolean> {
    return Observable.of(true);
    // return this.http.put(environment.endpoint + '/api/financialaid/waiverApplications', JSON.stringify(waiverApplication))
    //   .flatMap(data => Observable.of(true));
  }
}

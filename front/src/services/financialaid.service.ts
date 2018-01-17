import {Injectable} from '@angular/core';
import {Response, RequestOptions,ResponseContentType, Headers} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {Settlement} from '../app/shared/model/financialaid/settlement.interface';
import {SettlementItem} from '../app/shared/model/financialaid/settlement-item.interface';
import {WaiverApplicationTask} from '../app/shared/model/financialaid/waiver-application-task.interface';
import {WaiverApplication} from '../app/shared/model/financialaid/waiver-application.interface';
import {Sponsor} from '../app/shared/model/identity/sponsor.interface';
import {FacultyCode} from '../app/shared/model/common/faculty-code.interface';
import {CohortCode} from '../app/shared/model/common/cohort-code.interface';
import {SettlementCreator} from '../app/shared/model/financialaid/settlement-creator.interface';
import { SponsorshipType } from "../app/shared/model/financialaid/sponsorship-type.enum";

@Injectable()
export class FinancialaidService {

  private FINANCIALAID_API: string = environment.endpoint + '/api/financialaid';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // SETTLEMENT
  // ====================================================================================================

  findSettlementByReferenceNo(referenceNo: string): Observable<Settlement> {
    console.log('findSettlementByReferenceNo', referenceNo);
    return this._http.get(this.FINANCIALAID_API + '/settlements/' + referenceNo)
      .map((res: Response) => <Settlement>res.json());
  }

  findSettlementById(id: string): Observable<Settlement> {
    console.log('findSettlementById', id);
    return this._http.get(this.FINANCIALAID_API + '/settlements/' + id)
      .map((res: Response) => <Settlement>res.json());
  }

  findSettlements(): Observable<Settlement[]> {
    console.log('findSettlements');
    return this._http.get(this.FINANCIALAID_API + '/settlements')
      .map((res: Response) => <Settlement[]>res.json());
  }

  findSettlementItems(settlement: Settlement): Observable<SettlementItem[]> {
    console.log('findSettlementItems');
    return this._http.get(this.FINANCIALAID_API + '/settlements/' + settlement.referenceNo + '/settlementItems')
      .map((res: Response) => <SettlementItem[]>res.json());
  }

  initSettlement(settlement: Settlement): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/settlements/init', JSON.stringify(settlement))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementBySponsor(settlement: Settlement, sponsor: Sponsor): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/settlements/initBySponsor/' + sponsor.identityNo, JSON.stringify(settlement))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementByFacultyCode(settlement: Settlement, facultyCode: FacultyCode): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/settlements/initByFacultyCode/' + facultyCode.code, JSON.stringify(settlement))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementByCohortCode(settlement: Settlement, cohortCode: CohortCode): Observable<String> {
    console.log('initSettlementByCohortCode', settlement, cohortCode);
    return this._http.post(this.FINANCIALAID_API + '/settlements/initByCohortCode/' + cohortCode.code, JSON.stringify(settlement))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  initSettlementByAcademicSession(settlementCreator: SettlementCreator): Observable<String> {
    console.log('init settlement', settlementCreator);
    return this._http.post(this.FINANCIALAID_API + '/settlements/initByAcademicSession/' + settlementCreator.academicSession.code, JSON.stringify(settlementCreator))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  processSettlement(settlement: Settlement): Observable<Boolean> {
    return Observable.of(true);
    // return this._http.post(this.FINANCIALAID_API + '/settlements/process', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

  updateSettlement(settlement: Settlement): Observable<Boolean> {
    return Observable.of(true);
    // return this._http.put(this.FINANCIALAID_API + '/settlements', JSON.stringify(settlement))
    //   .flatMap(data => Observable.of(true));
  }

  executeSettlement(settlement: Settlement): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/settlements/' + settlement.referenceNo + '/execute', null)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  uploadSettlement(sponsorshipType: SponsorshipType,file: File): Observable<String> {
      console.log('uploadSettlement :'+file);
      console.log('sponsorshipType :'+sponsorshipType.toString());
      // Pass empty Content-Type key for ng2 Upload bug workaround, see request.interceptor.ts
      let headers: Headers = new Headers({'Content-Type': ''});
      let options: RequestOptions = new RequestOptions({headers: headers});
      let formData: FormData = new FormData();
      formData.append('file', file);
      console.log('formData', formData);
      return this._http.post(this.FINANCIALAID_API + '/settlements/uploadSettlement/sponsorship/'+sponsorshipType.toString(), formData, options)
        .flatMap((res: Response) => Observable.of(res.text()));
    }

  addSettlementItem(settlement: Settlement, settlementItem: SettlementItem): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/settlements/' + settlement.referenceNo + '/settlementItems', JSON.stringify(settlementItem))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSettlementItem(settlement: Settlement, settlementItem: SettlementItem) {
    return this._http.put(this.FINANCIALAID_API + '/settlements/' + settlement.referenceNo + '/settlementItems/' + settlementItem.id, JSON.stringify(settlementItem))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  deleteSettlementItem(settlement: Settlement, settlementItem: SettlementItem) {
    return this._http.delete(this.FINANCIALAID_API + '/settlements/' + settlement.referenceNo + '/settlementItems/' + settlementItem.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // WAIVER APPLICATION
  // ====================================================================================================

  findCompletedWaiverApplications(): Observable<WaiverApplication[]> {
    console.log('findCompletedWaiverApplications');
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/state/COMPLETED')
      .map((res: Response) => <WaiverApplication[]>res.json());
  }

  findAssignedWaiverApplicationTasks(): Observable<WaiverApplicationTask[]> {
    console.log('findAssignedWaiverApplicationTasks');
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/assignedTasks')
      .map((res: Response) => <WaiverApplicationTask[]>res.json());
  }

  findPooledWaiverApplicationTasks(): Observable<WaiverApplicationTask[]> {
    console.log('findPooledWaiverApplicationTasks');
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/pooledTasks')
      .map((res: Response) => <WaiverApplicationTask[]>res.json());
  }

  findWaiverApplicationTaskByTaskId(taskId: string): Observable<WaiverApplicationTask> {
    console.log('findWaiverApplicationTaskByTaskId');
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/viewTask/' + taskId)
      .map((res: Response) => <WaiverApplicationTask>res.json());
  }

  findWaiverApplicationByReferenceNo(referenceNo: string): Observable<WaiverApplication> {
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/' + referenceNo)
      .map((res: Response) => <WaiverApplication>res.json());
  }

  findWaiverApplicationByTaskId(taskId: string): Observable<WaiverApplication> {
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/' + taskId)
      .map((res: Response) => <WaiverApplication>res.json());
  }

  findArchivedWaiverApplications(): Observable<WaiverApplication[]> {
    console.log('findArchivedWaiverApplications');
    return this._http.get(this.FINANCIALAID_API + '/waiverApplications/archived')
      .map((res: Response) => <WaiverApplication[]>res.json());
  }

  startWaiverApplicationTask(waiverApplication: WaiverApplication): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/waiverApplications/startTask', JSON.stringify(waiverApplication))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  completeWaiverApplicationTask(waiverApplicationTask: WaiverApplicationTask): Observable<String> {
    console.log('TaskId: ' + waiverApplicationTask.taskId);
    return this._http.post(this.FINANCIALAID_API + '/waiverApplications/completeTask', JSON.stringify(waiverApplicationTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  claimWaiverApplicationTask(waiverApplicationTask: WaiverApplicationTask): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/waiverApplications/claimTask', JSON.stringify(waiverApplicationTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  releaseWaiverApplicationTask(waiverApplicationTask: WaiverApplicationTask): Observable<String> {
    return this._http.post(this.FINANCIALAID_API + '/waiverApplications/releaseTask', JSON.stringify(waiverApplicationTask))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateWaiverApplication(waiverApplication: WaiverApplication): Observable<String> {
     console.log('updatedWaiverApplications' + waiverApplication.referenceNo);
     return this._http.put(this.FINANCIALAID_API + '/waiverApplications/' + waiverApplication.referenceNo + '/update', JSON.stringify(waiverApplication))
     .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  removeWaiverApplicationTask(waiverApplicationTask: WaiverApplicationTask): Observable<String> {
      return this._http.post(this.FINANCIALAID_API + '/waiverApplications/removeTask', JSON.stringify(waiverApplicationTask))
          .flatMap((res: Response) => Observable.of(res.text()));
  }

}

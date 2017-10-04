import { SecurityChargeCode } from '../app/shared/model/common/security-charge-code.interface';
import {Injectable} from '@angular/core';
import {Response} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {CohortCode} from '../app/shared/model/common/cohort-code.interface';
import {FacultyCode} from '../app/shared/model/common/faculty-code.interface';
import {CountryCode} from '../app/shared/model/common/country-code.interface';
import {ProgramCode} from '../app/shared/model/common/program-code.interface';
import {StudyMode} from '../app/shared/model/common/study-mode.interface';
import {StateCode} from '../app/shared/model/common/state-code.interface';
import {BankCode} from '../app/shared/model/common/bank-code.interface';
import {ResidencyCode} from '../app/shared/model/common/residency-code.interface';
import {TaxCode} from '../app/shared/model/common/tax-code.interface';

@Injectable()
export class CommonService {

  private COMMON_API: string = environment.endpoint + '/api/common';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // BANK CODES
  // ====================================================================================================

  findBankCodes(): Observable<BankCode[]> {
    console.log('findBankCodes()');
    return this._http.get(this.COMMON_API + '/bankCodes')
      .map((res: Response) => <BankCode[]>res.json());
  }

  findBankCodeByCode(code: string): Observable<BankCode> {
    console.log('findBankCodeByCode');
    return this._http.get(this.COMMON_API + '/bankCodes/' + code)
      .map((res: Response) => <BankCode>res.json());
  }

  saveBankCode(code: BankCode) {
    return this._http.post(this.COMMON_API + '/bankCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateBankCode(code: BankCode) {
    return this._http.put(this.COMMON_API + '/bankCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeBankCode(code: BankCode) {
    return this._http.delete(this.COMMON_API + '/bankCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // COHORT CODES
  // ====================================================================================================

  findCohortCodes(): Observable<CohortCode[]> {
    console.log('findCohortCodes');
    return this._http.get(this.COMMON_API + '/cohortCodes')
      .map((res: Response) => <CohortCode[]>res.json());
  }

  findCohortCodeByCode(code: string): Observable<CohortCode> {
    console.log('findCohortCodeByCode');
    return this._http.get(this.COMMON_API + '/cohortCodes/' + code)
      .map((res: Response) => <CohortCode>res.json());
  }

  saveCohortCode(code: CohortCode) {
    return this._http.post(this.COMMON_API + '/cohortCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeCohortCode(code: CohortCode) {
    return this._http.delete(this.COMMON_API + '/cohortCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateCohortCode(code: CohortCode) {
    return this._http.put(this.COMMON_API + '/cohortCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // FACULTY CODES
  // ====================================================================================================

  findFacultyCodes(): Observable<FacultyCode[]> {
    console.log('findFacultyCodes');
    return this._http.get(this.COMMON_API + '/facultyCodes')
      .map((res: Response) => <FacultyCode[]>res.json());
  }

  findFacultyCodeByCode(code: string): Observable<FacultyCode> {
    console.log('findFacultyCodeByCode');
    return this._http.get(this.COMMON_API + '/facultyCodes/' + code)
      .map((res: Response) => <FacultyCode>res.json());
  }

  findProgramCodesByFacultyCode(facultyCode: FacultyCode): Observable<ProgramCode[]> {
    console.log('findProgramCodesByFacultyCode');
    return this._http.get(this.COMMON_API + '/facultyCodes/' + facultyCode.code + '/programCodes')
      .map((res: Response) => <ProgramCode[]>res.json());
  }

  saveFacultyCode(code: FacultyCode) {
    return this._http.post(this.COMMON_API + '/facultyCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }


  // ====================================================================================================
  // PROGRAM CODES
  // ====================================================================================================

  findProgramCodes(): Observable<ProgramCode[]> {
    console.log('findProgramCodes');
    return this._http.get(this.COMMON_API + '/programCodes')
      .map((res: Response) => <ProgramCode[]>res.json());
  }

  findProgramCodeByCode(code: string): Observable<ProgramCode> {
    console.log('findProgramCodeByCode');
    return this._http.get(this.COMMON_API + '/programCodes/' + code)
      .map((res: Response) => <ProgramCode>res.json());
  }

  saveProgramCode(code: ProgramCode) {
    return this._http.post(this.COMMON_API + '/programCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeProgramCode(code: ProgramCode) {
    return this._http.delete(this.COMMON_API + '/programCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // RESIDENCY CODES
  // ====================================================================================================

  findResidencyCodes(): Observable<ResidencyCode[]> {
    console.log('findResidencyCodes');
    return this._http.get(this.COMMON_API + '/residencyCodes')
      .map((res: Response) => <ResidencyCode[]>res.json());
  }

  saveResidencyCode(code: ResidencyCode) {
    return this._http.post(this.COMMON_API + '/residencyCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateResidencyCode(code: ResidencyCode) {
    return this._http.put(this.COMMON_API + '/residencyCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeResidencyCode(code: ResidencyCode) {
    return this._http.delete(this.COMMON_API + '/residencyCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // STUDY MODE
  // ====================================================================================================

  findStudyModes(): Observable<StudyMode[]> {
    console.log('findStudyModes');
    return this._http.get(this.COMMON_API + '/studyModes')
      .map((res: Response) => <StudyMode[]>res.json());
  }

  findStudyModeByCode(code: string): Observable<StudyMode> {
    console.log('findStudyModeByCode');
    return this._http.get(this.COMMON_API + '/studyModes/' + code)
      .map((res: Response) => <StudyMode>res.json());
  }

  saveStudyMode(code: StudyMode) {
    return this._http.post(this.COMMON_API + '/studyModes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// COUNTRY CODES
// ====================================================================================================

  findCountryCodes(): Observable<CountryCode[]> {
    console.log('findCountryCodes()');
    return this._http.get(this.COMMON_API + '/countryCodes')
      .map((res: Response) => <CountryCode[]>res.json());
  }

  findCountryCodeByCode(code: string): Observable<CountryCode> {
    console.log('findCountryCodeByCode');
    return this._http.get(this.COMMON_API + '/countryCodes/' + code)
      .map((res: Response) => <CountryCode>res.json());
  }

// ====================================================================================================
// STATE CODES
// ====================================================================================================

  findStateCodes(): Observable<StateCode[]> {
    console.log('findStateCodes()');
    return this._http.get(this.COMMON_API + '/stateCodes')
      .map((res: Response) => <StateCode[]>res.json());
  }

  findStateCodeByCode(code: string): Observable<StateCode> {
    console.log('findStateCodeByCode');
    return this._http.get(this.COMMON_API + '/stateCodes/' + code)
      .map((res: Response) => <StateCode>res.json());
  }

  // ====================================================================================================
  // TAX CODES
  // ====================================================================================================

  findTaxCodes(): Observable<TaxCode[]> {
    console.log('findTaxCodes');
    return this._http.get(this.COMMON_API + '/taxCodes')
      .map((res: Response) => <TaxCode[]>res.json());
  }

  saveTaxCode(code: TaxCode) {
    return this._http.post(this.COMMON_API + '/taxCodes', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateTaxCode(code: TaxCode) {
    return this._http.put(this.COMMON_API + '/taxCodes/' + code.code, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeTaxCode(code: TaxCode) {
    return this._http.delete(this.COMMON_API + '/taxCodes/' + code.code)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // SECURITY Charge CODES
  // ====================================================================================================

  findSecurityChargeCodes(): Observable<SecurityChargeCode[]> {
    console.log('findSecurityChargeCodes');
    return this._http.get(this.COMMON_API + '/securityChargeCodes')
      .map((res: Response) => <SecurityChargeCode[]>res.json());
  }

  findSecurityChargeCodesByActive(): Observable<SecurityChargeCode[]> {
    console.log('findSecurityChargeCodesActive');
    return this._http.get(this.COMMON_API + '/securityChargeCodesActive')
      .map((res: Response) => <SecurityChargeCode[]>res.json());
  }

  saveSecurityChargeCode(code: SecurityChargeCode) {
    return this._http.post(this.COMMON_API + '/securityChargeCode', JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  updateSecurityChargeCode(code: SecurityChargeCode) {
    return this._http.put(this.COMMON_API + '/securityChargeCode/' + code.id, JSON.stringify(code))
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeSecurityChargeCode(code: SecurityChargeCode) {
    return this._http.delete(this.COMMON_API + '/securityChargeCode/' + code.id)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
}

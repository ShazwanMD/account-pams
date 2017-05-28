import {Injectable} from '@angular/core';
import {Response, Http, Headers, RequestOptions} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {CohortCode} from "../app/common/cohort-codes/cohort-code.interface";
import {FacultyCode} from "../app/common/faculty-codes/faculty-code.interface";
import {CountryCode} from "../app/common/country-codes/country-code.interface";
import {ProgramCode} from "../app/common/program-codes/program-code.interface";
import {StudyMode} from "../app/common/study-modes/study-mode.interface";
import {StateCode} from "../app/common/state-codes/state-code.interface";
import {BankCode} from "../app/common/bank-codes/bank-code.interface";
import {ResidencyCode} from "../app/common/residency-codes/residency-code.interface";

@Injectable()
export class CommonService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // BANK CODES
  // ====================================================================================================

  findBankCodes(): Observable<BankCode[]> {
    console.log("findBankCodes()");
    return this.http.get(environment.endpoint + '/api/common/bankCodes')
      .map((res: Response) => <BankCode[]>res.json());
  }

  findBankCodeByCode(code: string): Observable<BankCode> {
    console.log("findBankCodeByCode");
    return this.http.get(environment.endpoint + '/api/common/bankCodes/' + code)
      .map((res: Response) => <BankCode>res.json());
  }

  saveBankCode(code: BankCode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/common/bankCodes', JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  updateBankCode(code: BankCode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.put(environment.endpoint + '/api/common/bankCodes/' + code.code, JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }
  
  removeBankCode(code: BankCode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/common/bankCodes/' + code.code, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // COHORT CODES
  // ====================================================================================================

  findCohortCodes(): Observable<CohortCode[]> {
      console.log("findCohortCodes");
      return this.http.get(environment.endpoint + '/api/common/cohortCodes')
        .map((res: Response) => <CohortCode[]>res.json());
    }

    findCohortCodeByCode(code: string): Observable<CohortCode> {
      console.log("findCohortCodeByCode");
      return this.http.get(environment.endpoint + '/api/common/cohortCodes/' + code)
        .map((res: Response) => <CohortCode>res.json());
    }
  
  saveCohortCode(code: CohortCode) {
      let headers = new Headers({
        'Content-Type': 'application/json',
        //'Authorization': 'Bearer ' + this.authService.token
      });
      let options = new RequestOptions({headers: headers});
      return this.http.post(environment.endpoint + '/api/common/cohortCodes', JSON.stringify(code), options)
        .flatMap((res: Response) => Observable.of(res.text()));
    }

    removeCohortCode(code: CohortCode) {
      let headers = new Headers({
        'Content-Type': 'application/json',
        //'Authorization': 'Bearer ' + this.authService.token
      });
      let options = new RequestOptions({headers: headers});
      return this.http.delete(environment.endpoint + '/api/common/cohortCodes/' + code.code, options)
        .flatMap((res: Response) => Observable.of(res.text()));
    }

   updateCohortCode(code: CohortCode) {
      let headers = new Headers({
        'Content-Type': 'application/json',
        //'Authorization': 'Bearer ' + this.authService.token
      });
      let options = new RequestOptions({headers: headers});
      return this.http.put(environment.endpoint + '/api/common/cohortCodes/' + code.code, JSON.stringify(code), options)
        .flatMap((res: Response) => Observable.of(res.text()));
    }

  // ====================================================================================================
  // FACULTY CODES
  // ====================================================================================================

  findFacultyCodes(): Observable<FacultyCode[]> {
    console.log("findFacultyCodes");
    return this.http.get(environment.endpoint + '/api/common/facultyCodes')
      .map((res: Response) => <FacultyCode[]>res.json());
  }

  findFacultyCodeByCode(code: string): Observable<FacultyCode> {
    console.log("findFacultyCodeByCode");
    return this.http.get(environment.endpoint + '/api/common/facultyCodes/' + code)
      .map((res: Response) => <FacultyCode>res.json());
  }

  findProgramCodesByFacultyCode(facultyCode: FacultyCode): Observable<ProgramCode[]> {
    console.log("findProgramCodesByFacultyCode");
    return this.http.get(environment.endpoint + '/api/common/facultyCodes/' + facultyCode.code + '/programCodes')
      .map((res: Response) => <ProgramCode[]>res.json());
  }

  saveFacultyCode(code: FacultyCode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/common/facultyCodes', JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }


  // ====================================================================================================
  // PROGRAM CODES
  // ====================================================================================================

  findProgramCodes(): Observable<ProgramCode[]> {
    console.log("findProgramCodes");
    return this.http.get(environment.endpoint + '/api/common/programCodes')
      .map((res: Response) => <ProgramCode[]>res.json());
  }

  findProgramCodeByCode(code: string): Observable<ProgramCode> {
    console.log("findProgramCodeByCode");
    return this.http.get(environment.endpoint + '/api/common/programCodes/' + code)
      .map((res: Response) => <ProgramCode>res.json());
  }

  saveProgramCode(code: ProgramCode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/common/programCodes', JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  removeProgramCode(code: ProgramCode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.delete(environment.endpoint + '/api/common/programCodes/' + code.code, options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

  // ====================================================================================================
  // RESIDENCY CODES
  // ====================================================================================================

  findResidencyCodes(): Observable<ResidencyCode[]> {
      console.log("findResidencyCodes");
      return this.http.get(environment.endpoint + '/api/common/residencyCodes')
        .map((res: Response) => <ResidencyCode[]>res.json());
    }

  saveResidencyCode(code: ResidencyCode) {
      let headers = new Headers({
        'Content-Type': 'application/json',
        //'Authorization': 'Bearer ' + this.authService.token
      });
      let options = new RequestOptions({headers: headers});
      return this.http.post(environment.endpoint + '/api/common/residencyCodes', JSON.stringify(code), options)
        .flatMap((res: Response) => Observable.of(res.text()));
    }

   updateResidencyCode(code: ResidencyCode) {
      let headers = new Headers({
        'Content-Type': 'application/json',
        //'Authorization': 'Bearer ' + this.authService.token
      });
      let options = new RequestOptions({headers: headers});
      return this.http.put(environment.endpoint + '/api/common/residencyCodes/' + code.code, JSON.stringify(code), options)
        .flatMap((res: Response) => Observable.of(res.text()));
    }

  // ====================================================================================================
  // STUDY MODE
  // ====================================================================================================

  findStudyModes(): Observable<StudyMode[]> {
    console.log("findStudyModes");
    return this.http.get(environment.endpoint + '/api/common/studyModes')
      .map((res: Response) => <StudyMode[]>res.json());
  }

  findStudyModeByCode(code: string): Observable<StudyMode> {
    console.log("findStudyModeByCode");
    return this.http.get(environment.endpoint + '/api/common/studyModes/' + code)
      .map((res: Response) => <StudyMode>res.json());
  }

  saveStudyMode(code: StudyMode) {
    let headers = new Headers({
      'Content-Type': 'application/json',
      //'Authorization': 'Bearer ' + this.authService.token
    });
    let options = new RequestOptions({headers: headers});
    return this.http.post(environment.endpoint + '/api/common/studyModes', JSON.stringify(code), options)
      .flatMap((res: Response) => Observable.of(res.text()));
  }

// ====================================================================================================
// COUNTRY CODES
// ====================================================================================================

  findCountryCodes(): Observable<CountryCode[]> {
    console.log("findCountryCodes()");
    return this.http.get(environment.endpoint + '/api/common/countryCodes')
      .map((res: Response) => <CountryCode[]>res.json());
  }

  findCountryCodeByCode(code: string): Observable<CountryCode> {
    console.log("findCountryCodeByCode");
    return this.http.get(environment.endpoint + '/api/common/countryCodes/' + code)
      .map((res: Response) => <CountryCode>res.json());
  }

// ====================================================================================================
// STATE CODES
// ====================================================================================================

  findStateCodes(): Observable<StateCode[]> {
    console.log("findStateCodes()");
    return this.http.get(environment.endpoint + '/api/common/stateCodes')
      .map((res: Response) => <StateCode[]>res.json());
  }

  findStateCodeByCode(code: string): Observable<StateCode> {
    console.log("findStateCodeByCode");
    return this.http.get(environment.endpoint + '/api/common/stateCodes/' + code)
      .map((res: Response) => <StateCode>res.json());
  }

}

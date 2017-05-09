import {Injectable} from '@angular/core';
import {Response, Http, Headers, RequestOptions} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {CohortCode} from "../app/common/cohort-codes/cohort-code.interface";
import {FacultyCode} from "../app/common/faculty-codes/faculty-code.interface";

@Injectable()
export class CommonService {

  constructor(private http: Http,
              private _http: HttpInterceptorService) {
  }


  // ====================================================================================================
  // PROGRAM CODES
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




}

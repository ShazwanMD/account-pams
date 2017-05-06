import {Injectable} from '@angular/core';
import {Response, Http, Headers, RequestOptions} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from "rxjs";
import {environment} from "../environments/environment";
import {CohortCode} from "../app/common/cohort-codes/cohort-code.interface";

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
    return this.http.get(environment.endpoint + '/api/common/programCodes')
      .map((res: Response) => <CohortCode[]>res.json());
  }

  findCohortCodeByCode(code: string): Observable<CohortCode> {
    console.log("findCohortCodeByCode");
    return this.http.get(environment.endpoint + '/api/common/programCodes/' + code)
      .map((res: Response) => <CohortCode>res.json());
  }

}

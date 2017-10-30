import {Injectable} from '@angular/core';
import {Response, RequestOptions, ResponseContentType} from '@angular/http';
import {HttpInterceptorService} from '@covalent/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';

@Injectable()
export class ReportService {

  private REPORT_API: string = environment.endpoint + '/servlet/report?report=';

  constructor(private _http: HttpInterceptorService) {
  }

  // ====================================================================================================
  // REPORT
  // ====================================================================================================

  /*downloadReport(repParam): Observable<String> {
      console.log('downloadReport repParam :'+this.REPORT_API+repParam.repParam);
      
      return this._http.get(this.REPORT_API +repParam.repParam)
      .map((res: Response) => Observable.of(res.text()))
      .catch((error) => this.handleError(error));
    }*/
  
  downloadReport(repParam): Observable<Blob> {
      console.log('downloadReport');
      let options: RequestOptions = new RequestOptions({responseType: ResponseContentType.Blob});
      return this._http.get(this.REPORT_API +repParam.repParam, options)
        .map((res: Response) => {
          return new Blob([res.blob()], {type: "application/pdf"})
        })
        .catch((error) => this.handleError(error));
    }
  
//====================================================================================================
  // PRIVATE METHODS
  // ====================================================================================================

  private handleError(error: Response | any) {
    let body: any = error.json();
    return Observable.throw(body);
  }
}
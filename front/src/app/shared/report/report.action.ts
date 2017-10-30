import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class ReportActions {
    
    static DOWNLOAD_REPORT = '[Report] Download Report';

    downloadReport(repParam) {
      console.log('repParam :'+repParam);
      return {
        type: ReportActions.DOWNLOAD_REPORT,
        payload: {repParam},
      };
    }

    static DOWNLOAD_REPORT_SUCCESS = '[Report] Download Report Success';

    downloadReportSucces(message) {
      console.log('downloadReportSucces');
      return {
        type: ReportActions.DOWNLOAD_REPORT_SUCCESS,
        payload: message,
      };
    }
}
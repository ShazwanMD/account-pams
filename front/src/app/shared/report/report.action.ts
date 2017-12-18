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

    static DOWNLOAD_REPORT_LISTING = '[Report] Download Report Listing';
    
        downloadReportListing(repParam, repParam2) {
          console.log('repParam :'+repParam, 'repParam2 :'+repParam2);
          return {
            type: ReportActions.DOWNLOAD_REPORT_LISTING,
            payload: {repParam, repParam2},
          };
        }
    
        static DOWNLOAD_REPORT_LISTING_SUCCESS = '[Report] Download Report Listing Success';
    
        downloadReportListingSucces(message) {
          console.log('downloadReportListingSucces');
          return {
            type: ReportActions.DOWNLOAD_REPORT_LISTING_SUCCESS,
            payload: message,
          };
        }
}
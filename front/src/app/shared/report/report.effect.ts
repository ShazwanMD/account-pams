import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {Store} from '@ngrx/store';
import {ReportActions} from './report.action';
import {Router, ActivatedRoute} from '@angular/router';
import {AccountService} from '../../../services/account.service';
import {ReportService} from '../../../services/report.service';
import {ApplicationContextActions} from '../../application-context.action';
import {Observable} from 'rxjs/Observable';
import {ApplicationContextState} from '../../application-context.reducer';

@Injectable()
export class ReportEffects {
  constructor(private actions$: Actions,
              private router: Router,
              private reportActions: ReportActions,
              private reportService: ReportService,
              private ctxActions: ApplicationContextActions) {
      
  }
  
  // ====================================================================================================
  // DOWNLOAD REPORT
  // ====================================================================================================
  @Effect() downloadReport = this.actions$
  .ofType(ReportActions.DOWNLOAD_REPORT)
  .map((action) => action.payload)
  .switchMap((repParam) => this.reportService.downloadReport(repParam))
  .map((file) => {
      let url = URL.createObjectURL(file);
      window.open(url);
    }).ignoreElements();

}
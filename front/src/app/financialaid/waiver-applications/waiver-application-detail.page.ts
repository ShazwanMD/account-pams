import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialogRef, MdDialog} from '@angular/material';
import {FinancialaidModuleState} from '../index';
import {WaiverApplicationActions} from './waiver-application.action';
import {WaiverApplication} from '../../shared/model/financialaid/waiver-application.interface';

@Component({
  selector: 'pams-waiver-application-detail',
  templateUrl: './waiver-application-detail.page.html',
})
export class WaiverApplicationDetailPage implements OnInit {

  private WAIVER_APPLICATION: string[] = 'financialaidModuleState.waiverApplication'.split('.');

  private waiverApplication$: Observable<WaiverApplication>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<FinancialaidModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: WaiverApplicationActions) {
    this.waiverApplication$ = this.store.select(...this.WAIVER_APPLICATION);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findWaiverApplicationByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/financialaid/waiver-applications']);
  }
}

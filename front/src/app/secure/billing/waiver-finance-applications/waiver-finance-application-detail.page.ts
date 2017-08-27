import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {WaiverApplication} from '../../../shared/model/financialaid/waiver-application.interface';
import { WaiverFinanceApplication } from "../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";
import { BillingModuleState } from "../index";

@Component({
  selector: 'pams-waiver-finance-application-detail',
  templateUrl: './waiver-finance-application-detail.page.html',
})
export class WaiverFinanceApplicationDetailPage implements OnInit {

  private WAIVER_FINANCE_APPLICATION: string[] = 'billingModuleState.waiverFinanceApplication'.split('.');

  private waiverFinanceApplication$: Observable<WaiverFinanceApplication>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: WaiverFinanceApplicationActions) {
    this.waiverFinanceApplication$ = this.store.select(...this.WAIVER_FINANCE_APPLICATION);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findWaiverFinanceApplicationByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/waiver-finance-applications']);
  }
}


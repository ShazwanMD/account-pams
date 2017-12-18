import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Invoice } from '../../../shared/model/billing/invoice.interface';
import { Store } from '@ngrx/store';
import { BillingModuleState } from '../../billing/index';
import { InvoiceActions } from '../../billing/invoices/invoice.action';
import { ReportActions } from '../../../shared/report/report.action';

@Component({
  selector: 'pams-listing-invoice-center-page',
  templateUrl: './listing-invoice-center.page.html',
})

export class ListingInvoiceCenterPage implements OnInit {

  @Input() invoice: Invoice;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private reportActions: ReportActions,
    private actions: InvoiceActions) {
}

  ngOnInit(): void {
    this.route.params.subscribe(() => {
    });
  }

  downloadReportListing(reportId,parameterReport, parameterReport2:Invoice): void {
    let repParam = reportId+'&Start_date='+ parameterReport.issuedDate;  
    let repParam2 = reportId+'&End_date='+ parameterReport2.issuedDate;  
    this.store.dispatch(this.reportActions.downloadReportListing(repParam, repParam2));
  }
}

import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Invoice } from '../../../shared/model/billing/invoice.interface';
import { Store } from '@ngrx/store';
import { BillingModuleState } from '../../billing/index';
import { InvoiceActions } from '../../billing/invoices/invoice.action';
import { ReportActions } from '../../../shared/report/report.action';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DateRange } from '../../../shared/model/billing/date-range.interface';

@Component({
  selector: 'pams-listing-invoice-center-page',
  templateUrl: './listing-invoice-center.page.html',
})

export class ListingInvoiceCenterPage implements OnInit {

  @Input() invoice: Invoice;
  private editForm: FormGroup;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private reportActions: ReportActions,
    private formBuilder: FormBuilder,
    private actions: InvoiceActions) {
}

ngOnInit(): void {
  this.editForm = this.formBuilder.group(<DateRange>{
    Start_date: undefined,
    End_date: undefined,
  });
}

  downloadReport(reportId: Invoice,parameterReport:DateRange): void {
    let repParam = reportId+'&Start_date=' +  this.editForm.value.Start_date;

    let repParam2 = reportId+'&End_date='+ this.editForm.value.End_date;
    console.log("End Date" + this.editForm.value.End_date);
    this.store.dispatch(this.reportActions.downloadReport(repParam));
  }
}

import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Receipt } from '../../../shared/model/billing/receipt.interface';
import { Store } from '@ngrx/store';
import { BillingModuleState } from '../../billing/index';
import { ReportActions } from '../../../shared/report/report.action';
import { ReceiptActions } from '../../billing/receipts/receipt.action';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Observable} from 'rxjs';
import { DateRange } from '../../../shared/model/billing/date-range.interface';

@Component({
  selector: 'pams-listing-receipt-center-page',
  templateUrl: './listing-receipt-center.page.html',
})

export class ListingReceiptCenterPage implements OnInit {

  @Input() receipt: Receipt;

  private editForm: FormGroup;
  
  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private formBuilder: FormBuilder,
    private reportActions: ReportActions,
    private actions: ReceiptActions) {
  }
  
    ngOnInit(): void {
      this.editForm = this.formBuilder.group(<DateRange>{
        Start_date: undefined,
        End_date: undefined,
      });
    }
  

downloadReportListing(reportId: Receipt,parameterReport:DateRange): void {
  let repParam = reportId+'&Start_date=';  
  let repParam2 = reportId+'&End_date=';  
  this.store.dispatch(this.reportActions.downloadReportListing(repParam, repParam2));
}

}

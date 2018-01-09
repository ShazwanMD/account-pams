import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { Invoice } from '../../../shared/model/billing/invoice.interface';
import { Store } from '@ngrx/store';
import { BillingModuleState } from '../../billing/index';
import { InvoiceActions } from '../../billing/invoices/invoice.action';
import { ReportActions } from '../../../shared/report/report.action';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DateRange } from '../../../shared/model/billing/date-range.interface';
import { ProgramLevel } from '../../../shared/model/common/program-level.interface';
import { ProgramCode } from '../../../shared/model/common/program-code.interface';

@Component({
  selector: 'pams-listing-invoice-center-page',
  templateUrl: './listing-invoice-center.page.html',
})

export class ListingInvoiceCenterPage implements OnInit {

  @Input() invoice: Invoice;
  @Input() programCode: ProgramCode;
  @Input() programLevel: ProgramLevel;
  private editForm: FormGroup;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private reportActions: ReportActions,
    private formBuilder: FormBuilder,
    private actions: InvoiceActions) {
}

ngOnInit(): void {
  this.editForm = this.formBuilder.group({
    Start_date: undefined,
    End_date: undefined,
    programLevel: [<ProgramLevel>{}],
    programCode: [<ProgramCode>{}],
  });
}

  downloadReport(editForm: FormGroup): void {
    let repParam =editForm +'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date
    +'&Program_code='+ this.editForm.value.programCode.description +'&Study_level='+ this.editForm.value.programLevel.code;
    console.log(editForm +'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date
    +'&Program_code='+ this.editForm.value.programCode.description +'&Study_level='+ this.editForm.value.programLevel.code);
    this.store.dispatch(this.reportActions.downloadReport(repParam));
  } 
}

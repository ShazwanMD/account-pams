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
import { ProgramLevel } from '../../../shared/model/common/program-level.interface';
import { ProgramCode } from '../../../shared/model/common/program-code.interface';
import { ReceiptStatusType } from '../../../shared/model/common/receipt-status-type';

@Component({
  selector: 'pams-listing-receipt-center-page',
  templateUrl: './listing-receipt-center.page.html',
})

export class ListingReceiptCenterPage implements OnInit {

  @Input() programCode: ProgramCode;
  @Input() programLevel: ProgramLevel;
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
    this.editForm = this.formBuilder.group({
      Start_date: undefined,
      End_date: undefined,
      programLevel: [<ProgramLevel>{}],
      programCode: [<ProgramCode>{}],
      receiptStatusType: ReceiptStatusType,
    });
    }
  
    downloadReport(editForm: FormGroup): void {
      let repParam = editForm+'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date
      +'&Program_code='+ this.editForm.value.programCode.description +'&Study_level='+ this.editForm.value.programLevel.code
      +'&Status='+ this.editForm.value.receiptStatusType;
      this.store.dispatch(this.reportActions.downloadReport(repParam));
      }

}
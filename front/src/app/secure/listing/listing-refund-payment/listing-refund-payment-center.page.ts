import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { AdvancePayment } from '../../../shared/model/billing/advance-payment.interface';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ReportActions } from '../../../shared/report/report.action';
import { BillingModuleState } from '../../billing/index';
import { Store } from '@ngrx/store';
import { ProgramLevel } from '../../../shared/model/common/program-level.interface';
import { ProgramCode } from '../../../shared/model/common/program-code.interface';
import { RefundPayment } from '../../../shared/model/billing/refund-payment.interface';
import { RefundPaymentStatusType } from '../../../shared/model/common/refund-status-type';

@Component({
  selector: 'pams-listing-refund-payment-center-page',
  templateUrl: './listing-refund-payment-center.page.html',
})

export class ListingRefundPaymentCenterPage implements OnInit {

  @Input() refundPayment: RefundPayment;
  @Input() programCode: ProgramCode;
  @Input() programLevel: ProgramLevel;
  private editForm: FormGroup;

  constructor(private router: Router,
    private route: ActivatedRoute,
    private store: Store<BillingModuleState>,
    private formBuilder: FormBuilder,
    private reportActions: ReportActions) {
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      Start_date: undefined,
      End_date: undefined,
      programLevel: [<ProgramLevel>{}],
      programCode: [<ProgramCode>{}],
      refundPaymentStatusType: RefundPaymentStatusType,
    });
  }
downloadReport(editForm: FormGroup): void {
let repParam = editForm+'&Start_date=' +  this.editForm.value.Start_date +'&End_date='+ this.editForm.value.End_date
+'&Program_code='+ this.editForm.value.programCode.description +'&Study_level='+ this.editForm.value.programLevel.code
+'&Status='+ this.editForm.value.refundPaymentStatusType;
this.store.dispatch(this.reportActions.downloadReport(repParam));
}
}

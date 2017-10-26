import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {BillingModuleState} from '../../index';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { RefundPaymentActions } from "../refund-payment.action";
import { RefundPayment } from "../../../../shared/model/billing/refund-payment.interface";
import { AdvancePayment } from "../../../../shared/model/billing/advance-payment.interface";

@Component({
  selector: 'pams-refund-payment-task-creator',
  templateUrl: './refund-payment-task-creator.dialog.html',
})

export class RefundPaymentTaskCreatorDialog implements OnInit {
 private _advancePayment: AdvancePayment;

  private createForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private store: Store<BillingModuleState>,
              private actions: RefundPaymentActions,
              private dialog: MdDialogRef<RefundPaymentTaskCreatorDialog>) {
  }

  set advancePayment( value: AdvancePayment ) {
    this._advancePayment = value;
}

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      id: [null],
      referenceNo: [''],
      sourceNo: [''],
      auditNo: [''],
      description: [''],
      amount: [0],
      issuedDate: [undefined, Validators.required],
      advancePayment: <AdvancePayment>{},
    });
  }

  save( refundPayment: RefundPayment, isValid: boolean ) {
    if(isValid)
      refundPayment.advancePayment = this._advancePayment;
    
    if(isValid)
    this.store.dispatch( this.actions.startRefundPaymentTask(refundPayment, this._advancePayment))
    this.dialog.close();
}
}

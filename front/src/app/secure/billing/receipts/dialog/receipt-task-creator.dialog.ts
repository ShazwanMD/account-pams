import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {ReceiptActions} from '../receipt.action';
import {BillingModuleState} from '../../index';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptType} from '../../../../shared/model/billing/receipt-type.enum';
import {PaymentMethod} from '../../../../shared/model/common/payment-method.enum';

@Component({
  selector: 'pams-receipt-task-creator',
  templateUrl: './receipt-task-creator.dialog.html',
})

export class ReceiptTaskCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<ReceiptTaskCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<Receipt>{
      id: null,
      referenceNo: '',
      receivedDate: undefined,
      receiptNo: '',
      sourceNo: '',
      auditNo: '',
      description: '',
      totalApplied: 0,
      totalReceived: 0,
      totalAmount: 0,
      account: <Account>{},
      receiptType: <ReceiptType>{},
      paymentMethod: <PaymentMethod>{},
    });
  }

  save(receipt: Receipt, isValid: boolean) {
    this.store.dispatch(this.actions.startReceiptTask(receipt));
    this.dialog.close();
  }
}

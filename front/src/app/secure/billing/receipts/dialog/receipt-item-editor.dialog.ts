import { AmountValidation } from './../../../../shared/component/amount-validation';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-receipt-item-editor',
  templateUrl: './receipt-item-editor.dialog.html',
})

export class ReceiptItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private edit: boolean = false;
  private _receipt: Receipt;
  private _receiptItem: ReceiptItem;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<ReceiptItemEditorDialog>) {
  }

  set receipt(value: Receipt) {
    this._receipt = value;
  }

  set receiptItem(value: ReceiptItem) {
    this._receiptItem = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: [undefined],
      description: [''],
      totalAmount: [0],
      adjustedAmount: [0],
      appliedAmount: [0,Validators.required],
      dueAmount: [0,Validators.required],
      unit: [0],
      price: [0],
      totalPaid:this._receipt.totalPayment,
      chargeCode: <ChargeCode>{},
      invoice: <Invoice>{},
    },{
      validator: AmountValidation.CheckAmount // validation method
   });
    if (this.edit) this.editForm.patchValue(this._receiptItem);
  }

  submit(item: ReceiptItem, isValid: boolean) {
    this.store.dispatch(this.actions.updateReceiptItem(this._receipt, item));
    this.dialog.close();
  }
}

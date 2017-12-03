import { AmountValidation } from './../../../../shared/component/amount-validation';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";

@Component({
  selector: 'pams-waiver-item-editor',
  templateUrl: './waiver-item-editor.dialog.html',
})

export class WaiverItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private edit: boolean = false;
  private _waiverFinanceApplication: WaiverFinanceApplication;
  private _waiverItem: WaiverItem;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<WaiverItemEditorDialog>) {
  }

  set waiverFinanceApplication(value: WaiverFinanceApplication) {
    this._waiverFinanceApplication = value;
  }

  set waiverItem(value: WaiverItem) {
    this._waiverItem = value;
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
      chargeCode: <ChargeCode>{},
      invoice: <Invoice>{},
    },{
       validator: AmountValidation.CheckAmountWaiver // validation method
   });
    if (this.edit) this.editForm.patchValue(this._waiverItem);
  }

  submit(item: WaiverItem, isValid: boolean) {
    this.store.dispatch(this.actions.updateWaiverItems(this._waiverFinanceApplication, item));
    this.dialog.close();
  }
}

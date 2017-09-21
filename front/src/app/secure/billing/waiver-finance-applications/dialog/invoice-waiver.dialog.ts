import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {ReceiptInvoice} from '../../../../shared/model/billing/receipt-invoice.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";

@Component({
  selector: 'pams-invoice-waiver',
  templateUrl: './invoice-waiver.dialog.html',
})

export class WaiverReceiptDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _waiverFinanceApplication: WaiverFinanceApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<WaiverReceiptDialog>) {
  }

  set waiverFinanceApplication(value: WaiverFinanceApplication) {
    this._waiverFinanceApplication = value;
    this.edit = true;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      //referenceNo: '',
      invoice: <Invoice>{},
      waiverFinanceApplication: <WaiverFinanceApplication>{},
    });
    if (this.edit) this.createForm.patchValue(this._waiverFinanceApplication);
  }

  save(waiverFinanceApplication: WaiverFinanceApplication, isValid: boolean) {
    
      console.log("receiptNo" + this._waiverFinanceApplication.referenceNo);
      console.log("Invc ref No" + this.createForm.get('invoice').value.referenceNo);
      
    this.store.dispatch(this.actions.addWaiverInvoice(this._waiverFinanceApplication, this.createForm.get('invoice').value));
    this.dialog.close();
  }
}

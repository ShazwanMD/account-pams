import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
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
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";

@Component({
  selector: 'pams-debit-note-waiver',
  templateUrl: './debit-note-waiver.dialog.html',
})

export class DebitNoteWaiverDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _waiverFinanceApplication: WaiverFinanceApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private action: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<DebitNoteWaiverDialog>) {
  }

  set waiverFinanceApplication(value: WaiverFinanceApplication) {
    this._waiverFinanceApplication = value;
    this.edit = true;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      //referenceNo: '',
      debitNote: <DebitNote>{},
      waiverFinanceApplication: <WaiverFinanceApplication>{},
    });
    if (this.edit) this.createForm.patchValue(this._waiverFinanceApplication);
  }

  save(receipt: Receipt, isValid: boolean) {
    
      console.log("receiptNo" + this._waiverFinanceApplication.referenceNo);
      console.log("Debit Note ref No" + this.createForm.get('debitNote').value.referenceNo);
      
    this.store.dispatch(this.action.addWaiverDebitNote(this._waiverFinanceApplication, this.createForm.get('debitNote').value));
    this.dialog.close();
  }
}

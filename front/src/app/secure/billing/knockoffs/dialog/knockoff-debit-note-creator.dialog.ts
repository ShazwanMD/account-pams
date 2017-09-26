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
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { KnockoffActions } from "../knockoff.action";
import { AccountCharge } from '../../../../shared/model/account/account-charge.interface';

@Component({
  selector: 'pams-knockoff-debit-note-creator',
  templateUrl: './knockoff-debit-note-creator.dialog.html',
})

export class DebitNoteKnockoffDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _knockoff: Knockoff;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<DebitNoteKnockoffDialog>) {
  }

  set knockoff(value: Knockoff) {
    this._knockoff = value;
    this.edit = true;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      //referenceNo: '',
      debitNote: <DebitNote>{},
      knockoff: <Knockoff>{},
    });
    if (this.edit) this.createForm.patchValue(this._knockoff);
  }

  save(knockoff: Knockoff, isValid: boolean) {
      console.log("Knockoff ref No" + this._knockoff.referenceNo);
      console.log("Debit Note ref No" + this.createForm.get('debitNote').value.referenceNo);
      
    this.store.dispatch(this.actions.addKnockoffDebitNote(this._knockoff, this.createForm.get('debitNote').value));
    this.dialog.close();
  }
}

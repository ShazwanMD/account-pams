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

@Component({
  selector: 'pams-knockoff-invoice-creator',
  templateUrl: './knockoff-invoice-creator.dialog.html',
})

export class InvoiceKnockoffDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _knockoff: Knockoff;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<InvoiceKnockoffDialog>) {
  }

  set knockoff(value: Knockoff) {
    this._knockoff = value;
    this.edit = true;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      //referenceNo: '',
      invoice: <Invoice>{},
      knockoff: <Knockoff>{},
    });
    if (this.edit) this.createForm.patchValue(this._knockoff);
  }

  save(knockoff: Knockoff, isValid: boolean) {
      console.log("Knockoff ref No" + this._knockoff.referenceNo);
      console.log("Invc ref No" + this.createForm.get('invoice').value.referenceNo);
      
    this.store.dispatch(this.actions.addKnockoffInvoice(this._knockoff, this.createForm.get('invoice').value));
    this.dialog.close();
  }
}

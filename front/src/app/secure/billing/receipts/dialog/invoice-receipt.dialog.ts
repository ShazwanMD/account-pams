import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {ReceiptInvoice} from '../../../../shared/model/billing/receipt-invoice.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-receipt',
  templateUrl: './invoice-receipt.dialog.html',
})

export class InvoiceReceiptDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _receipt: Receipt;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<InvoiceReceiptDialog>) {
  }

  set receipt(value: Receipt) {
    this._receipt = value;
    this.edit = true;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      //referenceNo: '',
      invoice: <Invoice>{},
      receipt: <Receipt>{},
    });
    if (this.edit) this.createForm.patchValue(this._receipt);
  }

  save(receipt: Receipt, isValid: boolean) {
    
      console.log("receiptNo" + this._receipt.referenceNo);
      console.log("Invc ref No" + this.createForm.get('invoice').value.referenceNo);
      
    this.store.dispatch(this.actions.addReceiptInvoiceItems(this._receipt, this.createForm.get('invoice').value));
    this.dialog.close();
  }
}

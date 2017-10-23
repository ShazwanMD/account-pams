import { Component, OnInit, ViewContainerRef, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { Store } from '@ngrx/store';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { ReceiptItem } from '../../../../shared/model/billing/receipt-item.interface';
import { ReceiptInvoice } from '../../../../shared/model/billing/receipt-invoice.interface';
import { ChargeCode } from '../../../../shared/model/account/charge-code.interface';
import { Invoice } from '../../../../shared/model/billing/invoice.interface';
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { Observable } from "rxjs/Observable";
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";
import { ReceiptActions } from '../receipt.action';

@Component({
  selector: 'pams-receipt-item-list-only',
  templateUrl: './receipt-item-list-only.component.html',
})

export class ReceiptItemListOnly implements OnInit {

  @Input() receiptItem: ReceiptItem[];



  constructor(private router: Router,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private viewContainerRef: ViewContainerRef,
    private store: Store<BillingModuleState>,
    private actions: ReceiptActions) {

  }

  private columns: any[] = [
    { name: 'invoice.referenceNo', label: 'Invoice' },
    { name: 'accountCharge.referenceNo', label: 'Account Charge' },
    { name: 'debitNote.referenceNo', label: 'Debit Note' },
    { name: 'chargeCode.code', label: 'Charge Code' },
    { name: 'description', label: 'Charge Code Description' },
    { name: 'dueAmount', label: 'Amount' },
    { name: 'appliedAmount', label: 'Received Amount' },
    { name: 'totalAmount', label: 'Balance Amount' },
    { name: 'action', label: '' },
  ];

  ngOnInit(): void {
    //this.store.dispatch(this.actions.findInvoiceKnockoffItems(this._knockoff, this._invoice));
  }

}

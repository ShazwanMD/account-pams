import {Component, Input, OnInit, ViewContainerRef, Output, EventEmitter} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {BillingModuleState} from '../../../billing/index';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';
import { FormGroup } from "@angular/forms";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";
import { ReceiptItem } from "../../../../shared/model/billing/receipt-item.interface";
import { ReceiptActions } from "../../../billing/receipts/receipt.action";

@Component({
  selector: 'pams-receipt-item',
  templateUrl: './receipt-item.dialog.html',
})
export class ReceiptItemDialog implements OnInit {

  private _accountActivity: AccountActivity;

  private receipt: Receipt;

  private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
  private RECEIPT: string[] = 'billingModuleState.receipt'.split('.');
  private receiptItems$: Observable<ReceiptItem[]>;
  private receipt$: Observable<Receipt>;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: ReceiptActions) {
      this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
      this.receipt$ = this.store.select(...this.RECEIPT);
  }
  
  set activity(value: AccountActivity) {
      this._accountActivity = value;
    }

  ngOnInit(): void {
      console.log("sourceNo" + this._accountActivity.sourceNo);
      
      this.receipt$.subscribe((receipt: Receipt) => {
          receipt.referenceNo = this._accountActivity.sourceNo;

         console.log("receiptNo" + receipt.description);
        if (receipt.referenceNo) this.store.dispatch(this.actions.findReceiptItems(this.receipt));
      });
  }


}

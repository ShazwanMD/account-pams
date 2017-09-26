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
import { Observable } from "rxjs/Observable";
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";
import { AccountCharge } from '../../../../shared/model/account/account-charge.interface';

@Component({
  selector: 'pams-knockoff-account-charge-item',
  templateUrl: './knockoff-account-charge-item.dialog.html',
})

export class KnockoffAccountChargeItemDialog implements OnInit {

  private _knockoff: Knockoff;
  private _accountCharge: AccountCharge;
  showHide: boolean;

  private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
  private knockoffItem$: Observable<KnockoffItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<KnockoffAccountChargeItemDialog>) {
      this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
      this.showHide = true;
  }

  set knockoff(value: Knockoff) {
    this._knockoff = value;
  }
  
  set accountCharge(value: AccountCharge) {
      this._accountCharge = value;
    }

  ngOnInit(): void {
      this.store.dispatch(this.actions.findKnockoffItemsByAccountCharge(this._knockoff, this._accountCharge));
  }

  Apply(): void {
      this.showHide = !this.showHide;
      this.store.dispatch( this.actions.itemToKnockoffAccountChargeItem(this._accountCharge, this._knockoff));
  }
}

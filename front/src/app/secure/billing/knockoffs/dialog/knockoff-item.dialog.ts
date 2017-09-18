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

@Component({
  selector: 'pams-knockoff-item',
  templateUrl: './knockoff-item.dialog.html',
})

export class KnockoffItemDialog implements OnInit {

  private _knockoff: Knockoff;
  private _invoice: Invoice;
  showHide: boolean;

  private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
  private knockoffItem$: Observable<KnockoffItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<KnockoffItemDialog>) {
      this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
      this.showHide = true;
  }

  set knockoff(value: Knockoff) {
    this._knockoff = value;
  }
  
  set invoice(value: Invoice) {
      this._invoice = value;
    }

  ngOnInit(): void {
      this.store.dispatch(this.actions.findKnockoffItemsByInvoice(this._knockoff, this._invoice));
  }

  Apply(): void {
      this.showHide = !this.showHide;
      this.store.dispatch( this.actions.itemToKnockoffItem(this._invoice, this._knockoff));
  }
}

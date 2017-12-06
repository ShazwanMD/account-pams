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
import { DebitNote } from "../../../../shared/model/billing/debit-note.interface";

@Component({
  selector: 'pams-debit-knockoff-applicator',
  templateUrl: './debit-knockoff-applicator.dialog.html',
})

export class DebitKnockoffItemDialog implements OnInit {

  private _knockoff: Knockoff;
  private _debitNote: DebitNote;

  private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
  private knockoffItem$: Observable<KnockoffItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<DebitKnockoffItemDialog>) {
      this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
  }

  set knockoff(value: Knockoff) {
    this._knockoff = value;
  }
  
  set debitNote(value: DebitNote) {
      this._debitNote = value;
    }

  private columns: any[] = [
                            { name: 'chargeCode.code', label: 'Charge Code' },
                            { name: 'description', label: 'Charge Code Description' },
                            { name: 'dueAmount', label: 'Amount' },
                            { name: 'appliedAmount', label: 'Received Amount' },
                            { name: 'totalAmount', label: 'Balance Amount' },
                            { name: 'action', label: '' },
                        ];
    
  ngOnInit(): void {
      this.store.dispatch(this.actions.findDebitKnockoffItems(this._knockoff, this._debitNote));
  }

  Apply(): void {
      this.store.dispatch( this.actions.debitToKnockoffItem(this._debitNote, this._knockoff));
  }
}

import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {Component, Input, OnInit, ViewContainerRef, Output, EventEmitter} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {InvoiceActions} from '../../../billing/invoices/invoice.action';
import {BillingModuleState} from '../../../billing/index';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';
import { FormGroup } from "@angular/forms";
import { DebitNoteActions } from "../../../billing/debit-notes/debit-note.action";

@Component({
  selector: 'pams-debit-note-item',
  templateUrl: './debit-note-item.dialog.html',
})
export class DebitNoteItemDialog implements OnInit {

  private _accountActivity: AccountActivity;

  private DEBIT_NOTES: string[] = 'billingModuleState.debitNote'.split('.');
  private debitNotes$: Observable<DebitNote>;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: DebitNoteActions) {
      this.debitNotes$ = this.store.select(...this.DEBIT_NOTES);
  }
  
  set activity(value: AccountActivity) {
      this._accountActivity = value;
    }

  ngOnInit(): void {
      console.log("sourceNo" + this._accountActivity.sourceNo);

      this.route.params.subscribe((params: { referenceNo: string }) => {
          let referenceNo: string = this._accountActivity.sourceNo;
      
          console.log("debitNo" + referenceNo);
          this.store.dispatch(this.actions.findDebitNoteByReferenceNo(referenceNo));
        });
  }


}

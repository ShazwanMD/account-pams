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
import { CreditNote } from "../../../../shared/model/billing/credit-note.interface";
import { CreditNoteActions } from "../../../billing/credit-notes/credit-note.action";

@Component({
  selector: 'pams-credit-note-item',
  templateUrl: './credit-note-item.dialog.html',
})
export class CreditNoteItemDialog implements OnInit {

  private _accountActivity: AccountActivity;

  private CREDIT_NOTES: string[] = 'billingModuleState.creditNote'.split('.');
  private creditNotes$: Observable<CreditNote>;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: CreditNoteActions) {
      this.creditNotes$ = this.store.select(...this.CREDIT_NOTES);
  }
  
  set activity(value: AccountActivity) {
      this._accountActivity = value;
    }

  ngOnInit(): void {
      console.log("sourceNo" + this._accountActivity.sourceNo);

      this.route.params.subscribe((params: { referenceNo: string }) => {
          let referenceNo: string = this._accountActivity.sourceNo;
      
          console.log("creditNo" + referenceNo);
          this.store.dispatch(this.actions.findCreditNoteByReferenceNo(referenceNo));
        });
  }


}

import { DebitNoteActions } from './../../debit-notes/debit-note.action';
import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {BillingModuleState} from '../../index';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import { InvoiceActions } from "../../invoices/invoice.action";
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { AccountModuleState } from "../../../account/index";
import { AccountActions } from "../../../account/accounts/account.action";

@Component({
  selector: 'pams-debit-note-select-unpaid',
  templateUrl: './debit-note-select-unpaid.component.html',
})
export class DebitNoteUnpaidSelectComponent implements OnInit {

  private DEBIT_NOTES = 'billingModuleState.debitNotes'.split('.');
  private debitNotes$: Observable<DebitNote[]>;
  private selected: DebitNote;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  @Input() preSelected: DebitNote;
  @Input() receipt: Receipt;

  constructor(private store: Store<BillingModuleState>,
              private actions: DebitNoteActions) {
    this.debitNotes$ = this.store.select(...this.DEBIT_NOTES);
  }

  ngOnInit() {
    if (this.preSelected) {
      this.debitNotes$.subscribe((debitNotes) => {
        this.selected = debitNotes.find((debitNote) => debitNote.id == this.preSelected.id);
      });
    }
  }

  selectChangeEvent(event: DebitNote) {
    this.innerFormControl.setValue(event, {emitEvent: false});
    this.selected = event;
  }
}


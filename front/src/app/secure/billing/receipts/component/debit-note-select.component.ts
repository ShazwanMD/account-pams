import { DebitNoteActions } from './../../debit-notes/debit-note.action';
import { BillingModuleState } from './../../index';
import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { AccountModuleState } from "../../../account/index";
import { AccountActions } from "../../../account/accounts/account.action";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'pams-debit-note-select',
  templateUrl: './debit-note-select.component.html',
  styleUrls: ['./debit-note-select.scss'],
})
export class DebitNoteSelectComponent implements OnInit {

  private DEBIT_NOTES: string[] = 'billingModuleState.debitNotes'.split('.');
  private debitNotes$: Observable<DebitNote[]>

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<BillingModuleState>,
    private route: ActivatedRoute,
    private actions: DebitNoteActions) {
    this.debitNotes$ = this.store.select(...this.DEBIT_NOTES);
}
  ngOnInit(): void {
    this.route.params.subscribe((params: { debitNote: string }) => {
      let debitNote: string = params.debitNote;
      this.store.dispatch(this.actions.findDebitNotes(debitNote));
    });
  }
  selectChangeEvent(event: DebitNote) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


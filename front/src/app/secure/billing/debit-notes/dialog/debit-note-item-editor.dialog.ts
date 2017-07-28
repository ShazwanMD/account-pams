import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {DebitNoteItem} from '../../../../shared/model/billing/debit-note-item.interface';
import {DebitNote} from '../../../../shared/model/billing/debit-note.interface';
import {DebitNoteActions} from '../debit-note.action';

@Component({
  selector: 'pams-debit-note-item-editor',
  templateUrl: './debit-note-item-editor.dialog.html',
})

export class DebitNoteItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _debitNoteItem: DebitNoteItem;
  private _debitNote: DebitNote;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: DebitNoteActions,
              private dialog: MdDialogRef<DebitNoteItemEditorDialog>) {
  }

  set debitNoteItem(value: DebitNoteItem) {
    this._debitNoteItem = value;
    this.edit = true;
  }

  set debitNote(value: DebitNote) {
    this._debitNote = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<DebitNoteItem>{
      id: null,
      description: '',
      amount: 0,
      chargeCode: <ChargeCode>{},
      debitNoteItemDate: undefined,
    });
    if (this.edit) this.editForm.patchValue(this._debitNoteItem);
  }

  submit(item: DebitNoteItem, isValid: boolean) {
    item.description = item.chargeCode.description;
    if (!item.id) this.store.dispatch(this.actions.addDebitNoteItem(this._debitNote, item));
    else  this.store.dispatch(this.actions.updateDebitNoteItem(this._debitNote, item));
    this.dialog.close();
  }
}

import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ChargeCode} from '../../../account/charge-codes/charge-code.interface';
import {MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import { DebitNoteItem } from '../debit-note-item.interface';
import { DebitNote } from '../debit-note.interface';
import { DebitNoteActions } from '../debit-note.action';

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

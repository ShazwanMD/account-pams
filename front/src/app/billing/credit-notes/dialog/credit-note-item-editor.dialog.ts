import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ChargeCode} from "../../../account/charge-codes/charge-code.interface";
import {MdDialogRef} from "@angular/material";
import {BillingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import { CreditNoteItem } from "../credit-note-item.interface";
import { CreditNote } from "../credit-note.interface";
import { CreditNoteActions } from "../credit-note.action";


@Component({
  selector: 'pams-credit-note-item-editor',
  templateUrl: './credit-note-item-editor.dialog.html',
})

export class CreditNoteItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _creditNoteItem: CreditNoteItem;
  private _creditNote: CreditNote;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions:CreditNoteActions,
              private dialog: MdDialogRef<CreditNoteItemEditorDialog>) {
  }

  set creditNoteItem(value: CreditNoteItem) {
    this._creditNoteItem = value;
    this.edit = true;
  }

  set creditNote(value: CreditNote) {
    this._creditNote = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<CreditNoteItem>{
      id: null,
      description: '',
      amount: 0,
      creditNoteItemDate: undefined,
      chargeCode: <ChargeCode>{},
    });
    if (this.edit) this.editForm.patchValue(this._creditNoteItem);
  }

  submit(item: CreditNoteItem, isValid: boolean) {
 item.description = item.chargeCode.description;
    if (!item.id) this.store.dispatch(this.actions.addCreditNoteItem(this._creditNote, item));
    else  this.store.dispatch(this.actions.updateCreditNoteItem(this._creditNote, item));
    this.dialog.close();
  }
}

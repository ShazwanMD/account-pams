import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {CreditNoteItem} from '../../../../shared/model/billing/credit-note-item.interface';
import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';
import {CreditNoteActions} from '../credit-note.action';
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";

@Component({
  selector: 'pams-credit-note-item-editor',
  templateUrl: './credit-note-item-editor.dialog.html',
})

export class CreditNoteItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private chargeCodeForm: FormGroup;
  private _creditNoteItem: CreditNoteItem;
  private _creditNote: CreditNote;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: CreditNoteActions,
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
    this.chargeCodeForm = this.formBuilder.group({
       invoiceItem: <InvoiceItem>{}, 
    });
    this.editForm = this.formBuilder.group(<CreditNoteItem>{
      id: null,
      description: '',
      amount: 0,
      balanceAmount: 0,
      creditNoteItemDate: undefined,
      chargeCode: <ChargeCode>{},
      creditNote: <CreditNote>{},
    });
    if (this.edit) this.editForm.patchValue(this._creditNoteItem);
  }

  submit(item: CreditNoteItem, isValid: boolean) {
    item.creditNoteItemDate = this._creditNote.creditNoteDate;
    
    if (!item.id) this.store.dispatch(this.actions.addCreditNoteItem(this._creditNote, item));
    else  this.store.dispatch(this.actions.updateCreditNoteItem(this._creditNote, item));
    this.dialog.close();
  }
  
  show() {
      this.editForm.patchValue( { description: this.chargeCodeForm.get('invoiceItem').value.description } );
      this.editForm.patchValue( { amount: this.chargeCodeForm.get('invoiceItem').value.balanceAmount } );
      this.editForm.patchValue( { chargeCode: this.chargeCodeForm.get('invoiceItem').value.chargeCode } );
  }
}

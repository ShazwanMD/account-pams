import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {DebitNote} from "../debit-note.interface";
import {BillingModuleState} from "../../index";
import {DebitNoteActions} from "../debit-note.action";
import {Invoice} from "../../invoices/invoice.interface";
import { ChargeCode } from "../../../account/charge-codes/charge-code.interface";


@Component({
  selector: 'pams-debit-note-creator',
  templateUrl: './debit-note-creator.dialog.html',
})

export class DebitNoteCreatorDialog implements OnInit {

  private _invoice: Invoice;
  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: DebitNoteActions,
              private dialog: MdDialogRef<DebitNoteCreatorDialog>) {
  }

  set invoice(value: Invoice) {
    this._invoice = value;
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<DebitNote>{
      id: null,
      description: '',
      referenceNo: '',
      sourceNo: '',
      totalAmount: 0,
      debitNoteDate: undefined,
      chargeCode: <ChargeCode>{},
      invoice: <Invoice>{},
    });
    this.createForm.patchValue({invoice: this._invoice});
  }

  save(debitNote: DebitNote, isValid: boolean): void {
    console.log("start debit note");
    debitNote.sourceNo = this._invoice.referenceNo;
    console.log("sourceNo: " + debitNote.sourceNo);
    console.log("invoice: " + this._invoice.referenceNo);

    this.store.dispatch(this.actions.startDebitNoteTask(debitNote));
    this.dialog.close();
  }
}

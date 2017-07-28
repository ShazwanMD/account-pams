import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';
import {CreditNoteActions} from '../credit-note.action';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';

@Component({
  selector: 'pams-credit-note-creator',
  templateUrl: './credit-note-creator.dialog.html',
})

export class CreditNoteCreatorDialog implements OnInit {

  private _invoice: Invoice;
  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: CreditNoteActions,
              private dialog: MdDialogRef<CreditNoteCreatorDialog>) {
  }

  set invoice(value: Invoice) {
    this._invoice = value;
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<CreditNote>{
      id: null,
      code: '',
      description: '',
      referenceNo: '',
      sourceNo: '',
      totalAmount: 0,
      accountCode: '',
      creditNoteDate: undefined,
      accountName: '',
      chargeCode: <ChargeCode>{},
      invoice: <Invoice>{},
    });
    this.createForm.patchValue({invoice: this._invoice});
  }

  save(creditNote: CreditNote, isValid: boolean): void {
    console.log('start credit note');
    creditNote.sourceNo = this._invoice.referenceNo;

    console.log('sourceNo: ' + creditNote.sourceNo);
    console.log('invoice: ' + this._invoice.referenceNo);

    this.store.dispatch(this.actions.startCreditNoteTask(creditNote));
    this.dialog.close();
  }
}

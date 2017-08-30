import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceActions} from '../invoice.action';
import {BillingModuleState} from '../../index';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-task-creator',
  templateUrl: './invoice-task-creator.dialog.html',
})

export class InvoiceTaskCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions,
              private dialog: MdDialogRef<InvoiceTaskCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      id: [null],
      referenceNo: [''],
      sourceNo: [''],
      invoiceNo: [''],
      description: [''],
      totalPretaxAmount: [0],
      totalTaxAmount: [0],
      totalAmount: [0],
      balanceAmount: [0],
      paid: [false],
      issuedDate: [undefined,Validators.required],
      account: [<Account>{}],
      academicSession: [<AcademicSession>{}],
    });
  }

  save(invoice: Invoice, isValid: boolean) {
    console.log('invoice: ' + invoice.description);
    console.log('account: ' + invoice.account.code);
    invoice.description = 'Registration Invoice for '+  invoice.academicSession.code;
    this.store.dispatch(this.actions.startInvoiceTask(invoice));
    this.dialog.close();
  }
}

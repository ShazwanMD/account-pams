import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {InvoiceActions} from '../invoice.action';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';

@Component({
  selector: 'pams-invoice-item-editor',
  templateUrl: './invoice-item-editor.dialog.html',
})

export class InvoiceItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _invoiceItem: InvoiceItem;
  private _invoice: Invoice;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions,
              private dialog: MdDialogRef<InvoiceItemEditorDialog>) {
  }

  set invoiceItem(value: InvoiceItem) {
    this._invoiceItem = value;
    this.edit = true;
  }

  set invoice(value: Invoice) {
    this._invoice = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<InvoiceItem>{
      id: undefined,
      description: '',
      amount: 0,
      balanceAmount: 0,
      chargeCode: <ChargeCode>{},
    });
    if (this.edit) this.editForm.patchValue(this._invoiceItem);
  }

  submit(item: InvoiceItem, isValid: boolean) {
    item.description = item.chargeCode.description;
    item.amount = item.balanceAmount;
    if (!item.id) this.store.dispatch(this.actions.addInvoiceItem(this._invoice, item));
    else  this.store.dispatch(this.actions.updateInvoiceItem(this._invoice, item));
    this.dialog.close();
  }
}

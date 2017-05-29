import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceItem} from "../invoice-item.interface";
import {ChargeCode} from "../../../account/charge-codes/charge-code.interface";
import {MdDialogRef} from "@angular/material";
import {BillingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {InvoiceActions} from "../invoice.action";
import {Invoice} from "../invoice.interface";


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
              private actions:InvoiceActions,
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
      id: null,
      description: '',
      amount: 0,
      balanceAmount: 0,
      chargeCode: <ChargeCode>{},
    });
    if (this.edit) this.editForm.patchValue(this._invoiceItem);
  }

  submit(item: InvoiceItem, isValid: boolean) {
    item.description = item.chargeCode.description;
    if (!item.id) this.store.dispatch(this.actions.addInvoiceItem(this._invoice, item));
    else  this.store.dispatch(this.actions.updateInvoiceItem(this._invoice, item));
    this.dialog.close();
  }
}

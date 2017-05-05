import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {IdentityService} from "../../../../services/identity.service";
import {CommonService} from "../../../../services/common.service";
import {InvoiceItem} from "../invoice-item.interface";
import {ChargeCode} from "../../../account/charge-codes/charge-code.interface";
import {PromoCodeCreatorDialog} from "../../../marketing/promo-codes/dialog/promo-code-creator.dialog";
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
    // this.editForm.patchValue(this.invoiceItem);
  }

  save(item: InvoiceItem, isValid: boolean) {
    this.store.dispatch(this.actions.addInvoiceItem(this._invoice, item))
    this.close();
  }

  close(): void {
    this.dialog.close();
  }
}

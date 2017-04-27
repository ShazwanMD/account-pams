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


@Component({
  selector: 'pams-invoice-item-editor',
  templateUrl: './invoice-item-editor.dialog.html',
})

export class InvoiceItemEditorDialog implements OnInit {

  private editForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<InvoiceItemEditorDialog>) {
  }

  set invoiceItem(value: InvoiceItem) {
    this.invoiceItem = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<InvoiceItem>{
      id: null,
      description: '',
      amount: 0,
      balanceAmount: 0,
      chargeCode: {},
    });
    // this.editForm.patchValue(this.invoiceItem);
  }

  // save(invoice: Invoice, isValid: boolean) {
  //   this.submitted = true; // set form submit to true
  //   this._invoiceService.startInvoiceTask(invoice).subscribe(res => {
  //   });
  // }

  close(): void {
    this.dialog.close();
  }
}

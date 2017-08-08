import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { Account } from "../../../../shared/model/account/account.interface";
import { TdDialogService, ITdDataTableColumn } from "@covalent/core";

@Component({
  selector: 'pams-invoice-receipt-creator',
  templateUrl: './invoice-receipt-creator.dialog.html',
})

export class InvoiceReceiptCreatorDialog implements OnInit {

  private createForm: FormGroup;
  private _receipt: Receipt;
  private _invoice: Invoice;
  private edit:boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private _dialogService: TdDialogService,
              private dialog: MdDialogRef<InvoiceReceiptCreatorDialog>) {
  }

  set receipt(value: Receipt) {
    this._receipt = value;
  }
  
  set invoice(value: Invoice) {
      this._invoice = value;
      this.edit = true;
    }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      invoice: '',
      receipt: '',
    });
  }

  submit(item: Receipt, isValid: boolean) {
    
    this.dialog.close();
  }
}

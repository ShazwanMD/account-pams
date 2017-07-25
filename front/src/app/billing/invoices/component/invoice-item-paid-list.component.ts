import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {InvoiceItem} from '../invoice-item.interface';
import {InvoiceItemEditorDialog} from '../dialog/invoice-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {InvoiceActions} from '../invoice.action';
import {Invoice} from '../invoice.interface';
import { ReceiptActions } from "../../receipts/receipt.action";
import { Receipt } from "../../receipts/receipt.interface";

@Component({
  selector: 'pams-invoice-item-paid-list',
  templateUrl: './invoice-item-paid-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceItemPaidListComponent implements OnInit{

  @Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];
  @Input() receipt: Receipt;
  
  private selectedRows: InvoiceItem[];
  
  private columns: any[] = [
                            {name: 'chargeCode.code', label: 'Charge Code'},
                            {name: 'chargeCode.description', label: 'Charge Code Description'},
                            {name: 'chargeCode.taxCode.code', label: 'Tax Code'},
                            {name: 'chargeCode.taxCode.taxRate', label: 'Tax Rate'},
                            {name: 'chargeCode.inclusive', label: 'Inclusive'},
                            {name: 'amount', label: 'Amount'},
                            {name: 'action', label: ''},
                          ];

  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: ReceiptActions,
          private store: Store<BillingModuleState>,
          private vcf: ViewContainerRef,
          private dialog: MdDialog,
          private snackbar: MdSnackBar) {
    }
    
  ngOnInit(): void {
      //this.selectedRows = this.invoiceItems.filter((value) => value.selected);
      
    }

}

import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import { InvoiceActions } from "../../invoices/invoice.action";
import {ReceiptActions} from '../receipt.action';

@Component({
  selector: 'pams-invoice-applicator-list',
  templateUrl: './invoice-applicator-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceApplicatorListComponent implements OnInit {

    @Input() invoiceItems: InvoiceItem[];
    @Input() receipt: Receipt;
    
    private selectedRows: InvoiceItem[];
    
    constructor(private actions: ReceiptActions,
            private vcf: ViewContainerRef,
            private store: Store<BillingModuleState>,
            private dialog: MdDialog) {
}

    private columns: any[] = [
      {name: 'chargeCode.code', label: 'Charge Code'},
      {name: 'chargeCode.description', label: 'Charge Code Description'},
      {name: 'chargeCode.taxCode.code', label: 'Tax Code'},
      {name: 'chargeCode.taxCode.taxRate', label: 'Tax Rate'},
      {name: 'chargeCode.inclusive', label: 'Inclusive'},
      {name: 'amount', label: 'Amount'},
      {name: 'action', label: ''},
    ];

    ngOnInit(): void {
        this.selectedRows = this.invoiceItems.filter((value) => value.selected);
    }
    
    selectRow(invoiceItems: InvoiceItem[]): void {
        let totalAmountToPay = 0;
        for (var i = 0; i < this.selectedRows.length; i++) {
            totalAmountToPay += this.selectedRows[i].amount;
        }
        
        console.log("Total Amount Check: "+ totalAmountToPay);
      
    }

    selectAllRows(invoiceItems: InvoiceItem[]): void {
        this.selectRow(invoiceItems);
    }
    
    Apply(invoiceItems: InvoiceItem[]): void {
        //this.selectRow(invoiceItems)
        console.log('length: ' + this.selectedRows.length);
        for (let i: number = 0; i < this.selectedRows.length; i++) {
          this.store.dispatch(this.actions.addReceiptItem(this.receipt, this.selectedRows[i]));
        }
        this.selectedRows = [];
    }

}

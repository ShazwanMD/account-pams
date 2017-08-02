import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { InvoiceActions } from "../../invoices/invoice.action";

@Component({
  selector: 'pams-invoice-applicator-list',
  templateUrl: './invoice-applicator-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceApplicatorListComponent implements OnInit {

    @Input() invoiceItems: InvoiceItem[];
    
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

    ngOnInit(): void {
        this.selectedRows = this.invoiceItems.filter((value) => value.selected);
    }
    
    selectRow(invoiceItems: InvoiceItem[]): void {
        
        let sum = 0;
        for(var i=0; i<=this.invoiceItems.length; i++){
           
        }
      
    }

    selectAllRows(invoiceItems: InvoiceItem[]): void {
    }

}

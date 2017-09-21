import { Component, OnInit, ViewContainerRef, ElementRef, Input, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { ReceiptActions } from '../receipt.action';
import { Store } from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { ReceiptItem } from "../../../../shared/model/billing/receipt-item.interface";

@Component( {
    selector: 'pams-receipt-item-list-dialog',
    templateUrl: './receipt-item-list-dialog.component.html',
} )

export class ReceiptItemListDialog {

    @Input() receipt: Receipt;
    @Input() invoice: Invoice;
    @Input() receiptItems: ReceiptItem[];
    
    private columns: any[] = [
                              { name: 'chargeCode.code', label: 'Charge Code' },
                              { name: 'description', label: 'Charge Code Description' },
                              { name: 'dueAmount', label: 'Amount' },
                              { name: 'appliedAmount', label: 'Received Amount' },
                              { name: 'totalAmount', label: 'Balance Amount' },
                          ];


}
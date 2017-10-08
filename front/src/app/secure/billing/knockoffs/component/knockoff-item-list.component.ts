import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { InvoiceKnockoffDialog } from "../dialog/knockoff-invoice-creator.dialog";
import { KnockoffActions } from "../knockoff.action";

@Component({
  selector: 'pams-knockoff-item-list',
  templateUrl: './knockoff-item-list.component.html',
})
export class KnockoffItemListComponent {

    @Input() knockoffItem: KnockoffItem[];
    @Input() knockoff: Knockoff;
    @Input() invoice: Invoice;
    

    
  private columns: any[] = [
                            
                            {name: 'chargeCode.code', label: 'Charge Code'},
                            {name: 'description', label: 'Charge Code Description'},
                            {name: 'dueAmount', label: 'Amount'},
                            {name: 'appliedAmount', label: 'Received Amount'},
                            {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialog) {
  }
  

}

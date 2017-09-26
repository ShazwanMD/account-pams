import { AccountTransaction } from './../../../../shared/model/account/account-transaction.interface';
import {ChangeDetectionStrategy, Component, Input, EventEmitter, Output, ViewContainerRef} from '@angular/core';
import {AccountActivityCharge} from '../../../../shared/model/account/account-activity-charge.interface';
import { Router, ActivatedRoute } from "@angular/router";
import { AccountActions } from "../account.action";
import { Store } from "@ngrx/store";
import { MdDialog, MdDialogConfig, MdDialogRef } from "@angular/material";
import { AccountModuleState } from "../../index";
import { InvoiceItemDialog } from "../dialog/invoice-item.dialog";
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { DebitNoteItemDialog } from "../dialog/debit-note-item.dialog";
import { CreditNoteItemDialog } from "../dialog/credit-note-item.dialog";
import { AccountChargeType } from "../../../../shared/model/account/account-charge-type.enum";

@Component({
  selector: 'pams-account-charge-activity-list',
  templateUrl: './account-charge-activity-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityChargeListComponent {

    @Input() chargeActivity: AccountActivityCharge[];

  transaction:any= AccountChargeType;

  private columns: any[] = [
    {name: 'postedDate', label: 'Date'},                        
    {name: 'sourceNo', label: 'Document No'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Amount'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: AccountActions,
          private store: Store<AccountModuleState>,
          private vcf: ViewContainerRef,
          private dialog: MdDialog) {
  }

  

}

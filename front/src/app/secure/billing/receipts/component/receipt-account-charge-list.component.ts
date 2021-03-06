import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {ReceiptActions} from '../receipt.action';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import {Account} from '../../../../shared/model/account/account.interface';
import { AccountChargeReceiptDialog } from "../dialog/account-charge-receipt-creator.dialog";

@Component({
  selector: 'pams-receipt-account-charge-list',
  templateUrl: './receipt-account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReceiptAccountChargeListComponent implements OnInit {

  private selectedRows: ReceiptItem[];
  private editorDialogRef: MdDialogRef<AccountChargeReceiptDialog>;
  private columns: any[] = [
    {name: 'description', label: 'Description'},
    {name: 'dueAmount', label: 'Amount'},
    {name: 'appliedAmount', label: 'Received Amount'},
    {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];
  
  @Input() receipt: Receipt;
  @Input() receiptItems: ReceiptItem[];
  @Input() accountCharges: AccountCharge[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.receiptItems.filter((value) => value.selected);
  }

  edit(receiptItem: ReceiptItem): void {
    //this.showDialog(receiptItem);
  }
  filter(): void {
  }

  selectRow(receiptItem: ReceiptItem): void {
  }

  selectAllRows(receiptItems: ReceiptItem[]): void {
  }

  UnpaidAccountCharge(charge: AccountCharge): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(AccountChargeReceiptDialog, config);
    //this.editorDialogRef.componentInstance.account = this.account;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      // no op
    });
  }

}

import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";

@Component({
  selector: 'pams-waiver-item-list',
  templateUrl: './waiver-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class WaiverItemListComponent implements OnInit {

  private selectedRows: WaiverItem[];
  //private editorDialogRef: MdDialogRef<ReceiptItemEditorDialog>;
  private columns: any[] = [
    {name: 'invoice.referenceNo', label: 'Invoice'},
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'description', label: 'Charge Code Description'},
    {name: 'dueAmount', label: 'Amount'},
    {name: 'appliedAmount', label: 'Received Amount'},
    {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  @Input() waiverFinanceApplication: WaiverFinanceApplication;
  @Input() waiverItem: WaiverItem[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.waiverItem.filter((value) => value.selected);
  }

/*  edit(receiptItem: ReceiptItem): void {
    this.showDialog(receiptItem);
  }
  
  showDialog(receiptItem: ReceiptItem): void {
      console.log('showDialog');
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '65px'};
      this.editorDialogRef = this.dialog.open(ReceiptItemEditorDialog, config);
      this.editorDialogRef.componentInstance.receipt = this.receipt;
      this.editorDialogRef.componentInstance.receiptItem = receiptItem;
    }*/

/*  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.deleteReceiptItem(this.receipt, this.selectedRows[i]));
    }
  }

  filter(): void {
  }

  selectRow(receiptItem: ReceiptItem): void {
  }

  selectAllRows(receiptItems: ReceiptItem[]): void {
  }*/

}
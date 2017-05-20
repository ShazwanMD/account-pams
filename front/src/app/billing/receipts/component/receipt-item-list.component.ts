import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {ReceiptItem} from "../receipt-item.interface";
import {ReceiptItemEditorDialog} from "../dialog/receipt-item-editor.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {BillingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {ReceiptActions} from "../receipt.action";
import {Receipt} from "../receipt.interface";

@Component({
  selector: 'pams-receipt-item-list',
  templateUrl: './receipt-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReceiptItemListComponent implements OnInit {

  @Input() receipt: Receipt;
  @Input() receiptItems: ReceiptItem[];

  private selectedRows: ReceiptItem[];
  private editorDialogRef: MdDialogRef<ReceiptItemEditorDialog>;
  private columns: any[] = [
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'chargeCode.description', label: 'Charge Code Description'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''}
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
    this.selectedRows = this.receiptItems.filter(value => value.selected);
  }

  createDialog(): void {
    this.showDialog(null);
  }

  edit(receiptItem: ReceiptItem): void {
    this.showDialog(receiptItem);
  }

  delete(): void {
    console.log("length: " + this.selectedRows.length);
    for (var i = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.deleteReceiptItem(this.receipt, this.selectedRows[i]));
    }
  }

  filter(): void {
  }

  selectRow(receiptItem: ReceiptItem): void {
  }

  selectAllRows(receiptItems: ReceiptItem[]): void {
  }

  showDialog(receiptItem: ReceiptItem): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(ReceiptItemEditorDialog, config);
    this.editorDialogRef.componentInstance.receipt = this.receipt;
    if (receiptItem) this.editorDialogRef.componentInstance.receiptItem = receiptItem; // set
    this.editorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

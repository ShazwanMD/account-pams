import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";
import {Receipt} from "../receipt.interface";

@Component({
  selector: 'pams-archived-receipt-list',
  templateUrl: './archived-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedReceiptListComponent {

  @Input() receipts: Receipt[];
  @Output() view = new EventEmitter<Receipt>();

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewReceipt(receipt: Receipt): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing invoice", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(receipt);
    });
  }
}

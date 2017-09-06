import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';

@Component({
  selector: 'pams-archived-receipt-list',
  templateUrl: './archived-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedReceiptListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() receipts: Receipt[];
  @Output() view = new EventEmitter<Receipt>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewReceipt(receipt: Receipt): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing invoice', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(receipt);
    });
  }
}

import {
  Component, 
  Input, 
  EventEmitter, 
  Output, 
  ChangeDetectionStrategy, 
  AfterViewInit, 
  OnChanges, 
  SimpleChange
} from '@angular/core';
import {
TdDataTableSortingOrder,
TdDataTableService,
ITdDataTableSortChangeEvent,
IPageChangeEvent
} from '@covalent/core';
import {MdSnackBar} from '@angular/material';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';

@Component({
  selector: 'pams-archived-receipt-list',
  templateUrl: './archived-receipt-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedReceiptListComponent  implements OnChanges{

  private columns: any[] = [
    {name: 'receivedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'receiptType', label: 'Receipt Type'},
    {name: 'paymentMethod', label: 'Payment Method'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'referenceNo';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  @Input() receipts: Receipt[];
  @Output() view = new EventEmitter<Receipt>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}
  viewReceipt(receipt: Receipt): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing Receipt Detail', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(receipt);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['receipts']){
        this.filteredData = changes['receipts'].currentValue; 
        this.filteredTotal = changes['receipts'].currentValue.length;
        this.filter();
      }
    }
  sort(sortEvent: ITdDataTableSortChangeEvent): void {
    this.sortBy = sortEvent.name;
    this.sortOrder = sortEvent.order;
    this.filter();
  }
  search(searchTerm: string): void {
    this.searchTerm = searchTerm;
    this.filter();
  }
  page(pagingEvent: IPageChangeEvent): void {
    this.fromRow = pagingEvent.fromRow;
    this.currentPage = pagingEvent.page;
    this.pageSize = pagingEvent.pageSize;
    this.filter();
  }
  filter(): void {
    let newData: any[] = this.receipts;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

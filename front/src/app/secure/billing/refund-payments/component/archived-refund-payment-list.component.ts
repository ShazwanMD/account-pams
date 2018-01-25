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
import { MdSnackBar } from '@angular/material';
import { RefundPayment } from '../../../../shared/model/billing/refund-payment.interface';

@Component({
  selector: 'pams-archived-refund-payment-list',
  templateUrl: './archived-refund-payment-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedRefundPaymentListComponent implements OnChanges {

  private columns: any[] = [
    { name: 'issuedDate', label: 'Date' },
    { name: 'referenceNo', label: 'Reference No' },
    {name: 'accountCode', label: 'Account'},
    {name: 'accountName', label: 'Name'},
    { name: 'description', label: 'Description' },
    { name: 'amount', label: 'Total Amount' },
    { name: 'creatorUsername', label: 'Creator' },
    { name: 'createdDate', label: 'Created Date' },
    { name: 'flowState', label: 'Status' },
    { name: 'action', label: '' },
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'referenceNo';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  @Input() refundPayment: RefundPayment[];
  @Output() view = new EventEmitter<RefundPayment>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}
  viewRefundPayment(invoice: RefundPayment): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing refundPayment', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(invoice);
    });
  }
  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['refundPayment']){
        this.filteredData = changes['refundPayment'].currentValue; 
        this.filteredTotal = changes['refundPayment'].currentValue.length;
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
    let newData: any[] = this.refundPayment;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}
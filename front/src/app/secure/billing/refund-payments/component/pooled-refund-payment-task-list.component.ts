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
import { RefundPaymentTask } from '../../../../shared/model/billing/refund-payment-task.interface';
import { MdSnackBar, MdSnackBarConfig, MdSnackBarRef, SimpleSnackBar } from '@angular/material';

@Component({
  selector: 'pams-pooled-refund-payment-task-list',
  templateUrl: './pooled-refund-payment-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledRefundPaymentTaskListComponent {

  private columns: any[] = [
    { name: 'issuedDate', label: 'Date' },
    { name: 'referenceNo', label: 'Reference No' },
    { name: 'description', label: 'Description' },
    { name: 'amount', label: 'Total Amount' },
    { name: 'refundPayment.creatorUsername', label: 'Creator' },
    { name: 'refundPayment.createdDate', label: 'Created Date' },
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
  @Input() refundPaymentTask: RefundPaymentTask[];
  @Output() claim: EventEmitter<RefundPaymentTask> = new EventEmitter<RefundPaymentTask>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}

  claimTask(task: RefundPaymentTask): void {
    console.log('Emitting task');
    let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Claiming invoice');
    snackBarRef.dismiss();
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['refundPaymentTask']){
        this.filteredData = changes['refundPaymentTask'].currentValue; 
        this.filteredTotal = changes['refundPaymentTask'].currentValue.length;
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
    let newData: any[] = this.refundPaymentTask;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

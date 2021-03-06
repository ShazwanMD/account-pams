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
import {ReceiptTask} from '../../../../shared/model/billing/receipt-task.interface';

@Component({
  selector: 'pams-pooled-receipt-task-list',
  templateUrl: './pooled-receipt-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledReceiptTaskListComponent implements OnChanges{

  @Input() receiptTasks: ReceiptTask[];
  @Output() claim = new EventEmitter<ReceiptTask>();

  private columns: any[] = [
                            {name: 'receivedDate', label: 'Date'},
                            {name: 'referenceNo', label: 'Reference No'},
                            {name: 'description', label: 'Description'},
                            {name: 'receiptType', label: 'Receipt Type'},
                            {name: 'paymentMethod', label: 'Payment Method'},
                            {name: 'sourceNo', label: 'Source No'},
                            {name: 'receipt.creatorUsername', label: 'Creator'},
                            {name: 'receipt.createdDate', label: 'Created Date'},
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
  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}

  claimTask(task: ReceiptTask): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Claiming receipt', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['receiptTasks']){
        this.filteredData = changes['receiptTasks'].currentValue; 
        this.filteredTotal = changes['receiptTasks'].currentValue.length;
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
    let newData: any[] = this.receiptTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

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
import {InvoiceTask} from '../../../../shared/model/billing/invoice-task.interface';
import {MdSnackBar, MdSnackBarRef, SimpleSnackBar} from '@angular/material';

@Component({
  selector: 'pams-pooled-invoice-task-list',
  templateUrl: './pooled-invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledInvoiceTaskListComponent implements OnChanges{

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'invoice.creatorUsername', label: 'Creator'},
    {name: 'invoice.createdDate', label: 'Created Date'},
    { name: 'paid', label: 'Status' },
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

  @Input() invoiceTasks: InvoiceTask[];
  @Output() claim: EventEmitter<InvoiceTask> = new EventEmitter<InvoiceTask>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}

  claimTask(task: InvoiceTask): void {
    console.log('Emitting task');
    let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Claiming invoice');
    snackBarRef.dismiss();
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['invoiceTasks']){
        this.filteredData = changes['invoiceTasks'].currentValue; 
        this.filteredTotal = changes['invoiceTasks'].currentValue.length;
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
    let newData: any[] = this.invoiceTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

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
import { KnockoffTask } from '../../../../shared/model/billing/knockoff-task.interface';
import { MdSnackBar, MdSnackBarConfig, MdSnackBarRef, SimpleSnackBar } from '@angular/material';

@Component({
  selector: 'pams-pooled-knockoff-task-list',
  templateUrl: './pooled-knockoff-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledKnockoffTaskListComponent {

  private columns: any[] = [
    { name: 'issuedDate', label: 'Date' },
    { name: 'referenceNo', label: 'Reference No' },
    {name: 'knockoff.payments.account.code', label: 'Account'},
    {name: 'knockoff.payments.account.name', label: 'Name'},
    { name: 'description', label: 'Description' },
    { name: 'totalAmount', label: 'Total Amount' },
    { name: 'knockoff.creatorUsername', label: 'Creator' },
    { name: 'knockoff.createdDate', label: 'Created Date' },
    {name: 'flowState', label: 'Status'},
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
  
  @Input() knockoffTask: KnockoffTask[];
  @Output() claim: EventEmitter<KnockoffTask> = new EventEmitter<KnockoffTask>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}

  claimTask(task: KnockoffTask): void {
    console.log('Emitting task');
    let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Claiming knockoff');
    snackBarRef.dismiss();
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['knockoffTask']){
        this.filteredData = changes['knockoffTask'].currentValue; 
        this.filteredTotal = changes['knockoffTask'].currentValue.length;
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
    let newData: any[] = this.knockoffTask;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

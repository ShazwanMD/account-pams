import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';
import {ActivatedRoute, Router} from '@angular/router';
import {
  IPageChangeEvent,
  ITdDataTableSortChangeEvent,
  TdDataTableService,
  TdDataTableSortingOrder
} from '@covalent/core';

@Component({
  selector: 'pams-pooled-credit-note-task-list',
  templateUrl: './pooled-credit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledCreditNoteTaskListComponent {

  private columns: any[] = [
    {name: 'creditNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'chargeCode.description', label: 'Charge Code'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() creditNoteTasks: CreditNoteTask[];
  @Output() claim = new EventEmitter<CreditNoteTask>();

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'referenceNo';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private snackBar: MdSnackBar,
              private router: Router,
              private route: ActivatedRoute,
              private _dataTableService: TdDataTableService) {
  }

  claimTask(task: CreditNoteTask): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Claiming invoice', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }

  ngAfterViewInit(): void {
    this.filteredData = this.creditNoteTasks;
    this.filteredTotal = this.creditNoteTasks.length;
    this.filter();
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
    let newData: any[] = this.creditNoteTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

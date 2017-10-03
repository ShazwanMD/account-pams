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
import {DebitNoteTask} from '../../../../shared/model/billing/debit-note-task.interface';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'pams-pooled-debit-note-task-list',
  templateUrl: './pooled-debit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledDebitNoteTaskListComponent implements OnChanges{

  @Input() debitNoteTasks: DebitNoteTask[];
  @Output() claim = new EventEmitter<DebitNoteTask>();

  private columns: any[] = [
    {name: 'debitNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'chargeCode.description', label: 'Charge Code'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'debitNote.creatorUsername', label: 'Creator'},
    {name: 'debitNote.createdDate', label: 'Created Date'},
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
    private snackBar: MdSnackBar,
    private router: Router,
    private route: ActivatedRoute,) {
}

  claimTask(task: DebitNoteTask): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Claiming debit note', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['debitNoteTasks']){
        this.filteredData = changes['debitNoteTasks'].currentValue; 
        this.filteredTotal = changes['debitNoteTasks'].currentValue.length;
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
    let newData: any[] = this.debitNoteTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

}

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
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'pams-assigned-credit-note-task-list',
  templateUrl: './assigned-credit-note-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedCreditNoteTaskListComponent implements OnChanges{

  @Input() creditNoteTasks: CreditNoteTask[];
  @Output() view = new EventEmitter<CreditNoteTask>();

  private columns: any[] = [
    {name: 'creditNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'creditNote.creatorUsername', label: 'Creator'},
    {name: 'creditNote.createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'referenceNo';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private snackBar: MdSnackBar,
              private router: Router,
              private route: ActivatedRoute,
              private _dataTableService: TdDataTableService) {
  }

  viewTask(task: CreditNoteTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing credit note", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['creditNoteTasks']){
        this.filteredData = changes['creditNoteTasks'].currentValue; 
        this.filteredTotal = changes['creditNoteTasks'].currentValue.length;
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
    let newData: any[] = this.creditNoteTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

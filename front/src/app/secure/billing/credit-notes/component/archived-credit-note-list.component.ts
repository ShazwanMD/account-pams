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
import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';

@Component({
  selector: 'pams-archived-credit-note-list',
  templateUrl: './archived-credit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedCreditNoteListComponent implements OnChanges{

  private columns: any[] = [
    {name: 'creditNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
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

  @Input() creditNotes: CreditNoteTask[];
  @Output() view = new EventEmitter<CreditNote>();

  constructor(private snackBar: MdSnackBar,
              private _dataTableService: TdDataTableService) {
  }

  viewCreditNote(credit: CreditNote): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing credit note', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(credit);
    });
  }
  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['creditNotes']){
        this.filteredData = changes['creditNotes'].currentValue; 
        this.filteredTotal = changes['creditNotes'].currentValue.length;
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
    let newData: any[] = this.creditNotes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

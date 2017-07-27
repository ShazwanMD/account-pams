import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import { DebitNote } from '../../../../shared/model/billing/debit-note.interface';
import { DebitNoteTask } from '../../../../shared/model/billing/debit-note-task.interface';
import { TdDataTableService, TdDataTableSortingOrder, ITdDataTableSortChangeEvent, IPageChangeEvent } from '@covalent/core';

@Component({
  selector: 'pams-archived-debit-note-list',
  templateUrl: './archived-debit-note-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedDebitNoteListComponent {

  private columns: any[] = [
    {name: 'debitNoteDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'sourceNo', label: 'Invoice'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'chargeCode.description', label: 'Charge Code'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() debitNotes: DebitNoteTask[];
  @Output() view = new EventEmitter<DebitNote>();

  constructor(private snackBar: MdSnackBar,
          private _dataTableService: TdDataTableService) {
  }

  viewDebitNote(debit: DebitNote): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing debit note', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(debit);
    });
  }

  filteredData: any[];
    filteredTotal: number;
    searchTerm: string = '';
    fromRow: number = 1;
    currentPage: number = 1;
    pageSize: number = 10;
    sortBy: string = 'referenceNo';
    sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

    ngAfterViewInit(): void {
      this.filteredData = this.debitNotes;
      this.filteredTotal = this.debitNotes.length;
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
      let newData: any[] = this.debitNotes;
      newData = this._dataTableService.filterData(newData, this.searchTerm, true);
      this.filteredTotal = newData.length;
      newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
      newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
      this.filteredData = newData;
    }
}


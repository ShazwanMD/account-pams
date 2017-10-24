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
import { Knockoff } from '../../../../shared/model/billing/knockoff.interface';

@Component({
  selector: 'pams-archived-knockoff-list',
  templateUrl: './archived-knockoff-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedKnockoffListComponent {

  private columns: any[] = [
    { name: 'issuedDate', label: 'Date' },
    { name: 'referenceNo', label: 'Reference No' },
    { name: 'description', label: 'Description' },
    { name: 'totalAmount', label: 'Total Amount' },
    { name: 'creatorUsername', label: 'Creator' },
    { name: 'createdDate', label: 'Created Date' },
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
  
  @Input() knockoff: Knockoff[];
  @Output() view = new EventEmitter<Knockoff>();
  
  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
  }

  viewKnockoff(invoice: Knockoff): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing knockoff', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(invoice);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['knockoff']){
        this.filteredData = changes['knockoff'].currentValue; 
        this.filteredTotal = changes['knockoff'].currentValue.length;
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
    let newData: any[] = this.knockoff;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

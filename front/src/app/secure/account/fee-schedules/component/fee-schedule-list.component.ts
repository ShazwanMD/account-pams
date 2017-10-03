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
import {FeeSchedule} from '../../../../shared/model/account/fee-schedule.interface';
import {MdSnackBar} from '@angular/material';
@Component({
  selector: 'pams-fee-schedule-list',
  templateUrl: './fee-schedule-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FeeScheduleListComponent implements OnChanges{

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''},
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
 
  @Input() feeSchedules: FeeSchedule[];
  @Output() view = new EventEmitter<FeeSchedule>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['feeSchedules']){
        this.filteredData = changes['feeSchedules'].currentValue; 
        this.filteredTotal = changes['feeSchedules'].currentValue.length;
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
    let newData: any[] = this.feeSchedules;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

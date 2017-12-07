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
import { TaxCode } from './../../../../shared/model/common/tax-code.interface';
@Component({
  selector: 'pams-tax-code-list',
  templateUrl: './tax-code.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaxCodesComponent implements OnChanges {
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'taxRate', label: 'TaxRate'},
    {name: 'active', label: 'Active'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'createdDate', label: 'Created Date'},
    {name: 'action', label: ''}
  ];
  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 5;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;
  @Input() taxCodes: TaxCode[];
  @Output() editDialog: EventEmitter<TaxCode> = new EventEmitter<TaxCode>();
  @Output() delete: EventEmitter<TaxCode> = new EventEmitter<TaxCode>();
  constructor(private _dataTableService: TdDataTableService,
              private snackBar: MdSnackBar) {
  }
  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['taxCodes']){
        this.filteredData = changes['taxCodes'].currentValue; 
        this.filteredTotal = changes['taxCodes'].currentValue.length;
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
    let newData: any[] = this.taxCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}
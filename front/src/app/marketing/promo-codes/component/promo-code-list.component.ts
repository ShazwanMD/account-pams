import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, AfterViewInit} from '@angular/core';
import { TdDataTableSortingOrder, TdDataTableService, ITdDataTableSortChangeEvent } from '@covalent/core';
import { IPageChangeEvent } from '@covalent/core';
import {PromoCode} from '../../../shared/model/marketing/promo-code.interface';

@Component({
  selector: 'pams-promo-code-list',
  templateUrl: './promo-code-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PromoCodeListComponent implements AfterViewInit {

  @Input() promoCodes: PromoCode[];
  @Output() view = new EventEmitter<PromoCode>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
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

  constructor(private _dataTableService: TdDataTableService) {}

  ngAfterViewInit(): void {
    this.filteredData = this.promoCodes;
    this.filteredTotal = this.promoCodes.length;
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
    let newData: any[] = this.promoCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

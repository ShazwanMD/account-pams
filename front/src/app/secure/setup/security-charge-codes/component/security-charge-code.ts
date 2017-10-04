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
import { BankCode } from './../../../../shared/model/common/bank-code.interface';
import { SecurityChargeCode } from '../../../../shared/model/common/security-charge-code.interface';
@Component({
  selector: 'pams-security-charge-code',
  templateUrl: './security-charge-code.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecurityChargeCodeComponent implements OnChanges {
  private columns: any[] = [
    {name: 'section', label: 'Compound Section'},
    {name: 'description', label: 'Compound Description'},
    {name: 'taxCode.code', label: 'Tax Code'},
    {name: 'offense', label: 'Compound Offense'},
    {name: 'offenseDescription', label: 'Compound Offense Description'},
    {name: 'amountDescription', label: 'Compound Amount Description'},
    {name: 'amount', label: 'Compound Amount'},
    {name: 'taxAmount', label: 'Tax Amount'},
    {name: 'inclusive', label: 'Inclusive'},
    {name: 'netAmount', label: 'Net Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'active', label: 'Compound Active'},
    {name: 'action', label: ''},
  ];
  
    filteredData: any[];
    filteredTotal: number;
    searchTerm: string = '';
    fromRow: number = 1;
    currentPage: number = 1;
    pageSize: number = 5;
    sortBy: string = 'section';
    sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

    constructor(private _dataTableService: TdDataTableService,
      private snackBar: MdSnackBar) {
}
@Input() securityChargeCodes: SecurityChargeCode[];
@Output() editDialog: EventEmitter<SecurityChargeCode> = new EventEmitter<SecurityChargeCode>();


  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['securityChargeCodes']){
        this.filteredData = changes['securityChargeCodes'].currentValue; 
        this.filteredTotal = changes['securityChargeCodes'].currentValue.length;
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
    let newData: any[] = this.securityChargeCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}
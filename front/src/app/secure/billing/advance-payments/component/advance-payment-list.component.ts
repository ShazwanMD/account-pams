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
import {AdvancePayment} from '../../../../shared/model/billing/advance-payment.interface';

@Component({
  selector: 'pams-advance-payment-list',
  templateUrl: './advance-payment-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdvancePaymentListComponent implements OnChanges{

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'sourceNo', label: 'Source No'},
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
  
  @Input() advancePayments: AdvancePayment[];
  @Output() knockoffCreateDialog: EventEmitter<AdvancePayment> = new EventEmitter<AdvancePayment>();
  @Output() refundPaymentCreateDialog: EventEmitter<AdvancePayment> = new EventEmitter<AdvancePayment>();

  constructor(private _dataTableService: TdDataTableService,
    private snackBar: MdSnackBar) {
}
ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
if (changes['advancePayments']){
this.filteredData = changes['advancePayments'].currentValue; 
this.filteredTotal = changes['advancePayments'].currentValue.length;
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
let newData: any[] = this.advancePayments;
newData = this._dataTableService.filterData(newData, this.searchTerm, true);
this.filteredTotal = newData.length;
newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
this.filteredData = newData;
}

}

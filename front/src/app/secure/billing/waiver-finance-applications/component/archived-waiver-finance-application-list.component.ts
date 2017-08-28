import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {WaiverFinanceApplication} from '../../../../shared/model/billing/waiver-finance-application.interface';
import {
  IPageChangeEvent,
  ITdDataTableSortChangeEvent,
  TdDataTableService,
  TdDataTableSortingOrder
} from '@covalent/core';

@Component({
  selector: 'pams-archived-waiver-finance-application-list',
  templateUrl: './archived-waiver-finance-application-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedWaiverFinanceApplicationListComponent {

  @Input() waiverFinanceApplications: WaiverFinanceApplication[];
  @Output() view = new EventEmitter<WaiverFinanceApplication>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'waivedAmount', label: 'Waived Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar,
              private _dataTableService: TdDataTableService) {
  }

  viewWaiverFinanceApplication(waiverFinanceApplication: WaiverFinanceApplication): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing waiverFinanceApplication', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(waiverFinanceApplication);
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
    this.filteredData = this.waiverFinanceApplications;
    this.filteredTotal = this.waiverFinanceApplications.length;
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
    let newData: any[] = this.waiverFinanceApplications;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

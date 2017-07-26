import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {WaiverApplication} from '../../../shared/model/financialaid/waiver-application.interface';
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-archived-waiver-application-list',
  templateUrl: './archived-waiver-application-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedWaiverApplicationListComponent {

  @Input() waiverApplications: WaiverApplication[];
  @Output() view = new EventEmitter<WaiverApplication>();

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

  viewWaiverApplication(waiverApplication: WaiverApplication): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing waiverApplication', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(waiverApplication);
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
    this.filteredData = this.waiverApplications;
    this.filteredTotal = this.waiverApplications.length;
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
    let newData: any[] = this.waiverApplications;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

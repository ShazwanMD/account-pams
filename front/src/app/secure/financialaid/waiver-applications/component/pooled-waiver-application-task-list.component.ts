import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import {
  IPageChangeEvent,
  ITdDataTableSortChangeEvent,
  TdDataTableService,
  TdDataTableSortingOrder
} from '@covalent/core';

@Component({
  selector: 'pams-pooled-waiver-application-task-list',
  templateUrl: './pooled-waiver-application-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledWaiverApplicationTaskListComponent {

  @Input() waiverApplicationTasks: WaiverApplicationTask[];
  @Output() claim = new EventEmitter<WaiverApplicationTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'application.waivedAmount', label: 'Waived Amount'},
    {name: 'waiverType', label: 'Waiver Type'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar,
              private _dataTableService: TdDataTableService) {
  }

  claimTask(task: WaiverApplicationTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Claiming waiverApplication", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
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
    this.filteredData = this.waiverApplicationTasks;
    this.filteredTotal = this.waiverApplicationTasks.length;
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
    let newData: any[] = this.waiverApplicationTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

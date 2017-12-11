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
import {WaiverFinanceApplicationTask} from '../../../../shared/model/billing/waiver-finance-application-task.interface';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'pams-assigned-waiver-finance-application-task-list',
  templateUrl: './assigned-waiver-finance-application-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedWaiverFinanceApplicationTaskListComponent implements OnChanges{

  private columns: any[] = [
    {name: 'graduateCenterType', label: 'Graduate Center'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'application.waivedAmount', label: 'Waived Amount'},
    {name: 'application.creatorUsername', label: 'Creator'},
    {name: 'application.createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
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

  @Input() waiverFinanceApplicationTasks: WaiverFinanceApplicationTask[];
  @Output() view = new EventEmitter<WaiverFinanceApplicationTask>();

  constructor(private snackBar: MdSnackBar,
              private router: Router,
              private route: ActivatedRoute,
              private _dataTableService: TdDataTableService) {
  }

  viewTask(task: WaiverFinanceApplicationTask): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing waiverFinanceApplication', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }

  ngOnChanges(changes: {[ propName: string]: SimpleChange}) {
    if (changes['waiverFinanceApplicationTasks']){
        this.filteredData = changes['waiverFinanceApplicationTasks'].currentValue; 
        this.filteredTotal = changes['waiverFinanceApplicationTasks'].currentValue.length;
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
    let newData: any[] = this.waiverFinanceApplicationTasks;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

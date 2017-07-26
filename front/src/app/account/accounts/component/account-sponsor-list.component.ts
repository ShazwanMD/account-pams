import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import {Account} from '../../../shared/model/account/account.interface';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';
import {Actor} from '../../../shared/model/identity/actor.interface';

@Component({
  selector: 'pams-account-sponsor-list',
  templateUrl: './account-sponsor-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountSponsorListComponent {

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'name', label: 'Name'},
    {name: 'email', label: 'Email'},
    {name: 'action', label: ''},
  ];

  @Input() actor: Actor;
  @Input() accountSponsorList: Account[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService) {
  }

  viewAccount(account: Account) {
    console.log('account: ' + account.id);
    this.router.navigate(['/accounts-detail', account.id]);
  }

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  ngAfterViewInit(): void {
    this.filteredData = this.accountSponsorList;
    this.filteredTotal = this.accountSponsorList.length;
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
    let newData: any[] = this.accountSponsorList;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }
}

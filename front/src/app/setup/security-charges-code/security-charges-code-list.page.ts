import { SecurityChargesCodeEditorDialog } from './dialog/security-charges-code-editor.dialog';
import { SecurityChargesCode } from './../../common/security-charges-code/security-charges-code.interface';

import {
  Component,
  Input,
  EventEmitter,
  Output,
  ChangeDetectionStrategy,
  OnInit,
  AfterViewInit,
  ViewContainerRef,
} from '@angular/core';


import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-security-charges-code-list-page',
  templateUrl: './security-charges-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecurityChargesCodeListPage implements OnInit{

  private SECURITY_CHARGES_CODES = "setupModuleState.securityChargesCode".split(".");
  private securityChargesCodes$: Observable<SecurityChargesCode[]>;
  private creatorDialogRef: MdDialogRef<SecurityChargesCodeEditorDialog>;
  private columns: any[] = [
    {name: 'section', label: 'Section'},
    {name: 'description', label: 'Description'},
    {name: 'offense', label: 'Offense'},
    {name: 'offenseDescription', label: 'Offense Description'},
    {name: 'amountDescription', label: 'Amount Description'},
    {name: 'amount', label: 'Amount'},
    {name: 'active', label: 'Active'},
    {name: 'action', label: ''}
  ];

    private securityChargesCodes: SecurityChargesCode[];

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'section';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService) {
    this.securityChargesCodes$ = this.store.select(...this.SECURITY_CHARGES_CODES);
    this.securityChargesCodes$.subscribe(SecurityChargesCodes=>this.securityChargesCodes = SecurityChargesCodes)
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findSecurityChargesCodes());
    this.store.dispatch(this.actions.changeTitle("Security Charges Codes"));
  }

  createDialog(): void {
    this.showDialog(null);
  }

  editDialog(code:SecurityChargesCode): void {
    this.showDialog(code);
  }

  delete(code: SecurityChargesCode): void {
    this.store.dispatch(this.actions.removeSecurityChargesCode(code))
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
    console.log('filter');
    let newData: any[] = this.securityChargesCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  private showDialog(code:SecurityChargesCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SecurityChargesCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.securityChargesCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

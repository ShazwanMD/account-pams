import { SecurityChargeCodeEditorDialog } from './dialog/security-charge-code-editor.dialog';
import { SecurityChargeCode } from '../../common/security-charge-codes/security-charge-code.interface';

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

import {Store} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {SetupModuleState} from '../index';
import {Observable} from 'rxjs/Observable';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-security-charge-code-list-page',
  templateUrl: './security-charge-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecurityChargeCodeListPage implements OnInit{

  private SECURITY_CHARGE_CODES = 'setupModuleState.securityChargeCode'.split('.');
  private securityChargeCodes$: Observable<SecurityChargeCode[]>;
  private creatorDialogRef: MdDialogRef<SecurityChargeCodeEditorDialog>;
  private columns: any[] = [
    {name: 'section', label: 'Compound Section'},
    {name: 'description', label: 'Compound Description'},
    {name: 'offense', label: 'Compound Offense'},
    {name: 'offenseDescription', label: 'Compound Offense Description'},
    {name: 'amountDescription', label: 'Compound Amount Description'},
    {name: 'amount', label: 'Compound Amount'},
    {name: 'active', label: 'Compound Active'},
    {name: 'action', label: ''},
  ];

    private securityChargeCodes: SecurityChargeCode[];

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
    this.securityChargeCodes$ = this.store.select(...this.SECURITY_CHARGE_CODES);
    this.securityChargeCodes$.subscribe((SecurityChargeCodes) => this.securityChargeCodes = SecurityChargeCodes);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findSecurityChargeCodes());
    this.store.dispatch(this.actions.changeTitle('Security Charge Codes'));
  }

  createDialog(): void {
    this.showDialog(null);
  }

  editDialog(code: SecurityChargeCode): void {
    this.showDialog(code);
  }

  delete(code: SecurityChargeCode): void {
    this.store.dispatch(this.actions.removeSecurityChargeCode(code));
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
    let newData: any[] = this.securityChargeCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  private showDialog(code: SecurityChargeCode): void {
    console.log('create');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SecurityChargeCodeEditorDialog, config);
    if (code) this.creatorDialogRef.componentInstance.securityChargeCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

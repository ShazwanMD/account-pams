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
import { BankCodeEditorDialog } from './dialog/bank-code-editor.dialog';
import { BankCode } from '../../../shared/model/common/bank-code.interface';
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
  selector: 'pams-bank-list-page',
  templateUrl: './bank-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BankCodeListPage implements OnInit {

  private BANK_CODES = "setupModuleState.bankCodes".split(".");
  private bankCodes$: Observable<BankCode[]>;
  private creatorDialogRef: MdDialogRef<BankCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'name', label: 'Name'},
    {name: 'swiftCode', label: 'SwiftCode'},
    {name: 'ibgCode', label: 'IbgCode'},
    {name: 'action', label: ''}
  ];

private bankCodes: BankCode[];

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
               private _dataTableService: TdDataTableService) {
    this.bankCodes$ = this.store.select(...this.BANK_CODES);
    this.bankCodes$.subscribe(BankCodes=>this.bankCodes= BankCodes)
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findBankCodes());
    this.store.dispatch(this.actions.changeTitle("Bank Codes"))
  }

  createDialog(): void {
    this.showDialog(null);
  }

  editDialog(code:BankCode): void {
    this.showDialog(code);
  }

  delete(code: BankCode): void {
    this.store.dispatch(this.actions.removeBankCode(code))
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
    let newData: any[] = this.bankCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  private showDialog(code:BankCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(BankCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.bankCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

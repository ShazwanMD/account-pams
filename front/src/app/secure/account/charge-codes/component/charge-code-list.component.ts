import {
  AfterViewInit,
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  Output,
  ViewContainerRef
} from '@angular/core';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {Store} from '@ngrx/store';
import {ChargeCodeActions} from '../charge-code.action';
import {AccountModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {ChargeCodeEditorDialog} from '../dialog/charge-code-editor.dialog';
import {Observable} from 'rxjs/Observable';
import {
  IPageChangeEvent,
  ITdDataTableSortChangeEvent,
  TdDataTableService,
  TdDataTableSortingOrder
} from '@covalent/core';

@Component({
  selector: 'pams-charge-code-list',
  templateUrl: './charge-code-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ChargeCodeListComponent implements AfterViewInit {

  private CHARGE_CODES: string[] = 'accountModuleState.chargeCodes'.split('.');
  private chargeCodes$: Observable<ChargeCode[]>;
  private editorDialogRef: MdDialogRef<ChargeCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'priority', label: 'Priority'},
    {name: 'taxCode.code', label: 'Tax Code'},
    {name: 'taxCode.taxRate', label: 'Tax Rate'},
    {name: 'inclusive', label: 'Inclusive'},
    {name: 'action', label: ''},
  ];

  @Input() chargeCodes: ChargeCode[];
  @Output() view: EventEmitter<ChargeCode> = new EventEmitter<ChargeCode>();

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private store: Store<AccountModuleState>,
              private actions: ChargeCodeActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService) {
    this.chargeCodes$ = this.store.select(...this.CHARGE_CODES);
  }

  ngAfterViewInit(): void {
    this.filteredData = this.chargeCodes;
    this.filteredTotal = this.chargeCodes.length;
    this.filter();
  }

  editDialog(code: ChargeCode): void {
    this.showDialog(code);
  }

  delete(code: ChargeCode): void {
    this.store.dispatch(this.actions.removeChargeCode(code));
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
    let newData: any[] = this.chargeCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  private showDialog(code: ChargeCode): void {
    console.log('create');
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(ChargeCodeEditorDialog, config);
    if (code) {
      this.editorDialogRef.componentInstance.chargeCode = code;
    }
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

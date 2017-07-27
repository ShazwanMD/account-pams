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
import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import {Store} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {SetupModuleState} from '../index';
import {Observable} from 'rxjs/Observable';
import { TaxCode} from '../../../shared/model/common/tax-code.interface';
import { TaxCodeEditorDialog } from './dialog/tax-code-editor.dialog';
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-tax-list-page',
  templateUrl: './tax-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaxCodeListPage implements OnInit {

  private TAX_CODES = 'setupModuleState.taxCodes'.split('.');

  private taxCodes$: Observable<TaxCode[]>;
  private creatorDialogRef: MdDialogRef<TaxCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'taxRate', label: 'Tax Rate'},
    {name: 'action', label: ''},
  ];

  private taxCodes: TaxCode[];

  filteredData: any[];
  filteredTotal: number;
  searchTerm: string = '';
  fromRow: number = 1;
  currentPage: number = 1;
  pageSize: number = 10;
  sortBy: string = 'code';
  sortOrder: TdDataTableSortingOrder = TdDataTableSortingOrder.Descending;

  constructor(private actions: SetupActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private store: Store<SetupModuleState>,
              private _dataTableService: TdDataTableService){
    this.taxCodes$ = this.store.select(...this.TAX_CODES);
    this.taxCodes$.subscribe((TaxCodes) => this.taxCodes = TaxCodes);
  }

   ngOnInit(): void {
    this.store.dispatch(this.actions.findTaxCodes());
    this.store.dispatch(this.actions.changeTitle('Tax Codes'));
  }

   createDialog(): void {
     this.showDialog(null);
   }

   editDialog(code: TaxCode): void {
     this.showDialog(code);
   }

   delete(code: TaxCode): void {
     this.store.dispatch(this.actions.removeTaxCode(code));
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
    let newData: any[] = this.taxCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

   private showDialog(code: TaxCode): void {
     console.log('create');
     let config = new MdDialogConfig();
     config.viewContainerRef = this.vcf;
     config.role = 'dialog';
     config.width = '70%';
     config.height = '65%';
     config.position = {top: '0px'};
     this.creatorDialogRef = this.dialog.open(TaxCodeEditorDialog, config);
     if (code) this.creatorDialogRef.componentInstance.taxCode = code; // set
     this.creatorDialogRef.afterClosed().subscribe((res) => {
       console.log('close dialog');
     });
   }

}

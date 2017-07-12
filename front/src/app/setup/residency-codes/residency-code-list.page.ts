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
import { ResidencyCode } from './../../common/residency-codes/residency-code.interface';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import { ResidencyCodeEditorDialog } from "./dialog/residency-code-editor.dialog";
import {
  TdDataTableService,
  TdDataTableSortingOrder,
  ITdDataTableSortChangeEvent,
  IPageChangeEvent,
} from '@covalent/core';

@Component({
  selector: 'pams-residency-list-page',
  templateUrl: './residency-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ResidencyCodeListPage implements OnInit {

  private RESIDENCY_CODES = "setupModuleState.residencyCodes".split(".");
  
  private residencyCodes$:Observable<ResidencyCode[]>;
  private creatorDialogRef: MdDialogRef<ResidencyCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''}
  ];

  private residencyCodes: ResidencyCode[];

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
    this.residencyCodes$ = this.store.select(...this.RESIDENCY_CODES);
    this.residencyCodes$.subscribe(ResidencyCodes=>this.residencyCodes= ResidencyCodes)
  }
 
   ngOnInit(): void {
    this.store.dispatch(this.actions.findResidencyCodes())
    this.store.dispatch(this.actions.changeTitle("Residency Codes"))
  }

   createDialog(): void {
     this.showDialog(null);
   }

   editDialog(code:ResidencyCode): void {
     this.showDialog(code);
   }

   delete(code: ResidencyCode): void {
     this.store.dispatch(this.actions.removeResidencyCode(code))
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
    let newData: any[] = this.residencyCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

   private showDialog(code:ResidencyCode): void {
     console.log("create");
     let config = new MdDialogConfig();
     config.viewContainerRef = this.vcf;
     config.role = 'dialog';
     config.width = '70%';
     config.height = '65%';
     config.position = {top: '0px'};
     this.creatorDialogRef = this.dialog.open(ResidencyCodeEditorDialog, config);
     if(code) this.creatorDialogRef.componentInstance.residencyCode = code; // set
     this.creatorDialogRef.afterClosed().subscribe(res => {
       console.log("close dialog");
     });
   }

}

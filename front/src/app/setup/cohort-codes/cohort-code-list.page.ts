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
import { CohortCodeEditorDialog } from './dialog/cohort-code-editor.dialog';
import { CohortCode } from './../../common/cohort-codes/cohort-code.interface';
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
  selector: 'pams-cohort-list-page',
  templateUrl: './cohort-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CohortCodeListPage implements OnInit{

  private COHORT_CODES = "setupModuleState.cohortCodes".split(".");
  private cohortCodes$: Observable<CohortCode[]>;
  private creatorDialogRef: MdDialogRef<CohortCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''}
  ];

    private cohortCodes: CohortCode[];

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
    this.cohortCodes$ = this.store.select(...this.COHORT_CODES);
    this.cohortCodes$.subscribe(CohortCodes=>this.cohortCodes = CohortCodes)
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findCohortCodes());
    this.store.dispatch(this.actions.changeTitle("Cohort Codes"));
  }

  createDialog(): void {
    this.showDialog(null);
  }

  editDialog(code:CohortCode): void {
    this.showDialog(code);
  }

  delete(code: CohortCode): void {
    this.store.dispatch(this.actions.removeCohortCode(code))
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
    let newData: any[] = this.cohortCodes;
    newData = this._dataTableService.filterData(newData, this.searchTerm, true);
    this.filteredTotal = newData.length;
    newData = this._dataTableService.sortData(newData, this.sortBy, this.sortOrder);
    newData = this._dataTableService.pageData(newData, this.fromRow, this.currentPage * this.pageSize);
    this.filteredData = newData;
  }

  private showDialog(code:CohortCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(CohortCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.cohortCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

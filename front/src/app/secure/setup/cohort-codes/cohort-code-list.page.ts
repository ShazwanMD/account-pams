import { CohortCode } from './../../../shared/model/common/cohort-code.interface';
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
import {CohortCodeEditorDialog} from './dialog/cohort-code-editor.dialog';
@Component({
  selector: 'pams-cohort-list.page',
  templateUrl: './cohort-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CohortCodeListPage implements OnInit{
  private COHORT_CODES = "setupModuleState.cohortCodes".split(".");
  private cohortCodes$: Observable<CohortCode[]>;
  private creatorDialogRef: MdDialogRef<CohortCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.cohortCodes$ = this.store.select(...this.COHORT_CODES);
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

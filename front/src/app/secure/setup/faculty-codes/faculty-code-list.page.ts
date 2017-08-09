import { FacultyCode } from './../../../shared/model/common/faculty-code.interface';
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
import {FacultyCodeEditorDialog} from './dialog/faculty-code-editor.dialog';
@Component({
  selector: 'pams-faculty-list.page',
  templateUrl: './faculty-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FacultyCodeListPage implements OnInit{
  private FACULTY_CODES = "setupModuleState.facultyCodes".split(".");
  private facultyCodes$: Observable<FacultyCode[]>;
  private creatorDialogRef: MdDialogRef<FacultyCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.facultyCodes$ = this.store.select(...this.FACULTY_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findFacultyCodes());
    this.store.dispatch(this.actions.changeTitle("Faculty Codes"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:FacultyCode): void {
    this.showDialog(code);
  }
  delete(code: FacultyCode): void {
    this.store.dispatch(this.actions.removeFacultyCode(code))
  }
  
  private showDialog(code:FacultyCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(FacultyCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.facultyCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

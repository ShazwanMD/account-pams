import { ProgramCode } from './../../../shared/model/common/program-code.interface';
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
import {ProgramCodeEditorDialog} from './dialog/program-code-editor.dialog';
@Component({
  selector: 'pams-program-list.page',
  templateUrl: './program-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProgramCodeListPage implements OnInit{
  private PROGRAM_CODES = "setupModuleState.programCodes".split(".");
  private programCodes$: Observable<ProgramCode[]>;
  private creatorDialogRef: MdDialogRef<ProgramCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.programCodes$ = this.store.select(...this.PROGRAM_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findProgramCodes());
    this.store.dispatch(this.actions.changeTitle("Program Codes"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:ProgramCode): void {
    this.showDialog(code);
  }
  delete(code: ProgramCode): void {
    this.store.dispatch(this.actions.removeProgramCode(code))
  }
  
  private showDialog(code:ProgramCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(ProgramCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.programCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

import { ResidencyCode } from './../../../shared/model/common/residency-code.interface';
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
import {ResidencyCodeEditorDialog} from './dialog/residency-code-editor.dialog';
@Component({
  selector: 'pams-residency-list.page',
  templateUrl: './residency-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ResidencyCodeListPage implements OnInit{
  private RESIDENCY_CODES = "setupModuleState.residencyCodes".split(".");
  private residencyCodes$: Observable<ResidencyCode[]>;
  private creatorDialogRef: MdDialogRef<ResidencyCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.residencyCodes$ = this.store.select(...this.RESIDENCY_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findResidencyCodes());
    this.store.dispatch(this.actions.changeTitle("Residency Codes"));
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

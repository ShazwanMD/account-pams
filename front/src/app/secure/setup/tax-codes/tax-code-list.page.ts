import { TaxCode } from './../../../shared/model/common/tax-code.interface';
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
import {TaxCodeEditorDialog} from './dialog/tax-code-editor.dialog';
@Component({
  selector: 'pams-tax-list.page',
  templateUrl: './tax-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaxCodeListPage implements OnInit{
  private TAX_CODES = 'setupModuleState.taxCodes'.split('.');
  private taxCodes$: Observable<TaxCode[]>;
  private creatorDialogRef: MdDialogRef<TaxCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.taxCodes$ = this.store.select(...this.TAX_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findTaxCodes());
    this.store.dispatch(this.actions.changeTitle("Tax Codes"));
  }
  createDialog(): void {
    this.showDialog(null);
  }
  editDialog(code:TaxCode): void {
    this.showDialog(code);
  }
  delete(code: TaxCode): void {
    this.store.dispatch(this.actions.removeTaxCode(code))
  }
  
  private showDialog(code:TaxCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(TaxCodeEditorDialog, config);
    if(code) this.creatorDialogRef.componentInstance.taxCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

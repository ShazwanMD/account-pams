import { BankCode } from './../../../shared/model/common/bank-code.interface';
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
import {BankCodeEditorDialog} from './dialog/bank-code-editor.dialog';
@Component({
  selector: 'pams-bank-list.page',
  templateUrl: './bank-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BankCodeListPage implements OnInit{
  private BANK_CODES = "setupModuleState.bankCodes".split(".");
  private bankCodes$: Observable<BankCode[]>;
  private creatorDialogRef: MdDialogRef<BankCodeEditorDialog>;
  
  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
              
              
    this.bankCodes$ = this.store.select(...this.BANK_CODES);
  }
  ngOnInit(): void {
    this.store.dispatch(this.actions.findBankCodes());
    this.store.dispatch(this.actions.changeTitle("Bank Codes"));
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

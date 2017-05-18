import { BankCodeEditorDialog } from './dialog/bank-code-editor.dialog';
import { BankCode } from './../../common/bank-codes/bank-code.interface';
import {Component, OnInit, ViewContainerRef} from "@angular/core";
import {Store} from "@ngrx/store";
import {SetupActions} from "../setup.action";
import {SetupModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";

@Component({
  selector: 'pams-bank-list-page',
  templateUrl: './bank-code-list.page.html',
})
export class BankCodeListPage implements OnInit {

  private BANK_CODES = "setupModuleState.bankCodes".split(".");
  private bankCodes$: Observable<BankCode>;
  private creatorDialogRef: MdDialogRef<BankCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'name', label: 'Name'},
    {name: 'swiftCode', label: 'SwiftCode'},
    {name: 'ibgCode', label: 'IbgCode'},
    {name: 'action', label: ''}
  ];

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.bankCodes$ = this.store.select(...this.BANK_CODES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findBankCodes());
    this.store.dispatch(this.actions.changeTitle("Bank Codes"))
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

  filter(): void {
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
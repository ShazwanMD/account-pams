import {LoanChargeEditorDialog} from '../dialog/loan-charge-editor.dialog';
import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
@Component({
  selector: 'pams-loan-charge',
  templateUrl: './loan-charge.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoanChargeComponent implements OnInit {
  private editorDialogRef: MdDialogRef<LoanChargeEditorDialog>;
  private loanChargeColumns: any[] = [
    {name: 'chargeDate', label: 'Date of Charge'},
    {name: 'sourceNo', label: 'Source No'},
    {name: 'session.code', label: 'Session'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'action', label: ''},
  ];

  @Input() loanAccountCharges: AccountCharge[];
  @Input() account: Account;

  private selectedRows: AccountCharge[];

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.loanAccountCharges.filter((value) => value.selected);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.removeAccountCharge(this.account, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  edit(loanAccountCharges: AccountCharge): void {
    this.showDialog(loanAccountCharges);
  }

  selectRow(loanAccountCharges: AccountCharge): void {
  }

  selectAllRows(loanAccountCharges: AccountCharge[]): void {
  }

  showDialog(loanAccountCharges: AccountCharge): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(LoanChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account;
    if (loanAccountCharges) this.editorDialogRef.componentInstance.accountCharge = loanAccountCharges;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

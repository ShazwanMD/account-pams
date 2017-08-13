import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
@Component({
  selector: 'pams-security-charge',
  templateUrl: './security-charge.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecurityChargeComponent implements OnInit {
  private editorDialogRef: MdDialogRef<SecurityChargeEditorDialog>;
  private securityChargeColumns: any[] = [
    {name: 'chargeDate', label: 'Date of Charge'},
    {name: 'sourceNo', label: 'Source No'},
    {name: 'session.code', label: 'Session'},
    // {name: 'securityChargeCode.section', label: 'Section'},
    // {name: 'securityChargeCode.offense', label: 'Offense'},
    {name: 'securityChargeCode.offenseDescription', label: 'Description'},
    {name: 'securityChargeCode.amount', label: 'Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'action', label: ''},
    // {name: 'referenceNo', label: 'Reference No'},
  ];
  private selectedRows: AccountCharge[];

  @Input() securityAccountCharges: AccountCharge[];
  @Input() account: Account;

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.securityAccountCharges.filter((value) => value.selected);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.removeAccountCharge(this.account, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  edit(securityAccountCharges: AccountCharge): void {
    this.showDialog(securityAccountCharges);
  }

  selectRow(securityAccountCharges: AccountCharge): void {
  }

  selectAllRows(securityAccountCharges: AccountCharge[]): void {
  }

  showDialog(securityAccountCharges: AccountCharge): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(SecurityChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account;
    if (securityAccountCharges) this.editorDialogRef.componentInstance.accountCharge = securityAccountCharges;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

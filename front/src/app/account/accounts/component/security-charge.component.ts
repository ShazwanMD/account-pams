import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef} from '@angular/core';
import {AccountActions} from "../account.action";
import {Store} from '@ngrx/store';
import {AccountModuleState} from "../../index";
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {AccountCharge} from "../account-charge.interface";
import {Account} from "../account.interface";
@Component({
  selector: 'pams-security-charge',
  templateUrl: './security-charge.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecurityChargeComponent {
  private editorDialogRef: MdDialogRef<SecurityChargeEditorDialog>;

  @Input() accountCharge: AccountCharge;
  @Input() account: Account;

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }
  delete(): void {
      this.store.dispatch(this.actions.removeAccountCharge(this.accountCharge, this.account));
  }
  editDialog(): void {
    console.log('showDialog');
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(SecurityChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.accountCharge = this.accountCharge
    this.editorDialogRef.componentInstance.account = this.account;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }
}

import {Component, Input, OnInit, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {AccountCharge} from '../account-charge.interface';
import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Account} from '../account.interface';
import {AdmissionChargeEditorDialog} from '../dialog/admission-charge-editor.dialog';

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent implements OnInit {
  private securityCreatorDialogRef: MdDialogRef<SecurityChargeEditorDialog>;
  private admissionCreatorDialogRef: MdDialogRef<AdmissionChargeEditorDialog>;

  @Input() account: Account;
  @Input() admissionAccountCharges: AccountCharge[];
  @Input() securityAccountCharges: AccountCharge[];

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    // no op
  }

  filter(): void {
    // no op
  }

  showSecurityChargeDialog(charge: AccountCharge): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.securityCreatorDialogRef = this.dialog.open(SecurityChargeEditorDialog, config);
    this.securityCreatorDialogRef.componentInstance.account = this.account;
    this.securityCreatorDialogRef.afterClosed().subscribe((res) => {
      // no op
    });
  }

  showAdmissionChargeDialog(charge: AccountCharge): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.admissionCreatorDialogRef = this.dialog.open(AdmissionChargeEditorDialog, config);
    this.admissionCreatorDialogRef.componentInstance.account = this.account;
    this.admissionCreatorDialogRef.afterClosed().subscribe((res) => {
      // no op
    });
  }
}

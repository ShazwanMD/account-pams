import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Account} from '../../../../shared/model/account/account.interface';
import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';

@Component({
  selector: 'pams-account-sponsorship-list',
  templateUrl: './account-sponsorship-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountSponsorshipListComponent implements OnInit {
  private securityCreatorDialogRef: MdDialogRef<SecurityChargeEditorDialog>;

  @Input() account: Account;
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
}

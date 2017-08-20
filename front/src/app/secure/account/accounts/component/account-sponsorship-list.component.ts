import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Account} from '../../../../shared/model/account/account.interface';
import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
import { SponsorshipEditorDialog } from "../dialog/sponsorship-editor.dialog";
import { AccountSponsorship } from "../../../../shared/model/account/account-sponsorship.interface";

@Component({
  selector: 'pams-account-sponsorship-list',
  templateUrl: './account-sponsorship-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountSponsorshipListComponent implements OnInit {
  private sponsorshipCreatorDialogRef: MdDialogRef<SponsorshipEditorDialog>;

  @Input() account: Account;
  @Input() sponsorships: AccountSponsorship[];

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

  showSponsorshipEditorDialog(sponsorships: AccountSponsorship): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.sponsorshipCreatorDialogRef = this.dialog.open(SponsorshipEditorDialog, config);
    this.sponsorshipCreatorDialogRef.componentInstance.account = this.account;
    this.sponsorshipCreatorDialogRef.afterClosed().subscribe((res) => {
      // no op
    });
  }
}

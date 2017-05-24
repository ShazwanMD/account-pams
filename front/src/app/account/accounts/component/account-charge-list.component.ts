import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {AccountCharge} from "../account-charge.interface";
import {AdmissionChargeDialog} from "../dialog/admission-charge.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {AccountActions} from "../account.action";
import {AccountModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {Account} from "../account.interface";

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent {
  @Input() account: Account;
  @Input() charges: AccountCharge[];

  private creatorDialogRef: MdDialogRef<AdmissionChargeDialog>;
  private columns: any[] = [
    {name: 'chargeType', label: 'Type'},
    {name: 'session.code', label: 'Session'},
    {name: 'amount', label: 'Amount'},
    {name: 'invoiced', label: 'Invoiced'},
    {name: 'action', label: ''}
  ];

  constructor(private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  delete(account: Account, accountCharge: AccountCharge): void {
    this.store.dispatch(this.actions.removeAdmissionCharge(account, accountCharge))
  }

  showAdmissionChargeDialog(): void {
    console.log("show dialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '90%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(AdmissionChargeDialog, config);
    this.creatorDialogRef.componentInstance.account = this.account;
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

}

import {Component, Input, EventEmitter, OnInit, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AccountCharge} from "../account-charge.interface";
import {AdmissionChargeDialog} from "../dialog/admission-charge.dialog";
import {AdmissionChargeEditorDialog} from "../dialog/admission-charge-editor.dialog";
import {SecurityChargeEditorDialog} from "../dialog/security-charge-editor.dialog";
import {CompoundChargeEditorDialog} from "../dialog/compound-charge-editor.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {AccountActions} from "../account.action";
import {AccountModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {Account} from "../account.interface";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent implements OnInit {
	@Input() account: Account;
	@Input() charges: AccountCharge[];

	private creatorSecurityDialogRef: MdDialogRef<SecurityChargeEditorDialog>;

	private columns: any[] = [
     {name: 'sourceNo', label: 'Source No'},
     {name: 'referenceNo', label: 'Reference No'},
     {name: 'code', label: 'Section Code'},
     {name: 'description', label: 'Description'},
     {name: 'chargeType', label: 'Type'},
     {name: 'session.code', label: 'Session'},
     {name: 'cohort.code', label: 'Cohort'},
     {name: 'studyMode.code', label: 'Study Mode'},
     {name: 'amount', label: 'Amount'},
     {name: 'invoiced', label: 'Invoiced'},
     {name: 'chargeDate', label: 'Date of Charge'},
     {name: 'action', label: ''}
  ];

	constructor(private actions: AccountActions,
    private vcf: ViewContainerRef,
    private store: Store<AccountModuleState>,
    private dialog: MdDialog) {
  }
  ngOnInit(): void {
  }
  createSecurityCharge(): void {
    this.showDialog1(null);
  }

  filter(): void {
  }
  showDialog1(securityCharge: AccountCharge): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = { top: '65px' };
    this.creatorSecurityDialogRef = this.dialog.open(SecurityChargeEditorDialog, config);
    this.creatorSecurityDialogRef.componentInstance.account = this.account;
    this.creatorSecurityDialogRef.afterClosed().subscribe(res => {
    });
  }
}

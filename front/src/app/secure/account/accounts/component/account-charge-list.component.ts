import {Component, Input, OnInit, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Account} from '../../../../shared/model/account/account.interface';
import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {AdmissionChargeEditorDialog} from '../dialog/admission-charge-editor.dialog';
import {StudentAffairsChargeEditorDialog} from '../dialog/student-affairs-charge-editor.dialog';
import {LoanChargeEditorDialog} from '../dialog/loan-charge-editor.dialog';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent implements OnInit {
  private securityCreatorDialogRef: MdDialogRef<SecurityChargeEditorDialog>;
  private admissionCreatorDialogRef: MdDialogRef<AdmissionChargeEditorDialog>;
  private studentAffairsCreatorDialogRef: MdDialogRef<StudentAffairsChargeEditorDialog>;
  private loanCreatorDialogRef: MdDialogRef<LoanChargeEditorDialog>;

  @Input() account: Account;
  @Input() admissionAccountCharges: AccountCharge[];
  @Input() securityAccountCharges: AccountCharge[];
  @Input() studentAffairsAccountCharges: AccountCharge[];
  @Input() loanAccountCharges: AccountCharge[];


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

  showStudentAffairsChargeDialog(charge: AccountCharge): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.studentAffairsCreatorDialogRef = this.dialog.open(StudentAffairsChargeEditorDialog, config);
    this.studentAffairsCreatorDialogRef.componentInstance.account = this.account;
    this.studentAffairsCreatorDialogRef.afterClosed().subscribe((res) => {
      // no op
    });
  }

  showLoanChargeDialog(charge: AccountCharge): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.loanCreatorDialogRef = this.dialog.open(LoanChargeEditorDialog, config);
    this.loanCreatorDialogRef.componentInstance.account = this.account;
    this.loanCreatorDialogRef.afterClosed().subscribe((res) => {
      // no op
    });
  }
}

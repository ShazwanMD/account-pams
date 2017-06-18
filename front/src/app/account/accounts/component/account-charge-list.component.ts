import {Component, Input, EventEmitter, OnInit, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {AccountCharge} from '../account-charge.interface';
import {AdmissionChargeEditorDialog} from '../dialog/admission-charge-editor.dialog';
import {CompoundChargeEditorDialog} from '../dialog/compound-charge-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Account} from '../account.interface';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent implements OnInit {

  private ADMISSION_CHARGES: string[] = 'accountModuleState.accountCharge'.split('.');
  private COMPOUND_CHARGES: string[] = 'accountModuleState.compoundCharges'.split('.');
  private admissionCharges$: Observable<AccountCharge[]>;
  private compoundCharges$: Observable<AccountCharge[]>;
  private editorDialogRef: MdDialogRef<AdmissionChargeEditorDialog>;
  private editorCompoundDialogRef: MdDialogRef<CompoundChargeEditorDialog>;
  private selectedRows: AccountCharge[];
  private columns: any[] = [
    {name: 'sourceNo', label: 'Source No'},
    {name: 'referenceNo', label: 'Reference No'},
    {name: 'description', label: 'Description'},
    {name: 'chargeType', label: 'Type'},
    {name: 'session.code', label: 'Session'},
    {name: 'amount', label: 'Amount'},
    {name: 'invoiced', label: 'Invoiced'},
    {name: 'action', label: ''},
  ];

  @Input() account: Account;
  @Input() charges: AccountCharge[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.admissionCharges$ = this.store.select(...this.ADMISSION_CHARGES);
    this.compoundCharges$ = this.store.select(...this.COMPOUND_CHARGES);
  }

  ngOnInit(): void {
    this.selectedRows = this.charges.filter((value) => value.selected);
  }

  edit(accountCharge: AccountCharge): void {
    this.showAdmissionDialog(accountCharge);
  }

  delete(account: Account, accountCharge: AccountCharge): void {
    this.store.dispatch(this.actions.removeAccountCharge(account, accountCharge));
    this.selectedRows = [];
  }

  filter(): void {
  }

  addAdmission(): void {
    this.showAdmissionDialog(null);
  }

  selectRow(accountCharge: AccountCharge): void {
  }

  selectAllRows(accountCharge: AccountCharge[]): void {
  }

  showAdmissionDialog(admissionCharge: AccountCharge): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '70%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(AdmissionChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account;
    if (admissionCharge) this.editorDialogRef.componentInstance.accountCharge = admissionCharge;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      this.selectedRows = [];
    });
  }

  addCompound(): void {
    this.CompoundChargeDialog(null);
  }

  CompoundChargeDialog(compoundCharge: AccountCharge): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '70%';
    config.position = {top: '65px'};
    this.editorCompoundDialogRef = this.dialog.open(CompoundChargeEditorDialog, config);
    this.editorCompoundDialogRef.componentInstance.account = this.account;
    if (compoundCharge) this.editorCompoundDialogRef.componentInstance.accountCharge = compoundCharge;
    this.editorCompoundDialogRef.afterClosed().subscribe((res) => {
      this.selectedRows = [];
    });
  }
}

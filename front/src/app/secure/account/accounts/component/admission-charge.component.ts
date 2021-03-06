import {AdmissionChargeEditorDialog} from '../dialog/admission-charge-editor.dialog';
import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
@Component({
  selector: 'pams-admission-charge',
  templateUrl: './admission-charge.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdmissionChargeComponent implements OnInit {
  private editorDialogRef: MdDialogRef<AdmissionChargeEditorDialog>;
  private admissionChargeColumns: any[] = [
    {name: 'chargeDate', label: 'Date of Charge'},
    {name: 'session.code', label: 'Session'},
    {name: 'taxCode.code', label: 'Tax Code'},
    {name: 'sourceNo', label: 'Source No'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Amount'},
    {name: 'taxAmount', label: 'Tax Amount'},
    {name: 'inclusive', label: 'Inclusive'},
    {name: 'netAmount', label: 'Net Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'action', label: ''},
  ];
  private selectedRows: AccountCharge[];

  @Input() admissionAccountCharges: AccountCharge[];
  @Input() account: Account;

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.admissionAccountCharges.filter((value) => value.selected);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.removeAccountCharge(this.account, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  edit(admissionAccountCharges: AccountCharge): void {
    this.showDialog(admissionAccountCharges);
  }

  selectRow(admissionAccountCharges: AccountCharge): void {
  }

  selectAllRows(admissionAccountCharges: AccountCharge[]): void {
  }

  showDialog(admissionAccountCharges: AccountCharge): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(AdmissionChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account;
    if (admissionAccountCharges) this.editorDialogRef.componentInstance.accountCharge = admissionAccountCharges;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

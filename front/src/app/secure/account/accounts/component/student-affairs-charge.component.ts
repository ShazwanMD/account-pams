import {StudentAffairsChargeEditorDialog} from '../dialog/student-affairs-charge-editor.dialog';
import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
@Component({
  selector: 'pams-student-affairs-charge',
  templateUrl: './student-affairs-charge.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class StudentAffairsChargeComponent implements OnInit {
  private editorDialogRef: MdDialogRef<StudentAffairsChargeEditorDialog>;
  private studentAffairsChargeColumns: any[] = [
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
  @Input() studentAffairsAccountCharges: AccountCharge[];
  @Input() account: Account;

  private selectedRows: AccountCharge[];

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.studentAffairsAccountCharges.filter((value) => value.selected);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.removeAccountCharge(this.account, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  edit(studentAffairsAccountCharges: AccountCharge): void {
    this.showDialog(studentAffairsAccountCharges);
  }

  selectRow(studentAffairsAccountCharges: AccountCharge): void {
  }

  selectAllRows(studentAffairsAccountCharges: AccountCharge[]): void {
  }

  showDialog(studentAffairsAccountCharges: AccountCharge): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(StudentAffairsChargeEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account;
    if (studentAffairsAccountCharges) this.editorDialogRef.componentInstance.accountCharge = studentAffairsAccountCharges;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

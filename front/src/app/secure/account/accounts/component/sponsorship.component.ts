import { AccountSponsorship } from './../../../../shared/model/account/account-sponsorship.interface';
import {SecurityChargeEditorDialog} from '../dialog/security-charge-editor.dialog';
import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
import { SponsorshipEditorDialog } from "../dialog/sponsorship-editor.dialog";
@Component({
  selector: 'pams-sponsorship',
  templateUrl: './sponsorship.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SponsorshipComponent implements OnInit {
  private editorDialogRef: MdDialogRef<SponsorshipEditorDialog>;
  private Columns: any[] = [
    {name: 'referenceNo', label: 'Reference No'},
    {name: 'session.code', label: 'Session'},   
    {name: 'sponsor.name', label: 'Sponsor'},
    {name: 'accountNo', label: 'Sponsor Acc No'},
    {name: 'startDate', label: 'Start Date'},
    {name: 'endDate', label: 'End Date'},
    {name: 'amount', label: 'Amount'},
   // {name: 'sponsorType', label: 'Sponsor Type'},
  ];
  private selectedRows: AccountSponsorship[];

  @Input() sponsorships: AccountSponsorship[];
  @Input() account: Account;

  constructor(private actions: AccountActions,
              private vcf: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.sponsorships.filter((value) => value.selected);
  }

  delete(): void {
    console.log('length: ' + this.selectedRows.length);
    for (let i: number = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.removeSponsorship(this.account, this.selectedRows[i]));
    }
    this.selectedRows = [];
  }

  edit(sponsorships: AccountSponsorship): void {
    this.showDialog(sponsorships);
  }

  selectRow(sponsorships: AccountSponsorship): void {
  }

  selectAllRows(sponsorships: AccountSponsorship[]): void {
  }

  showDialog(sponsorships: AccountSponsorship): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(SponsorshipEditorDialog, config);
    this.editorDialogRef.componentInstance.account = this.account;
    if (sponsorships) this.editorDialogRef.componentInstance.sponsorship = sponsorships;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

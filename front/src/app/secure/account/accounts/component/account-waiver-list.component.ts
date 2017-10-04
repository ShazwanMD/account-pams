import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog} from '@angular/material';
import {Store} from '@ngrx/store';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {AccountWaiver} from '../../../../shared/model/account/account-waiver.interface';

@Component({
  selector: 'pams-account-waiver-list',
  templateUrl: './account-waiver-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountWaiverListComponent implements OnInit {

  private columns: any[] = [
    {name: 'sourceNo', label: 'Source No'},
    {name: 'amount', label: 'Amount'},
    {name: 'session.code', label: 'Session'},
    {name: 'waiverType', label: 'Type'},
    {name: 'status', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() waivers: AccountWaiver[];
  @Input() account: Account;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {

  }

}

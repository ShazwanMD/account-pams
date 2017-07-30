import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {TdDialogService} from '@covalent/core';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-account-action',
  templateUrl: './account-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActionComponent {

  @Input() account: Account;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private actions: AccountActions) {
  }

  reviseAccount(): void {
    console.log('revise account');
    this.store.dispatch(this.actions.reviseAccount(this.account));
  }
}

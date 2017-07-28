import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {Account} from '../../../../shared/model/account/account.interface';
import {Actor} from '../../../../shared/model/identity/actor.interface';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Router} from '@angular/router';

@Component({
  selector: 'pams-account-creator',
  templateUrl: './account-creator.dialog.html',
})

export class AccountCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private store: Store<AccountModuleState>,
              private actions: AccountActions,
              private router: Router,
              private dialog: MdDialogRef<AccountCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      id: undefined,
      code: '',
      description: '',
      name: '',
      email: '',
      actor: <Actor>{},
    });
  }

  save(account: Account, isValid: boolean) {
    console.log('account: ' + account.name);
    this.store.dispatch(this.actions.saveAccount(account));
    this.dialog.close();
  }
}

import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Account} from '../../../../shared/model/account/account.interface';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';

@Component({
  selector: 'pams-compound-charge',
  templateUrl: './compound-charge.dialog.html',
})
export class CompoundChargeDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _account: Account;
  private _accountCharge: AccountCharge;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CompoundChargeDialog>,
              private store: Store<AccountModuleState>,
              private actions: AccountActions) {
  }

  set account(value: Account) {
    this._account = value;

  }

  set accountCharge(value: AccountCharge) {
    this.accountCharge = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group({
      id: undefined,
      referenceNo: '',
      sourceNo: '',
      description: '',
      amount: 0,
      compoundCode: '',
      compoundDescription: '',
    });
  }

  save(charge: AccountCharge, isValid: boolean) {
    console.log('account: ' + charge.amount);
    this.store.dispatch(this.actions.addAccountCharge(this._account, charge));
    this.dialog.close();
  }
}

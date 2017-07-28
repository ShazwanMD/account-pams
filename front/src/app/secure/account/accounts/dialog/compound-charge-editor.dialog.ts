import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {ActivatedRoute, Router} from '@angular/router';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';

@Component({
  selector: 'pams-compound-charge-editor',
  templateUrl: './compound-charge-editor.dialog.html',
})

export class CompoundChargeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _account: Account;
  private _accountCharge: AccountCharge;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CompoundChargeEditorDialog>,
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

    if (this.edit) {
      this.editorForm.patchValue(this._accountCharge);
    }
  }

  submit(charge: AccountCharge, isValid: boolean): void {
    if (this.edit) {
      this.store.dispatch(this.actions.updateAccountCharge(this._account, charge));
    } else {
      this.store.dispatch(this.actions.addAccountCharge(this._account, charge));
    }
    this.dialog.close();
  }
}


import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountChargeType} from '../../../../shared/model/account/account-charge-type.enum';

@Component({
  selector: 'pams-sponsorship-editor',
  templateUrl: './sponsorship-editor.dialog.html',
})
export class SponsorshipEditorDialog implements OnInit {
  private _account: Account;
  private _accountCharge: AccountCharge;
  private editForm: FormGroup;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private actions: AccountActions,
              private dialog: MdDialogRef<SponsorshipEditorDialog>) {
  }

  set accountCharge(value: AccountCharge) {
    this._accountCharge = value;
    this.edit = true;
  }

  set account(value: Account) {
    this._account = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: undefined,
      // referenceNo: '',
      // sourceNo: '',
      // description: '',
      amount: 0,
      startDate: undefined,
      endDate:undefined,
    });

    if (this.edit) {
      this.editForm.patchValue(this._accountCharge);
    }
  }

  submit(accountCharge: AccountCharge, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateAccountCharge(this._account, accountCharge));
    else  this.store.dispatch(this.actions.addAccountCharge(this._account, accountCharge));
    this.dialog.close();
  }
}
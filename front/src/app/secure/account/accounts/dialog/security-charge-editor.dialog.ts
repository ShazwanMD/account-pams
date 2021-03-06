import { TaxCode } from './../../../../shared/model/common/tax-code.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {AccountModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountChargeType} from '../../../../shared/model/account/account-charge-type.enum';
import { SecurityChargeCode } from "../../../../shared/model/common/security-charge-code.interface";

@Component({
  selector: 'pams-security-charge-editor',
  templateUrl: './security-charge-editor.dialog.html',
})
export class SecurityChargeEditorDialog implements OnInit {
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
              private dialog: MdDialogRef<SecurityChargeEditorDialog>) {
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
      id: [undefined],
      referenceNo: [''],
      sourceNo: ['',Validators.required],
      description:  [''],
      amount: [0],
      chargeDate: [undefined,Validators.required],
      chargeType: AccountChargeType.SECURITY,
      securityChargeCode: [<SecurityChargeCode>{}],
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

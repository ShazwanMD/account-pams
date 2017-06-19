import {Account} from "../account.interface";
import {AccountCharge} from "../account-charge.interface";
import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {AccountModuleState} from "../../index";
import {MdDialogRef} from "@angular/material";
import {AccountActions} from "../account.action";

@Component({
  selector: 'pams-security-charge-editor',
  templateUrl: './security-charge-editor.dialog.html',
})
export class SecurityChargeEditorDialog implements OnInit {
  private _account : Account;
  private _accountCharge : AccountCharge;
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
      id:undefined,
      sourceNo: '',
      description:'', 
      amount: 0,
      ordinal: 0,
      code: '',
      doc: null,
 
        });
    if (this.edit) this.editForm.patchValue(this._accountCharge);
  }
  submit(accountCharge: AccountCharge, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateAccountCharge(this._account, accountCharge));
    else  this.store.dispatch(this.actions.addAccountCharge(this._account, accountCharge));
    this.dialog.close();
  }
}
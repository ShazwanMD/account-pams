import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {StudyMode} from '../../../../shared/model/common/study-mode.interface';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {CohortCode} from '../../../../shared/model/common/cohort-code.interface';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountCharge} from '../../../../shared/model/account/account-charge.interface';
import {AccountChargeType} from '../../../../shared/model/account/account-charge-type.enum';

@Component({
  selector: 'pams-loan-charge-editor',
  templateUrl: './loan-charge-editor.dialog.html',
})

export class LoanChargeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _account: Account;
  private _accountCharge: AccountCharge;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<LoanChargeEditorDialog>,
              private store: Store<AccountModuleState>,
              private actions: AccountActions) {
  }

  set account(value: Account) {
    this._account = value;

  }

  set accountCharge(value: AccountCharge) {
    this._accountCharge = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group({
      id:[undefined],
      referenceNo: [''],
      sourceNo: ['',Validators.required],
      description: ['',Validators.required],
      amount: [0,Validators.required],
      chargeDate:[undefined,Validators.required],
      chargeType: AccountChargeType.LOAN,
      studyMode: <StudyMode>{},
      cohortCode: <CohortCode>{},
      session: <AcademicSession>{},
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


import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {StudyMode} from '../../../common/study-modes/study-mode.interface';
import {AcademicSession} from '../../academic-sessions/academic-session.interface';
import {Account} from '../account.interface';
import {CohortCode} from '../../../common/cohort-codes/cohort-code.interface';
import {Router, ActivatedRoute} from '@angular/router';
import {AccountCharge} from '../account-charge.interface';
import {AccountChargeType} from '../account-charge-type.enum';

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
      id: undefined,
      referenceNo:'',
      sourceNo: '',
      description: '',
      amount: 0,
      chargeDate: undefined,
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

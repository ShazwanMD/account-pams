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
import {AccountCharge} from '../account-charge.interface';

@Component({
  selector: 'pams-admission-charge',
  templateUrl: './admission-charge.dialog.html',
})
export class AdmissionChargeDialog implements OnInit {

  private createForm: FormGroup;
  private _account: Account;

  constructor(private formBuilder: FormBuilder,
              private store: Store<AccountModuleState>,
              private actions: AccountActions,
              private dialog: MdDialogRef<AdmissionChargeDialog>) {
  }

  set account(value: Account) {
    this._account = value;
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      id: undefined,
      referenceNo: '',
      sourceNo: '',
      description: '',
      amount: 0,
      studyMode: <StudyMode>{},
      cohortCode: <CohortCode>{},
      session: <AcademicSession>{},
    });
  }

  save(charge: AccountCharge, isValid: boolean) {
    console.log('account: ' + charge.amount);
    this.store.dispatch(this.actions.addAccountCharge(this._account, charge));
    this.dialog.close();
  }
}

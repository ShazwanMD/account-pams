import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {WaiverFinanceApplicationActions} from '../waiver-finance-application.action';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import { WaiverFinanceApplication } from '../../../../shared/model/billing/waiver-finance-application.interface';

@Component({
  selector: 'pams-waiver-application-editor',
  templateUrl: './waiver-application-editor.dialog.html',
})

export class WaiverApplicationEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _waiverFinanceApplication: WaiverFinanceApplication;
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<WaiverApplicationEditorDialog>) {
  }
  
  set application(value: WaiverFinanceApplication) {
      this._waiverFinanceApplication = value;
      this.edit = true;
    }

  ngOnInit(): void {
      
    this.editForm = this.formBuilder.group(<WaiverFinanceApplication>{
      referenceNo: '',
      sourceNo: '',
      description: '',
      reason: '',
      memo: '',
      balance: 0,
      effectiveBalance: 0,
      waivedAmount: 0,
      gracedAmount: 0,
      account: <Account>{},
      academicSession: <AcademicSession>{},
    });

    if (this.edit) this.editForm.patchValue(this._waiverFinanceApplication);
  }

  save(waiverFinanceApplication: WaiverFinanceApplication, isValid: boolean) {
    console.log("waiver updated" + this._waiverFinanceApplication.referenceNo);
    this.store.dispatch(this.actions.updateWaiverFinanceApplication(waiverFinanceApplication));
    this.dialog.close();
  }
}

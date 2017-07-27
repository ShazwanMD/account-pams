import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationActions} from '../waiver-application.action';
import {AcademicSession} from '../../../../shared/model/account/academic-session.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';

@Component({
  selector: 'pams-waiver-application-editor',
  templateUrl: './waiver-application-editor.dialog.html',
})

export class WaiverApplicationEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _application: WaiverApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: WaiverApplicationActions,
              private dialog: MdDialogRef<WaiverApplicationEditorDialog>) {
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<WaiverApplication>{
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

    if (this._application) this.editForm.patchValue(this._application);
  }

  set application(value: WaiverApplication) {
    this._application = value;
  }

  save(waiverApplication: WaiverApplication, isValid: boolean) {
    this.store.dispatch(this.actions.updateWaiverApplication(waiverApplication));
    this.dialog.close();
  }
}

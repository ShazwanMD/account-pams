import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
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
  private edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: WaiverApplicationActions,
              private dialog: MdDialogRef<WaiverApplicationEditorDialog>) {
  }
  
  set application(value: WaiverApplication) {
      this._application = value;
      this.edit = true;
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

    if (this.edit) this.editForm.patchValue(this._application);
  }

  save(application: WaiverApplication, isValid: boolean) {
    console.log("waiver updated" + this._application.referenceNo);
    this.store.dispatch(this.actions.updateWaiverApplication(application));
    this.dialog.close();
  }
}

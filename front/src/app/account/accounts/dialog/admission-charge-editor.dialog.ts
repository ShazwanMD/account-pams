import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {AccountActions} from "../account.action";
import {AccountModuleState} from "../../index";
import {AdmissionCharge} from "../admission-charge.interface";
import {StudyMode} from "../../../common/study-modes/study-mode.interface";
import {AcademicSession} from "../../academic-sessions/academic-session.interface";
import {Account} from "../account.interface";
import {AccountCharge} from "../account-charge.interface";
import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'pams-admission-charge-editor',
  templateUrl: './admission-charge-editor.dialog.html',
})

export class AdmissionChargeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _accountCharge  : AccountCharge;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<AdmissionChargeEditorDialog>,
              private store: Store<AccountModuleState>,
              private actions: AccountActions) {
  }

  set admissionCharge(value: AdmissionCharge) {
    this._accountCharge = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<AdmissionCharge>{
      id:null,
      referenceNo: '',
      sourceNo: '',
      description:'',
      amount: 0,
      studyMode: <StudyMode>{},
      cohortCode:<CohortCode>{},
      session: <AcademicSession>{}
    });


    if (this.edit) this.editorForm.patchValue(this._accountCharge);
  }

  submit(account: Account, charge: AdmissionCharge, isValid: boolean) {
    if (!charge.id) this.store.dispatch(this.actions.addAdmissionCharge(account, charge));
    else  this.store.dispatch(this.actions.updateAdmissionCharge(account, charge));
    this.dialog.close();
  }
}
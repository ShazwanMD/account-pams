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
import {CompoundCharge} from "../compound-charge.interface";
import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'pams-admission-charge-editor',
  templateUrl: './admission-charge-editor.dialog.html',
})

export class CompoundChargeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _account  : Account;
  private _compoundCharge  : CompoundCharge;


  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CompoundChargeEditorDialog>,
              private store: Store<AccountModuleState>,
              private actions: AccountActions) {
  }

  set account(value: Account) {
    this._account = value;
    
  }
  
  set compoundCharge(value: CompoundCharge) {
    this.compoundCharge = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<CompoundCharge>{
      id:null,
      compoundCode : '',
      compoundDescription : '',
    });
    if (this.edit) this.editorForm.patchValue(this._compoundCharge);
  }



  submit(charge: CompoundCharge, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateAdmissionCharge(this._account, charge));
    else  this.store.dispatch(this.actions.addAccountCharge(this._account, charge));
    this.dialog.close();
  }
}
 


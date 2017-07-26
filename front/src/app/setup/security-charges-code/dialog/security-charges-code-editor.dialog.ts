import { SecurityChargesCode } from './../../../common/security-charges-code/security-charges-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {SetupModuleState} from "../../index";
import {SetupActions} from "../../setup.action";


@Component({
  selector: 'pams-security-charges-code-editor',
  templateUrl: './security-charges-code-editor.dialog.html',
})

export class SecurityChargesCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _securityChargesCode: SecurityChargesCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<SecurityChargesCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set securityChargesCode(value: SecurityChargesCode) {
    this._securityChargesCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<SecurityChargesCode>{
        id: null,
        section:'',
        description:'',
        offense:'',
        offenseDescription:'',
        amount:0,
        amountDescription: '',
        active: false,

    });

    if (this.edit) this.editorForm.patchValue(this._securityChargesCode);
  }

  // submit(code: SecurityChargesCode, isValid: boolean) {
  //   if (!code.id) this.store.dispatch(this.actions.saveSecurityChargesCode(code));
  //   else  this.store.dispatch(this.actions.updateSecurityChargesCode(code.id));
  //   this.dialog.close();
  // }

    submit(code: SecurityChargesCode, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateSecurityChargesCode(code));
    else  this.store.dispatch(this.actions.saveSecurityChargesCode(code));
    this.dialog.close();
  }
}
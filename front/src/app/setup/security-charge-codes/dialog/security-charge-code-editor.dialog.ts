import { SecurityChargeCode } from '../../../common/security-charge-codes/security-charge-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-security-charge-code-editor',
  templateUrl: './security-charge-code-editor.dialog.html',
})

export class SecurityChargeCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _securityChargeCode: SecurityChargeCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<SecurityChargeCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set securityChargeCode(value: SecurityChargeCode) {
    this._securityChargeCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<SecurityChargeCode>{
        id: null,
        section: '',
        description: '',
        offense: '',
        offenseDescription: '',
        amount: 0,
        amountDescription: '',
        active: false,

    });

    if (this.edit) this.editorForm.patchValue(this._securityChargeCode);
  }

  // submit(code: SecurityChargeCode, isValid: boolean) {
  //   if (!code.id) this.store.dispatch(this.actions.saveSecurityChargeCode(code));
  //   else  this.store.dispatch(this.actions.updateSecurityChargeCode(code.id));
  //   this.dialog.close();
  // }

    submit(code: SecurityChargeCode, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateSecurityChargeCode(code));
    else  this.store.dispatch(this.actions.saveSecurityChargeCode(code));
    this.dialog.close();
  }
}

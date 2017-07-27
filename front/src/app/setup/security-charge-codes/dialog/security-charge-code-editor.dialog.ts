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

export class SecurityChargesCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _securityChargesCode: SecurityChargeCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<SecurityChargesCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set securityChargesCode(value: SecurityChargeCode) {
    this._securityChargesCode = value;
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

    if (this.edit) this.editorForm.patchValue(this._securityChargesCode);
  }

  // submit(code: SecurityChargesCode, isValid: boolean) {
  //   if (!code.id) this.store.dispatch(this.actions.saveSecurityChargesCode(code));
  //   else  this.store.dispatch(this.actions.updateSecurityChargesCode(code.id));
  //   this.dialog.close();
  // }

    submit(code: SecurityChargeCode, isValid: boolean) {
    if (this.edit) this.store.dispatch(this.actions.updateSecurityChargesCode(code));
    else  this.store.dispatch(this.actions.saveSecurityChargesCode(code));
    this.dialog.close();
  }
}

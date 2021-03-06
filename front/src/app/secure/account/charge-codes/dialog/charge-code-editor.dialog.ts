import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {SetupModuleState} from '../../../setup/index';
import {ChargeCodeActions} from '../charge-code.action';
import {TaxCode} from '../../../../shared/model/common/tax-code.interface';

@Component({
  selector: 'pams-charge-code-editor',
  templateUrl: './charge-code-editor.dialog.html',
})

export class ChargeCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _chargeCode: ChargeCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<ChargeCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: ChargeCodeActions) {
  }

  set chargeCode(value: ChargeCode) {
    this._chargeCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group({
      id: [null],
      code: ['',Validators.required],
      description: ['',Validators.required],
      priority: [0],
      taxCode: [<TaxCode>{}],
      inclusive: [false],
      active: [false],
    });

    if (this.edit) this.editorForm.patchValue(this._chargeCode);
  }

  submit(code: ChargeCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveChargeCode(code));
    else  this.store.dispatch(this.actions.updateChargeCode(code));
    this.dialog.close();
  }
}

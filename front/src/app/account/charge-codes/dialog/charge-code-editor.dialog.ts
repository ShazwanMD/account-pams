import { OnInit, Component, ViewContainerRef } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { ChargeCode } from '../../../shared/model/account/charge-code.interface';
import { Router, ActivatedRoute } from '@angular/router';
import { MdDialogRef } from '@angular/material';
import { Store } from '@ngrx/store';
import { SetupModuleState } from '../../../setup/index';
import { ChargeCodeActions } from '../charge-code.action';
import {TaxCode} from '../../../common/tax-codes/tax-code.interface';

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
    this.editorForm = this.formBuilder.group(<ChargeCode>{
      id: null,
      code: '',
      description: '',
      priority: 0,
      taxCode: <TaxCode>{},
    });

    if (this.edit) this.editorForm.patchValue(this._chargeCode);
  }

  submit(code: ChargeCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveChargeCode(code));
    else  this.store.dispatch(this.actions.updateChargeCode(code));
    this.dialog.close();
  }
}

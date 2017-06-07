import {ResidencyCode} from './../../../common/residency-codes/residency-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {SetupModuleState} from "../../index";
import {SetupActions} from "../../setup.action";
import { TaxCode } from "../../../common/tax-codes/tax-code.interface";


@Component({
  selector: 'pams-tax-code-editor',
  templateUrl: './tax-code-editor.dialog.html',
})

export class TaxCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _taxCode: TaxCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<TaxCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set taxCode(value: TaxCode) {
    this._taxCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<TaxCode>{
      id: null,
      code: '',
      description: '',
      accrualType: '',
      purposeType: '',
      rate: '',
      taxRate: '',
      taxType: '',
    });

    if (this.edit) this.editorForm.patchValue(this._taxCode);
  }

  submit(code: TaxCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveTaxCode(code));
    else  this.store.dispatch(this.actions.updateTaxCode(code));
    this.dialog.close();
  }
}
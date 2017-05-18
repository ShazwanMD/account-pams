import { BankCode } from './../../../common/bank-codes/bank-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {SetupModuleState} from "../../index";
import {SetupActions} from "../../setup.action";



@Component({
  selector: 'pams-bank-code-editor',
  templateUrl: './bank-code-editor.dialog.html',
})

export class BankCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _bankCode: BankCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<BankCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set bankCode(value: BankCode) {
    this._bankCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<BankCode>{
      id: null,
      code: '',
      name: '',
      swiftCode: '',
      ibgCode: '',
    });

    if (this.edit) this.editorForm.patchValue(this._bankCode);
  }

  submit(code: BankCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveBankCode(code));
    else  this.store.dispatch(this.actions.updateBankCode(code));
    this.dialog.close();
  }
}

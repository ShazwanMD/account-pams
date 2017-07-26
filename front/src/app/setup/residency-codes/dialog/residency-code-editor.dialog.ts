import {ResidencyCode} from './../../../common/residency-codes/residency-code.interface';
import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-residency-code-editor',
  templateUrl: './residency-code-editor.dialog.html',
})

export class ResidencyCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _residencyCode: ResidencyCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<ResidencyCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set residencyCode(value: ResidencyCode) {
    this._residencyCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<ResidencyCode>{
      id: null,
      code: '',
      description: '',
    });

    if (this.edit) this.editorForm.patchValue(this._residencyCode);
  }

  submit(code: ResidencyCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveResidencyCode(code));
    else  this.store.dispatch(this.actions.updateResidencyCode(code));
    this.dialog.close();
  }
}
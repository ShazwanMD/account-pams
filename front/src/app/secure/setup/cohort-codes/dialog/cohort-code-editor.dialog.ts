import {Component, ViewContainerRef, OnInit, AfterViewInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {CohortCode} from '../../../../shared/model/common/cohort-code.interface';

@Component({
  selector: 'pams-cohort-code-editor',
  templateUrl: './cohort-code-editor.dialog.html',
})

export class CohortCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _cohortCode: CohortCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CohortCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set cohortCode(value: CohortCode) {
    this._cohortCode = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<CohortCode>{
      id: null,
      code: '',
      description: '',
    });

    if (this.edit) this.editorForm.patchValue(this._cohortCode);
  }

  submit(code: CohortCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveCohortCode(code));
    else  this.store.dispatch(this.actions.updateCohortCode(code));
    this.dialog.close();
  }
}

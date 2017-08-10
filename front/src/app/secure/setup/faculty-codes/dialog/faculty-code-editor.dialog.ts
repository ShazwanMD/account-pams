import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {FacultyCode} from '../../../../shared/model/common/faculty-code.interface';

@Component({
  selector: 'pams-faculty-code-editor',
  templateUrl: './faculty-code-editor.dialog.html',
})

export class FacultyCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _facultyCode: FacultyCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<FacultyCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set facultyCode(value: FacultyCode) {
    this._facultyCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.editorForm = this.formBuilder.group(<FacultyCode>{
      id: null,
      code: '',
      description: '',
    });

    if (this.edit) this.editorForm.patchValue(this._facultyCode);
  }

  submit(code: FacultyCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveFacultyCode(code));
    else  this.store.dispatch(this.actions.updateFacultyCode(code));
    this.dialog.close();
  }

}

import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {ProgramCode} from '../../../../shared/model/common/program-code.interface';

@Component({
  selector: 'pams-program-code-editor',
  templateUrl: './program-code-editor.dialog.html',
})

export class ProgramCodeEditorDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _programCode: ProgramCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<ProgramCodeEditorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions) {
  }

  set programCode(value: ProgramCode) {
    this._programCode = value;
    this.edit = true;
  }

  ngOnInit(): void {

    this.editorForm = this.formBuilder.group(<ProgramCode>{
      id: null,
      code: '',
      description: '',
    });

    if (this.edit) this.editorForm.patchValue(this._programCode);
  }

  submit(code: ProgramCode, isValid: boolean) {
    if (!code.id) this.store.dispatch(this.actions.saveProgramCode(code));
    else  this.store.dispatch(this.actions.updateProgramCode(code));
    this.dialog.close();
  }

}

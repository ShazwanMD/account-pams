import {ProgramCode} from '../../../../shared/model/common/program-code.interface';

import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-program-code-creator',
  templateUrl: './program-code-creator.dialog.html',
})

export class ProgramCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<ProgramCodeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group(<ProgramCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',

    });
  }

  save(code: ProgramCode, isValid: boolean) {
    this.store.dispatch(this.actions.saveProgramCode(code));
    this.dialog.close();

  }

}

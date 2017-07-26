import { ProgramCode } from '../../../common/program-codes/program-code.interface';

import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
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
              private actions: SetupActions,
  ) {
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

import {StateCode} from '../../../../shared/model/common/state-code.interface';

import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-state-code-creator',
  templateUrl: './state-code-creator.dialog.html',
})

export class StateCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<StateCodeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group(<StateCode>{
      id: null,
      code: '',
      name: '',
      descriptionMs: '',
      descriptionEn: '',
      prefix: '',

    });
  }

  save(code: StateCode, isValid: boolean) {
    this.store.dispatch(this.actions.saveStateCode(code));
  }
}

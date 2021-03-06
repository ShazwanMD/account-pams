import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';
import {StudyMode} from '../../../../shared/model/common/study-mode.interface';


@Component({
  selector: 'pams-study-mode-creator',
  templateUrl: './study-mode-creator.dialog.html',
})

export class StudyModeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<StudyModeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group({
      id: undefined,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
    });
  }

  save(code: StudyMode, isValid: boolean) {
    this.store.dispatch(this.actions.saveStudyMode(code));
    this.dialog.close();
  }
}


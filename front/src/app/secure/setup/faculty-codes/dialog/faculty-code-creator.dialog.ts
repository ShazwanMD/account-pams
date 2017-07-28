import {FacultyCode} from '../../../../shared/model/common/faculty-code.interface';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {SetupModuleState} from '../../index';
import {SetupActions} from '../../setup.action';

@Component({
  selector: 'pams-faculty-code-creator',
  templateUrl: './faculty-code-creator.dialog.html',
})

export class FacultyCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<FacultyCodeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions,) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group(<FacultyCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
    });
  }

  save(code: FacultyCode, isValid: boolean) {
    this.store.dispatch(this.actions.saveFacultyCode(code));
    this.dialog.close();
  }

}

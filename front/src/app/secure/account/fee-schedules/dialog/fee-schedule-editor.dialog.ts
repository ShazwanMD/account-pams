import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {MdDialogRef} from '@angular/material';
import {AccountModuleState} from '../../index';
import {FeeScheduleActions} from '../fee-schedule.action';
import {CohortCode} from '../../../../shared/model/common/cohort-code.interface';
import {StudyMode} from '../../../../shared/model/common/study-mode.interface';
import {ResidencyCode} from '../../../../shared/model/common/residency-code.interface';
import {FeeSchedule} from '../../../../shared/model/account/fee-schedule.interface';

@Component({
  selector: 'pams-fee-schedule-editor',
  templateUrl: './fee-schedule-editor.dialog.html',
})

export class FeeScheduleEditorDialog implements OnInit {

  private editForm: FormGroup; feeSchedule: FeeSchedule;
  private edit: boolean = false;


  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private actions: FeeScheduleActions,
              private dialog: MdDialogRef<FeeScheduleEditorDialog>) {
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: [undefined],
      code: [''],
      description: [''],
      residencyCode: [<ResidencyCode>{}],
      cohortCode: [<CohortCode>{}],
      studyMode: [<StudyMode>{}],
      status: [true],
    });

    if (this.edit) {
      this.editForm.patchValue(this.feeSchedule);
    }
  
  }

  submit(feeSchedule: FeeSchedule, isValid: boolean) {
    feeSchedule.description = feeSchedule.description;  
    feeSchedule.code = 'Y' + feeSchedule.studyMode.code + '-' + feeSchedule.residencyCode.code + '-' + feeSchedule.cohortCode.code;
    if (this.edit) this.store.dispatch(this.actions.updateFeeSchedule(this.feeSchedule));
  //  else  this.store.dispatch(this.actions.saveFeeSchedule(this.feeSchedule));
    this.dialog.close();

  }
}



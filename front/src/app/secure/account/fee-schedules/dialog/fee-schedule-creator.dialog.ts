import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
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
  selector: 'pams-fee-schedule-creator',
  templateUrl: './fee-schedule-creator.dialog.html',
})

export class FeeScheduleCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private actions: FeeScheduleActions,
              private dialog: MdDialogRef<FeeScheduleCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<FeeSchedule>{
      id: undefined,
      code: '',
      description: '',
      residencyCode: <ResidencyCode>{},
      cohortCode: <CohortCode>{},
      studyMode: <StudyMode>{},
    });
  }

  save(feeSchedule: FeeSchedule, isValid: boolean): void {
    console.log('saving fee');
    feeSchedule.code = 'Y' + feeSchedule.studyMode.code + '-' + feeSchedule.residencyCode.code + '-' + feeSchedule.cohortCode.code;
    this.store.dispatch(this.actions.saveFeeSchedule(feeSchedule));
    this.dialog.close();
  }
}

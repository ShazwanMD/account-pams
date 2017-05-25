import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {Sponsor} from "../../../identity/sponsor.interface";
import {FeeSchedule} from "../fee-schedule.interface";
import {AccountModuleState} from "../../index";
import {FeeScheduleActions} from "../fee-schedule.action";
import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";
import {StudyMode} from "../../../common/study-modes/study-mode.interface";
import {ResidencyCode} from "../../../common/residency-codes/residency-code.interface";

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
      id: null,
      code: '',
      description: '',
      residencyCode: <ResidencyCode>{},
      cohortCode: <CohortCode>{},
      studyMode: <StudyMode>{},
    });
  }

  save(feeSchedule: FeeSchedule, isValid: boolean): void {
    console.log("saving fee");
    this.store.dispatch(this.actions.saveFeeSchedule(feeSchedule));
    this.dialog.close();
  }
}

import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {CohortCode} from "../cohort-code.interface";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {CommonActions} from "../../common.action";
import {CommonModuleState} from "../../index";


@Component({
  selector: 'pams-cohort-code-select',
  templateUrl: './cohort-code-select.component.html',
})
export class CohortCodeSelectComponent implements OnInit {

  private COHORT_CODES = "commonModuleState.cohortCodes".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  cohortCodes$: Observable<CohortCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.cohortCodes$ = this.store.select(...this.COHORT_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findCohortCodes());
  }

  selectChangeEvent(event: CohortCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


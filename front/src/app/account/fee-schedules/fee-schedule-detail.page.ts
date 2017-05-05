import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {FeeSchedule} from "./fee-schedule.interface";
import {FeeScheduleItem} from "./fee-schedule-item.interface";
import {FeeScheduleActions} from "./fee-schedule.action";
import {AccountModuleState} from "../index";

@Component({
  selector: 'pams-feeSchedule-detail',
  templateUrl: './feeSchedule-detail.page.html',
})

export class FeeScheduleDetailPage implements OnInit {

  private FEE_SCHEDULE = "feeScheduleModuleState.feeSchedule".split(".");
  private FEE_SCHEDULE_ITEMS = "feeScheduleModuleState.feeScheduleItems".split(".");
  private feeSchedule$: Observable<FeeSchedule>;
  private feeScheduleItems$: Observable<FeeScheduleItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: FeeScheduleActions,
              private store: Store<AccountModuleState>) {

    this.feeSchedule$ = this.store.select(...this.FEE_SCHEDULE);
    this.feeScheduleItems$ = this.store.select(...this.FEE_SCHEDULE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {code: string}) => {
      let code: string = params.code;
      this.store.dispatch(this.actions.findFeeSchedule(code));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/feeSchedules']);
  }
}


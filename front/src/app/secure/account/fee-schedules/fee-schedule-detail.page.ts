import {Component, OnInit, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {FeeScheduleActions} from './fee-schedule.action';
import {AccountModuleState} from '../index';
import {FeeScheduleItemEditorDialog} from './dialog/fee-schedule-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {FeeSchedule} from '../../../shared/model/account/fee-schedule.interface';
import {FeeScheduleItem} from '../../../shared/model/account/fee-schedule-item.interface';

@Component({
  selector: 'pams-fee-schedule-detail',
  templateUrl: './fee-schedule-detail.page.html',
})

export class FeeScheduleDetailPage implements OnInit {

  private FEE_SCHEDULE: string[] = 'accountModuleState.feeSchedule'.split('.');
  private FEE_SCHEDULE_ITEMS: string[] = 'accountModuleState.feeScheduleItems'.split('.');
  private feeSchedule$: Observable<FeeSchedule>;
  private feeScheduleItems$: Observable<FeeScheduleItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: FeeScheduleActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {

    this.feeSchedule$ = this.store.select(...this.FEE_SCHEDULE);
    this.feeScheduleItems$ = this.store.select(...this.FEE_SCHEDULE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { code: string }) => {
      let code: string = params.code;
      this.store.dispatch(this.actions.findFeeScheduleByCode(code));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/feeSchedules']);
  }
}


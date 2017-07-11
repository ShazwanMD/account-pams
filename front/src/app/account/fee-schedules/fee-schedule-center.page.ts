import { Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef, Input } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {AccountModuleState} from '../index';
import {FeeSchedule} from './fee-schedule.interface';
import {FeeScheduleActions} from './fee-schedule.action';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {FeeScheduleCreatorDialog} from './dialog/fee-schedule-creator.dialog';

@Component({
  selector: 'pams-fee-schedule-center',
  templateUrl: './fee-schedule-center.page.html',
})

export class FeeScheduleCenterPage implements OnInit {
@Input() feeSchedule: FeeSchedule;
  private FEE_SCHEDULES: string[] = 'accountModuleState.feeSchedules'.split('.');
  private feeSchedules$: Observable<FeeSchedule[]>;
  private creatorDialogRef: MdDialogRef<FeeScheduleCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: FeeScheduleActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.feeSchedules$ = this.store.select(...this.FEE_SCHEDULES);
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(FeeScheduleCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  filter(): void {
  }

  goBack(route: string): void {
    this.router.navigate(['/fee-schedules']);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findFeeSchedules());
  }

    download(): void {
    this.store.dispatch(this.actions.downloadFeeSchedule(this.feeSchedule));
  }

  upload(file: File): void {
    console.log('feeSchedule', file);
    this.store.dispatch(this.actions.uploadFeeSchedule(file));
  }
}


import { FeeScheduleEditorDialog } from './dialog/fee-schedule-editor.dialog';
import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {FeeScheduleActions} from './fee-schedule.action';
import {AccountModuleState} from '../index';
import { MdDialog, MdDialogRef, MdDialogConfig } from '@angular/material';
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
  private editorDialogRef: MdDialogRef<FeeScheduleEditorDialog>;

  @Input() feeSchedule: FeeSchedule;

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

  edit(feeSchedule:FeeSchedule): void {
    this.showDialog(feeSchedule);
  }

  showDialog(feeSchedule: FeeSchedule): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(FeeScheduleEditorDialog, config);
    this.editorDialogRef.componentInstance.feeSchedule = this.feeSchedule;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}


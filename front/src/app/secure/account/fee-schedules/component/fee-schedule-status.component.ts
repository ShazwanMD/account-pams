import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import { FeeSchedule } from '../../../../shared/model/account/fee-schedule.interface';

@Component({
  selector: 'pams-fee-schedule-status',
  templateUrl: './fee-schedule-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class FeeScheduleStatusComponent {

  @Input() feeSchedule: FeeSchedule;

}

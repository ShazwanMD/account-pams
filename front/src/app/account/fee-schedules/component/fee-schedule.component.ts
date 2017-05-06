import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {FeeSchedule} from "../fee-schedule.interface";

@Component({
  selector: 'pams-fee-schedule',
  templateUrl: './fee-schedule.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FeeScheduleComponent {

  @Input() feeSchedule: FeeSchedule;
}

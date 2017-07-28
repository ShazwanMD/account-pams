import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {FeeSchedule} from '../../../../shared/model/account/fee-schedule.interface';

@Component({
  selector: 'pams-fee-schedule',
  templateUrl: './fee-schedule.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FeeScheduleComponent {

  @Input() feeSchedule: FeeSchedule;
}

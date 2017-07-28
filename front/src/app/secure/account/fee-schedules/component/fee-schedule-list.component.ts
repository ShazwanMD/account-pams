import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {FeeSchedule} from '../../../../shared/model/account/fee-schedule.interface';

@Component({
  selector: 'pams-fee-schedule-list',
  templateUrl: './fee-schedule-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FeeScheduleListComponent {

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''},
  ];

  @Input() feeSchedules: FeeSchedule[];
  @Output() view = new EventEmitter<FeeSchedule>();

}

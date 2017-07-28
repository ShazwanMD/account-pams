import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';

@Component({
  selector: 'pams-account-activity-list',
  templateUrl: './account-activity-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityListComponent {

  @Input() activity: AccountActivity[];

  private columns: any[] = [
    {name: 'sourceNo', label: 'Source'},
    {name: 'chargeCode.description', label: 'ChargeCode'},
    {name: 'activity.postedDate', label: 'Session'},
    {name: 'totalAmount', label: 'Amount'},
    {name: 'action', label: ''}
  ];


}

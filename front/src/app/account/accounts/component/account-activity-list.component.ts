import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import { AccountActivity } from "../../../shared/model/account/account-activity.interface";

@Component({
  selector: 'pams-account-activity-list',
  templateUrl: './account-activity-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityListComponent {

  private columns: any[] = [
    {name: 'sourceNo', label: 'Source'},
    {name: 'chargeCode.description', label: 'ChargeCode'},
    {name: 'session.code', label: 'Session'},
    {name: 'totalAmount', label: 'Amount'},
    {name: 'action', label: ''}
  ];

  @Input() activity: AccountActivity[];

}

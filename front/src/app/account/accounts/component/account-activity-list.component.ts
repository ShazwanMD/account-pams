import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {AccountTransaction} from '../../../shared/model/account/account-transaction.interface';

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
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''}
  ];

  @Input() transactions: AccountTransaction[];

}

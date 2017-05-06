import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {AccountTransaction} from "../account-transaction.interface";

@Component({
  selector: 'pams-account-activity-list',
  templateUrl: './account-activity-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityListComponent {
  @Input() transactions: AccountTransaction[];
}

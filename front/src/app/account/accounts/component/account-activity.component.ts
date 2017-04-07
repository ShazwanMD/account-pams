import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {AccountTransaction} from "../account-transaction.interface";

@Component({
  selector: 'pams-account-activity',
  templateUrl: './account-activity.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActivityComponent {
  @Input() transactions: AccountTransaction[];
}

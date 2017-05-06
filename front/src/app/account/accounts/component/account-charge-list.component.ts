import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {AccountTransaction} from "../account-transaction.interface";

@Component({
  selector: 'pams-account-charge-list',
  templateUrl: './account-charge-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountChargeListComponent {
  @Input() transactions: AccountTransaction[];
}

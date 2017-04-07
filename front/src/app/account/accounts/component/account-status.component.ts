import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Account} from "../account.interface";

@Component({
  selector: 'pams-account-status',
  templateUrl: './account-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class AccountStatusComponent {
  @Input() account: Account;
}

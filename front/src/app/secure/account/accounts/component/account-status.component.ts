import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-account-status',
  templateUrl: './account-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class AccountStatusComponent {
  @Input() account: Account;
}

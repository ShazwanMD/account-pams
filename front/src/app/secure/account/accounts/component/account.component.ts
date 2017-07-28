import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-account',
  templateUrl: './account.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountComponent {

  @Input() account: Account;
}

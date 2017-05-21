import {Component, Input, ChangeDetectionStrategy} from '@angular/core';
import {Account} from "../account.interface";

@Component({
  selector: 'pams-account',
  templateUrl: './account.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountComponent {

  @Input() account: Account;
}

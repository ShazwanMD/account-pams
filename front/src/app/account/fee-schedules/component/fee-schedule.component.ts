import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Account} from "../account.interface";

@Component({
  selector: 'pams-account',
  templateUrl: './account.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountComponent {

  @Input() account: Account;
  // @Output() addComment = new EventEmitter<AccountComment>();
  // @Output() addAttachment = new EventEmitter<AccountAttachment>();
}

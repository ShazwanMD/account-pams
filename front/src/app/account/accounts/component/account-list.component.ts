import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Account} from "../account.interface";

@Component({
  selector: 'pams-account-list',
  templateUrl: './account-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountListComponent {

  @Input() accounts: Account[];
  @Output() view = new EventEmitter<Account>();

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'name', label: 'Name'},
    {name: 'email', label: 'Email'},
    {name: 'action', label: ''}
  ];
}

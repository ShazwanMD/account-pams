import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {AccountTransaction} from "../account-transaction.interface";
import {AccountWaiver} from "../account-waiver.interface";

@Component({
  selector: 'pams-account-waiver-list',
  templateUrl: './account-waiver-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountWaiverListComponent {
  @Input() waivers: AccountWaiver[];
}

import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {Store} from "@ngrx/store";
import {ActivatedRoute, Router} from "@angular/router";
import { MdDialogConfig, MdDialogRef, MdDialog } from "@angular/material";
import {Account} from "../account.interface";
import {AccountActions} from "../account.action";
import { AccountModuleState } from "../../index";
import { Actor } from "../../../identity/actor.interface";
import { ActorType } from "../../../identity/actor-type.enum";


@Component({
  selector: 'pams-account-sponsor-list',
  templateUrl: './account-sponsor-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountSponsorListComponent {

  @Input() actor: Actor;

  @Input() accountSponsorList: Account;
  
private columns: any[] = [
{name: 'code', label: 'Code'},
{name: 'name', label: 'Name'},
{name: 'email', label: 'Email'},
{name: 'action', label: ''}
];

  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: AccountActions,
          private store: Store<AccountModuleState>,
          private vcf: ViewContainerRef,
          private dialog: MdDialog) {

}
  
  viewAccount(account: Account) {
      console.log("account: " + account.id);
      this.router.navigate(['/accounts-detail', account.id]);
    }
}
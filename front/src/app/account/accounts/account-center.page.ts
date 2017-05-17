import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountModuleState} from "../index";
import {AccountCreatorDialog} from "./dialog/account-creator.dialog";

@Component({
  selector: 'pams-account-center',
  templateUrl: './account-center.page.html',
})

export class AccountCenterPage implements OnInit {

  private ACCOUNTS = "accountModuleState.accounts".split(".");
  private accounts$: Observable<Account[]>;
  private creatorDialogRef: MdDialogRef<AccountCreatorDialog>;

  text: string;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.accounts$ = this.store.select(...this.ACCOUNTS);
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }

  viewAccount(account: Account) {
    console.log("account: " + account.id);
    this.router.navigate(['/accounts-detail', account.id]);
  }

  filterAccounts(filter: string): void {
    console.log("filtering: " + filter);
    if (filter) this.store.dispatch(this.actions.findAccountsByFilter(filter));
    else this.store.dispatch(this.actions.findAccounts())
  }

  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(AccountCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findAccounts());
  }
}


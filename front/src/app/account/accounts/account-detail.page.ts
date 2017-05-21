import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";


import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountTransaction} from "./account-transaction.interface";
import {AccountModuleState} from "../index";
import {AccountCharge} from "./account-charge.interface";
import {AccountWaiver} from "./account-waiver.interface";
import {AdmissionChargeDialog} from "./dialog/admission-charge.dialog";

@Component({
  selector: 'pams-account-detail',
  templateUrl: './account-detail.page.html',
})

export class AccountDetailPage implements OnInit {

  private ACCOUNT: string[] = "accountModuleState.account".split(".");
  private ACCOUNT_TRANSACTIONS: string[] = "accountModuleState.accountTransactions".split(".");
  private ACCOUNT_CHARGES: string[] = "accountModuleState.accountCharges".split(".");
  private ACCOUNT_WAIVERS: string[] = "accountModuleState.accountWaivers".split(".");
  private account$: Observable<Account>;
  private accountTransactions$: Observable<AccountTransaction[]>;
  private accountCharges$: Observable<AccountCharge[]>;
  private accountWaivers$: Observable<AccountWaiver[]>;


  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {

    this.account$ = this.store.select(...this.ACCOUNT);
    this.accountTransactions$ = this.store.select(...this.ACCOUNT_TRANSACTIONS);
    this.accountCharges$ = this.store.select(...this.ACCOUNT_CHARGES);
    this.accountWaivers$ = this.store.select(...this.ACCOUNT_WAIVERS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { code: string }) => {
      let code: string = params.code;
      this.store.dispatch(this.actions.findAccountByCode(code));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }


}


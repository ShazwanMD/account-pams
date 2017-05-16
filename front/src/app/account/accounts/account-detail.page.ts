import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";


import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
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

  private ACCOUNT = "accountModuleState.account".split(".");
  private ACCOUNT_TRANSACTIONS = "accountModuleState.accountTransactions".split(".");
  private ACCOUNT_CHARGES = "accountModuleState.accountCharges".split(".");
  private ACCOUNT_WAIVERS = "accountModuleState.accountWaivers".split(".");
  private account$: Observable<Account>;
  private accountTransactions$: Observable<AccountTransaction[]>;
  private accountCharges$: Observable<AccountCharge[]>;
  private accountWaivers$: Observable<AccountWaiver[]>;
  private creatorDialogRef: MdDialogRef<AdmissionChargeDialog>;


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
    this.route.params.subscribe((params: {code: string}) => {
      let code: string = params.code;
      this.store.dispatch(this.actions.findAccount(code));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }

  showAdmissionChargeDialog():void{
    console.log("show dialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(AdmissionChargeDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  
}


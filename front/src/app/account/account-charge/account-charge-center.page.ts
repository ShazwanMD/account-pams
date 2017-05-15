import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {AccountModuleState} from "../index";
import {AccountCharge} from "./account-charge.interface";
import {AccountChargeActions} from "./account-charge.action";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
//import {FeeScheduleCreatorDialog} from "./dialog/fee-schedule-creator.dialog";

@Component({
  selector: 'pams-account-charge-center',
  templateUrl: './account-charge-center.page.html',
})

export class AccountChargeCenterPage implements OnInit {

  private ACCOUNT_CHARGES = "accountModuleState.accountCharges".split(".");
  private accountCharges$: Observable<AccountCharge[]>;
  //private creatorDialogRef: MdDialogRef<StudentChargeCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountChargeActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.accountCharges$ = this.store.select(...this.ACCOUNT_CHARGES);
  }

  // showDialog(): void {
  //   console.log("showDialog");
  //   let config = new MdDialogConfig();
  //   config.viewContainerRef = this.vcf;
  //   config.role = 'dialog';
  //   config.width = '50%';
  //   config.height = '65%';
  //   config.position = {top: '0px'};
  //   this.creatorDialogRef = this.dialog.open(FeeScheduleCreatorDialog, config);
  //   this.creatorDialogRef.afterClosed().subscribe(res => {
  //     console.log("close dialog");
  //     // load something here
  //   });
  // }

  filter(): void {
  }

  goBack(route: string): void {
    this.router.navigate(['/account-charge']);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findAccountCharges());
  }
}


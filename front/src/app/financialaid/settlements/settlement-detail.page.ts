import {Component, OnInit} from "@angular/core";
import {Settlement} from "./settlement.interface";
import {ActivatedRoute, Router} from "@angular/router";
import {SettlementItem} from "./settlement-item.interface";
import {Observable} from "rxjs/Observable";
import {AccountActions} from "../../account/accounts/account.action";
import {SettlementActions} from "./settlement.action";
import {Store} from "@ngrx/store";
import {FinancialaidModuleState} from "../index";
@Component({
  selector: 'pams-settlement-detail',
  templateUrl: './settlement-detail.page.html',
})

export class SettlementDetailPage implements OnInit {

  private SETTLEMENT = "financialaidModuleState.settlement".split(".");
  private SETTLEMENT_ITEMS = "financialaidModuleState.settlementItems".split(".");
  private settlement$: Observable<Settlement>;
  private settlementItems$: Observable<SettlementItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>) {

    this.settlement$ = this.store.select(...this.SETTLEMENT);
    this.settlementItems$ = this.store.select(...this.SETTLEMENT_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {referenceNo: string}) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findSettlementByReferenceNo(referenceNo));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }
}

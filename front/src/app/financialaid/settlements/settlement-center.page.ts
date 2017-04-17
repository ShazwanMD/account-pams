import {Component, OnInit, ChangeDetectionStrategy, state} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {SettlementActions} from "./settlement.action";
import {Settlement} from "./settlement.interface";
import {SettlementListState} from "./settlement-list.reducer";
import {FinancialaidModuleState} from "../index";

@Component({
  selector: 'pams-settlement-center',
  templateUrl: './settlement-center.page.html',
})

export class SettlementCenterPage implements OnInit {

  private settlements$: Observable<Settlement[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>) {
    this.settlements$ = this.store.select(state => state.settlements);
  }

  goBack(route: string): void {
    this.router.navigate(['/settlements']);
  }

  view(settlement: Settlement) {
    console.log("settlement: " + settlement.referenceNo);
    this.router.navigate(['/view', settlement.referenceNo]);
  }

  ngOnInit(): void {
    console.log("find settlements");
    this.store.dispatch(this.actions.findSettlements());
  }
}


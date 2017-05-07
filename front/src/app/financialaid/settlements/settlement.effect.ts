import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {SettlementActions} from "./settlement.action";
import {from} from "rxjs/observable/from";
import {FinancialaidService} from "../../../services/financialaid.service";
import {Router} from "@angular/router";


@Injectable()
export class SettlementEffects {
  constructor(private router: Router,
              private actions$: Actions,
              private settlementActions: SettlementActions,
              private financialaidService: FinancialaidService) {
  }

  @Effect() findSettlementById = this.actions$
    .ofType(SettlementActions.FIND_SETTLEMENT_BY_ID)
    .map(action => action.payload)
    .switchMap(id => this.financialaidService.findSettlementById(id))
    .map(settlement => this.settlementActions.findSettlementByIdSuccess(settlement));

  @Effect() findSettlementByReferenceNo$ = this.actions$
    .ofType(SettlementActions.FIND_SETTLEMENT_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.financialaidService.findSettlementByReferenceNo(referenceNo))
    .map(settlement => this.settlementActions.findSettlementByReferenceNoSuccess(settlement))
    .mergeMap(action => from([action, this.settlementActions.findSettlementItems(action.payload)]));

  @Effect() findSettlements$ = this.actions$
    .ofType(SettlementActions.FIND_SETTLEMENTS)
    .map(action => action.payload)
    .switchMap(settlement => this.financialaidService.findSettlements())
    .map(settlements => this.settlementActions.findSettlementsSuccess(settlements));

  @Effect() findSettlementItems$ = this.actions$
    .ofType(SettlementActions.FIND_SETTLEMENT_ITEMS)
    .map(action => action.payload)
    .switchMap(settlement => this.financialaidService.findSettlementItems(settlement))
    .map(items => this.settlementActions.findSettlementItemsSuccess(items));

  @Effect() initSettlement$ = this.actions$
    .ofType(SettlementActions.INIT_SETTLEMENT)
    .map(action => action.payload)
    .switchMap(settlement => this.financialaidService.initSettlement(settlement))
    .map(referenceNo => this.settlementActions.initSettlementSuccess(referenceNo))
    .mergeMap(action => from([action, this.settlementActions.findSettlements()]));
  /*
    .mergeMap(action => from([action,
      this.settlementActions.findSettlementByReferenceNo(action.payload),
      this.router.navigate(['financialaid/settlements/', action.payload])]));
*/
  @Effect() processSettlement$ = this.actions$
    .ofType(SettlementActions.PROCESS_SETTLEMENT)
    .map(action => action.payload);
  // todo
  // .switchMap(settlement => this.financialaidService.startSettlementTask(settlement))
  // .map(task => this.settlementActions.startSettlementTaskSuccess(task));

  @Effect() updateSettlement$ = this.actions$
    .ofType(SettlementActions.UPDATE_SETTLEMENT)
    .map(action => action.payload)
    .switchMap(settlement => this.financialaidService.updateSettlement(settlement))
    .map(settlement => this.settlementActions.updateSettlementSuccess(settlement));
}

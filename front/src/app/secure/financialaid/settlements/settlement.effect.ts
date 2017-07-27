import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {SettlementActions} from "./settlement.action";
import {from} from "rxjs/observable/from";
import {FinancialaidService} from "../../../../services/financialaid.service";
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import 'rxjs/add/operator/withLatestFrom';
import {FinancialaidModuleState} from "../index";
import {Observable} from "rxjs/Observable";
import {Settlement} from '../../../shared/model/financialaid/settlement.interface';

@Injectable()
export class SettlementEffects {

  private SETTLEMENT = "financialaidModuleState.settlement".split(".");

  constructor(private router: Router,
              private actions$: Actions,
              private settlementActions: SettlementActions,
              private financialaidService: FinancialaidService,
              private store$: Store<FinancialaidModuleState>) {
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

  @Effect() initSettlementBySponsor$ = this.actions$
    .ofType(SettlementActions.INIT_SETTLEMENT_BY_SPONSOR)
    .map(action => action.payload)
    .switchMap(payload => this.financialaidService.initSettlementBySponsor(payload.settlement, payload.sponsor))
    .map(referenceNo => this.settlementActions.initSettlementBySponsorSuccess(referenceNo))
    .mergeMap(action => from([action, this.settlementActions.findSettlements()]));

  @Effect() initSettlementByFacultyCode$ = this.actions$
    .ofType(SettlementActions.INIT_SETTLEMENT_BY_FACULTY_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.financialaidService.initSettlementByFacultyCode(payload.settlement, payload.facultyCode))
    .map(referenceNo => this.settlementActions.initSettlementByFacultyCodeSuccess(referenceNo))
    .mergeMap(action => from([action, this.settlementActions.findSettlements()]));

  @Effect() initSettlementByCohortCode$ = this.actions$
    .ofType(SettlementActions.INIT_SETTLEMENT_BY_COHORT_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.financialaidService.initSettlementByCohortCode(payload.settlement, payload.cohortCode))
    .map(referenceNo => this.settlementActions.initSettlementByFacultyCodeSuccess(referenceNo))
    .mergeMap(action => from([action, this.settlementActions.findSettlements()]));

  @Effect() initSettlementByAcademicSession$ = this.actions$
    .ofType(SettlementActions.INIT_SETTLEMENT_BY_ACADEMIC_SESSION)
    .map(action => action.payload)
    .switchMap(payload => this.financialaidService.initSettlementByAcademicSession(payload))
    .map(referenceNo => this.settlementActions.initSettlementByAcademicSessionSuccess(referenceNo))
    .mergeMap(action => from([action, this.settlementActions.findSettlements()]));

  @Effect() updateSettlement$ = this.actions$
    .ofType(SettlementActions.UPDATE_SETTLEMENT)
    .map(action => action.payload)
    .switchMap(settlement => this.financialaidService.updateSettlement(settlement))
    .map(settlement => this.settlementActions.updateSettlementSuccess(settlement));

  @Effect() executeSettlement$ = this.actions$
    .ofType(SettlementActions.EXEC_SETTLEMENT)
    .map(action => action.payload)
    .switchMap(settlement => this.financialaidService.executeSettlement(settlement))
    .map(message => this.settlementActions.executeSettlementSuccess(message))
    .withLatestFrom(this.store$.select(...this.SETTLEMENT))
    .map(state => state[1])
    .map((settlement: Settlement) => this.settlementActions.findSettlementByReferenceNo(settlement.referenceNo));

  @Effect() addSettlementItem$ =
      this.actions$
        .ofType(SettlementActions.ADD_SETTLEMENT_ITEM)
        .map(action => action.payload)
        .switchMap(payload => this.financialaidService.addSettlementItem(payload.settlement, payload.settlementItem))
        .map(message => this.settlementActions.addSettlementItemSuccess(message))
        .withLatestFrom(this.store$.select(...this.SETTLEMENT))
        .map(state => state[1])
        .map(settlement => this.settlementActions.findSettlementItems(settlement));

    @Effect() updateSettlementItem$ = this.actions$
      .ofType(SettlementActions.UPDATE_SETTLEMENT_ITEM)
      .map(action => action.payload)
      .switchMap(payload => this.financialaidService.updateSettlementItem(payload.settlement, payload.settlementItem))
      .map(message => this.settlementActions.updateSettlementItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.SETTLEMENT))
      .map(state => state[1])
      .map(settlement => this.settlementActions.findSettlementItems(settlement));

    @Effect() deleteSettlementItem$ = this.actions$
      .ofType(SettlementActions.DELETE_SETTLEMENT_ITEM)
      .map(action => action.payload)
      .switchMap(payload => this.financialaidService.deleteSettlementItem(payload.settlement, payload.settlementItem))
      .map(message => this.settlementActions.deleteSettlementItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.SETTLEMENT))
      .map(state => state[1])
      .map(settlement => this.settlementActions.findSettlementItems(settlement));
  }

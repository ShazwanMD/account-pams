import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {FinancialaidPage} from "./financialaid.page";
import {FinancialaidService} from "../../services/financialaid.service";
import {WaiverApplicationSubModule} from "./waiver-applications/index";
import {SettlementSubModule} from "./settlements/index";
import {SettlementState, settlementReducer} from "./settlements/settlement.reducer";
import {SettlementListState, settlementListReducer} from "./settlements/settlement-list.reducer";
import {WaiverApplicationState, waiverApplicationReducer} from "./waiver-applications/waiver-application.reducer";
import {
  assignedWaiverApplicationTaskListReducer,
  pooledWaiverApplicationTaskListReducer,
  WaiverApplicationTaskListState,
} from "./waiver-applications/waiver-application-task-list.reducer";
import {settlementItemListReducer, SettlementItemListState} from "./settlements/settlement-item-list.reducer";

import {
  WaiverApplicationTaskState,
  waiverApplicationTaskReducer
} from "./waiver-applications/waiver-application-task.reducer";
import {WaiverApplicationTask} from "./waiver-applications/waiver-application-task.interface";
import {AccountSubModule} from "../account/accounts/index";

export interface FinancialaidModuleState {
  assignedWaiverApplicationTasks: WaiverApplicationTaskListState;
  pooledWaiverApplicationTasks: WaiverApplicationTaskListState;
  waiverApplicationTask: WaiverApplicationTaskState;
  waiverApplication: WaiverApplicationState;
  settlements: SettlementListState;
  settlement: SettlementState;
  settlementItems: SettlementItemListState;
}
;

export const INITIAL_FINANCIALAID_STATE: FinancialaidModuleState =
  <FinancialaidModuleState>{
    assignedWaiverApplicationTasks: [],
    pooledWaiverApplicationTasks: [],
    waiverApplicationTask: {},
    waiverApplication: {},
    settlements: [],
    settlement: {},
    settlementItems: [],
  };

export const financialaidModuleReducers = {
  assignedWaiverApplicationTasks: assignedWaiverApplicationTaskListReducer,
  pooledWaiverApplicationTasks: pooledWaiverApplicationTaskListReducer,
  waiverApplicationTask: waiverApplicationTaskReducer,
  waiverApplication: waiverApplicationReducer,
  settlements: settlementListReducer,
  settlement: settlementReducer,
  settlementItems: settlementItemListReducer
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    SettlementSubModule.forRoot(),
    WaiverApplicationSubModule.forRoot(),
  ],
  declarations: [
    // page
    FinancialaidPage,
  ],
  exports: [],
})
export class FinancialaidModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: FinancialaidModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
      ],
    };
  }
}

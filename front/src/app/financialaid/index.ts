import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {FinancialaidPage} from "./financialaid.page";
import {FinancialaidService} from "../../services/financialaid.service";
import {ActionReducer, combineReducers} from "@ngrx/store";
import {WaiverApplicationSubModule} from "./waiver-applications/index";
import {SettlementSubModule} from "./settlements/index";
import {SettlementState, settlementReducer} from "./settlements/settlement.reducer";
import {SettlementListState, settlementListReducer} from "./settlements/settlement-list.reducer";
import {WaiverApplicationState, waiverApplicationReducer} from "./waiver-applications/waiver-application.reducer";
import {
  WaiverApplicationTaskListState,
  waiverApplicationTaskListReducer
} from "./waiver-applications/waiver-application-task-list.reducer";

import {
  WaiverApplicationTaskState,
  waiverApplicationTaskReducer
} from "./waiver-applications/waiver-application-task.reducer";

export interface FinancialaidModuleState {
  waiverApplicationTasks: WaiverApplicationTaskListState;
  waiverApplicationTask: WaiverApplicationTaskState;
  waiverApplication: WaiverApplicationState;
  settlements: SettlementListState;
  settlement: SettlementState;
};

export const INITIAL_FINANCIALAID_STATE: FinancialaidModuleState = <FinancialaidModuleState>{};
export const financialaidModuleReducers = {
   waiverApplicationTasks:waiverApplicationTaskListReducer,
   waiverApplicationTask:waiverApplicationTaskReducer,
   waiverApplications:waiverApplicationReducer,
   settlements:settlementListReducer,
   settlement:settlementReducer
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

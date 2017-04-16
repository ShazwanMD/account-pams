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

export interface FinancialaidModuleState {
  // waiverApplicationTasks: WaiverApplicationTaskListState;
  // waiverApplicationTask: WaiverApplicationTaskState;
  // waiverApplication: WaiverApplicationState;
  // settlements: SettlementListState;
  // settlement: SettlementState;
};

// export const INITIAL_FINANCIALAID_STATE: FinancialaidModuleState = <FinancialaidModuleState>{};
// const financialAidModuleReducers = {
//    waiverApplicationTasks:waiverApplicationTaskListReducer,
//    waiverApplicationTask:waiverApplicationTaskReducer,
//    waiverApplications:waiverApplicationReducer
//    settlements:settlementListReducer
//    settlement:settlementReducer
// };

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

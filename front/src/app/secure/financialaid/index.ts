import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';

import {FinancialaidPage} from './financialaid.page';
import {FinancialaidService} from '../../../services/financialaid.service';
import {WaiverApplicationSubModule} from './waiver-applications/index';
import {SettlementSubModule} from './settlements/index';
import {SettlementState, settlementReducer} from './settlements/settlement.reducer';
import {SettlementListState, settlementListReducer} from './settlements/settlement-list.reducer';
import {WaiverApplicationState, waiverApplicationReducer} from './waiver-applications/waiver-application.reducer';
import {
  assignedWaiverApplicationTaskListReducer,
  pooledWaiverApplicationTaskListReducer,
  WaiverApplicationTaskListState,
} from './waiver-applications/waiver-application-task-list.reducer';
import {settlementItemListReducer, SettlementItemListState} from './settlements/settlement-item-list.reducer';

import {
  WaiverApplicationTaskState,
  waiverApplicationTaskReducer,
} from './waiver-applications/waiver-application-task.reducer';
import {PipeModule} from '../../app.pipe.module';
import {WaiverApplicationListState, archivedWaiverApplicationListReducer, waiverApplicationListReducer} from './waiver-applications/waiver-application-list.reducer';
import {SettlementItem} from '../../shared/model/financialaid/settlement-item.interface';
import {Settlement} from '../../shared/model/financialaid/settlement.interface';
import {WaiverApplication} from '../../shared/model/financialaid/waiver-application.interface';
import {WaiverApplicationTask} from '../../shared/model/financialaid/waiver-application-task.interface';

export interface FinancialaidModuleState {
  assignedWaiverApplicationTasks: WaiverApplicationTaskListState;
  pooledWaiverApplicationTasks: WaiverApplicationTaskListState;
  archivedWaiverApplications: WaiverApplicationListState;
  waiverApplicationTask: WaiverApplicationTaskState;
  waiverApplication: WaiverApplicationState;
  waiverApplications: WaiverApplicationListState;
  settlements: SettlementListState;
  settlement: SettlementState;
  settlementItems: SettlementItemListState;
}
;

export const INITIAL_FINANCIALAID_STATE: FinancialaidModuleState =
  <FinancialaidModuleState>{
    assignedWaiverApplicationTasks: [],
    pooledWaiverApplicationTasks: [],
    archivedWaiverApplications: [],
    waiverApplicationTask: <WaiverApplicationTask>{},
    waiverApplication: {},
    waiverApplications: <WaiverApplication[]>[],
    settlements: <Settlement[]>[],
    settlement: <Settlement>{},
    settlementItems: <SettlementItem[]>[],
  };

export const financialaidModuleReducers = {
  assignedWaiverApplicationTasks: assignedWaiverApplicationTaskListReducer,
  pooledWaiverApplicationTasks: pooledWaiverApplicationTaskListReducer,
  archivedWaiverApplications: archivedWaiverApplicationListReducer,
  waiverApplicationTask: waiverApplicationTaskReducer,
  waiverApplication: waiverApplicationReducer,
  waiverApplications: waiverApplicationListReducer,
  settlements: settlementListReducer,
  settlement: settlementReducer,
  settlementItems: settlementItemListReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    SettlementSubModule.forRoot(),
    WaiverApplicationSubModule.forRoot(),
    PipeModule.forRoot(),
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

import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {FinancialaidService} from "../../../services/financialaid.service";
import {SettlementEffects} from "./settlement.effect";
import {EffectsModule} from "@ngrx/effects";
import {SettlementCenterPage} from "./settlement-center.page";
import {SettlementActions} from "./settlement.action";
import {SettlementListComponent} from "./component/settlement-list.component";
//import {SettlementCreatorDialog} from "./dialog/settlement-creator.dialog";
import {IdentityModule} from "../../identity/index";
import {CommonModule} from "../../common/index";
import {AcademicSessionSubModule} from "../../account/academic-sessions/index";
import {SettlementCreatorByCohortDialog} from "./dialog/settlement-creator-by-cohort.dialog";
import {SettlementCreatorByFacultyDialog} from "./dialog/settlement-creator-by-faculty.dialog";
import {SettlementCreatorBySponsorDialog} from "./dialog/settlement-creator-by-sponsor.dialog";

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    EffectsModule.run(SettlementEffects),
  ],
  declarations: [
    // page
    SettlementCenterPage,

    // component
    SettlementListComponent,
    //SettlementCreatorDialog,
    SettlementCreatorByCohortDialog,
    SettlementCreatorByFacultyDialog,
    SettlementCreatorBySponsorDialog,
  ],
  exports: [],
  entryComponents: [
    //SettlementCreatorDialog,
    SettlementCreatorByCohortDialog,
    SettlementCreatorByFacultyDialog,
    SettlementCreatorBySponsorDialog,
  ],

})
export class SettlementSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SettlementSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
        SettlementActions,
      ],
    };
  }
}

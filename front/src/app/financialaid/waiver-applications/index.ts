import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {EffectsModule} from "@ngrx/effects";
import {CovalentCoreModule} from '@covalent/core';
import {WaiverApplicationCenterPage} from "./waiver-application-center.page";
import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {FinancialaidService} from "../../../services/financialaid.service";
import {WaiverApplicationEffects} from "./waiver-application.effect";
import {WaiverApplicationActions} from "./waiver-application.action";
import {IdentityModule} from "../../identity/index";
import {CommonModule} from "../../common/index";
import {AcademicSessionSubModule} from "../../account/academic-sessions/index";
import {WaiverApplicationCreatorDialog} from "./dialog/waiver-application-creator.dialog";
import {AccountSubModule} from "../../account/accounts/index";
import {AssignedWaiverApplicationTaskListComponent} from "./component/assigned-waiver-application-task-list.component";
import {PooledWaiverApplicationTaskListComponent} from "./component/pooled-waiver-application-task-list.component";

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    EffectsModule.run(WaiverApplicationEffects),
  ],
  declarations: [
    // page
    WaiverApplicationCenterPage,
    //component
    WaiverApplicationCreatorDialog,
    AssignedWaiverApplicationTaskListComponent,
    PooledWaiverApplicationTaskListComponent
  ],
  exports: [],
  entryComponents: [
    WaiverApplicationCreatorDialog,
  ],

})
export class WaiverApplicationSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: WaiverApplicationSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
        WaiverApplicationActions,
      ],
    };
  }
}

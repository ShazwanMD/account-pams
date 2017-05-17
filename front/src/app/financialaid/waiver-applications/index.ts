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
import {WaiverApplicationDraftTaskPanel} from "./panel/waiver-application-draft-task.panel";
import {WaiverApplicationTaskWorkflowPanel} from "./panel/waiver-application-task-workflow.panel";
import {WaiverApplicationTaskViewPage} from "./waiver-application-task-view.page";
import {WaiverApplicationCheckTaskPanel} from "./panel/waiver-application-check-task.panel";
import {WaiverApplicationVerifyTaskPanel} from "./panel/waiver-application-verify-task.panel";
import {WaiverApplicationRegisterTaskPanel} from "./panel/waiver-application-register-task.panel";

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
    WaiverApplicationTaskViewPage,

    //component
    WaiverApplicationCreatorDialog,
    AssignedWaiverApplicationTaskListComponent,
    PooledWaiverApplicationTaskListComponent,
    WaiverApplicationTaskWorkflowPanel,
    WaiverApplicationDraftTaskPanel,
    WaiverApplicationRegisterTaskPanel,
    WaiverApplicationVerifyTaskPanel,
    WaiverApplicationCheckTaskPanel,
  ],
  exports: [],
  entryComponents: [
    WaiverApplicationCreatorDialog,
    WaiverApplicationDraftTaskPanel,
    WaiverApplicationRegisterTaskPanel,
    WaiverApplicationVerifyTaskPanel,
    WaiverApplicationCheckTaskPanel,
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

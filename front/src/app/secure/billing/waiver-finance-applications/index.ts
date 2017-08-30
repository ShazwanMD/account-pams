import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {EffectsModule} from '@ngrx/effects';
import {CovalentCoreModule} from '@covalent/core';
import { CommonService, IdentityService, BillingService } from '../../../../services';
import {FinancialaidService} from '../../../../services/financialaid.service';
import {IdentityModule} from '../../identity/index';
import {CommonModule} from '../../../common/index';
import {AcademicSessionSubModule} from '../../account/academic-sessions/index';
import {AccountSubModule} from '../../account/accounts/index';
import {PipeModule} from '../../../app.pipe.module';
import { WaiverFinanceApplicationVerifyTaskPanel } from "./panel/waiver-finance-application-verify-task.panel";
import { WaiverFinanceApplicationRegisterTaskPanel } from "./panel/waiver-finance-application-register-task.panel";
import { WaiverFinanceApplicationDraftTaskPanel } from "./panel/waiver-finance-application-draft-task.panel";
import { AssignedWaiverFinanceApplicationTaskListComponent } from "./component/assigned-waiver-finance-application-task-list.component";
import { PooledWaiverFinanceApplicationTaskListComponent } from "./component/pooled-waiver-finance-application-task-list.component";
import { WaiverFinanceApplicationTaskWorkflowPanel } from "./panel/waiver-finance-application-task-workflow.panel";
import { WaiverFinanceApplicationStatusComponent } from "./component/waiver-finance-application-status.component";
import { ArchivedWaiverFinanceApplicationListComponent } from "./component/archived-waiver-finance-application-list.component";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";
import {WaiverFinanceApplicationCreatorDialog} from './dialog/waiver-finance-application-creator.dialog';
import {WaiverFinanceApplicationEffects} from './waiver-finance-application.effect';
import {WaiverFinanceApplicationTaskViewPage} from './waiver-finance-application-task-view.page';
import {WaiverFinanceApplicationCenterPage} from './waiver-finance-application-center.page';
import {WaiverFinanceApplicationDetailPage} from './waiver-finance-application-detail.page';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    PipeModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    EffectsModule.run(WaiverFinanceApplicationEffects),
  ],
  declarations: [
    // page
    WaiverFinanceApplicationCenterPage,
    WaiverFinanceApplicationTaskViewPage,

    //component
    AssignedWaiverFinanceApplicationTaskListComponent,
    PooledWaiverFinanceApplicationTaskListComponent,
    WaiverFinanceApplicationTaskWorkflowPanel,
    WaiverFinanceApplicationDraftTaskPanel,
    WaiverFinanceApplicationRegisterTaskPanel,
    WaiverFinanceApplicationVerifyTaskPanel,
    WaiverFinanceApplicationStatusComponent,
    WaiverFinanceApplicationDetailPage,
    ArchivedWaiverFinanceApplicationListComponent,

    // dialog
    WaiverFinanceApplicationCreatorDialog,
    //WaiverFinanceApplicationEditorDialog,
  ],
  exports: [],
  entryComponents: [
    WaiverFinanceApplicationCreatorDialog,
   // WaiverFinanceApplicationEditorDialog,
    WaiverFinanceApplicationDraftTaskPanel,
    WaiverFinanceApplicationRegisterTaskPanel,
    WaiverFinanceApplicationVerifyTaskPanel,
  ],

})
export class WaiverFinanceApplicationSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: WaiverFinanceApplicationSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
        WaiverFinanceApplicationActions,
      ],
    };
  }
}

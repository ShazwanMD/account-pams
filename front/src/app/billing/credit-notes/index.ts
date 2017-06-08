import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService, AccountService} from '../../../services';
import {IdentityModule} from "../../identity/index";
import {CreditNoteCenterPage} from "./credit-note-center.page";
import {CreditNoteCreatorDialog} from "./dialog/credit-note-creator.dialog";
import {CreditNoteActions} from "./credit-note.action";
import {AssignedCreditNoteTaskListComponent} from "./component/assigned-credit-note-task-list.component";
import {PooledCreditNoteTaskListComponent} from "./component/pooled-credit-note-task-list.component";
import {CreditNoteEffects} from "./credit-note.effect";
import {ArchivedCreditNoteListComponent} from "./component/archived-credit-note-list.component";
import {CreditNoteTaskDetailPage} from "./credit-note-task-detail.page";
import {CreditNoteDraftTaskPanel} from "./panel/credit-note-draft-task.panel";
import {CreditNoteVerifyTaskPanel} from "./panel/credit-note-verify-task.panel";
import {CreditNoteTaskWorkflowPanel} from "./panel/credit-note-task-workflow.panel";
import {CreditNoteRegisterTaskPanel} from "./panel/credit-note-register-task.panel";
import {CreditNoteDetailPage} from "./credit-note-detail.page";
import {CreditNoteItemListComponent} from "./component/credit-note-item-list.component";
import {CreditNoteStatusComponent} from "./component/credit-note-status.component";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    EffectsModule.run(CreditNoteEffects),
  ],
  declarations: [
    //page
    CreditNoteCenterPage,
    CreditNoteTaskDetailPage,
    CreditNoteDetailPage,

    //component
    CreditNoteCreatorDialog,
    AssignedCreditNoteTaskListComponent,
    PooledCreditNoteTaskListComponent,
    ArchivedCreditNoteListComponent,
    CreditNoteStatusComponent,
    CreditNoteItemListComponent,
    CreditNoteDraftTaskPanel,
    CreditNoteRegisterTaskPanel,
    CreditNoteVerifyTaskPanel,
    CreditNoteTaskWorkflowPanel,

  ],
  exports: [
    CreditNoteCreatorDialog,
  ],
  entryComponents: [
    CreditNoteCreatorDialog,
    CreditNoteDraftTaskPanel,
    CreditNoteRegisterTaskPanel,
    CreditNoteVerifyTaskPanel,
    CreditNoteTaskWorkflowPanel,

  ],

})
export class CreditNoteSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CreditNoteSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        CreditNoteActions,
      ],
    };
  }
}

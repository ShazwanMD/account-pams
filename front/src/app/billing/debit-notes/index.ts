import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {AccountService} from "../../../services/account.service";
import {IdentityModule} from "../../identity/index";
import {DebitNoteCenterPage} from "./debit-note-center.page";
import {DebitNoteCreatorDialog} from "./dialog/debit-note-creator.dialog";
import {DebitNoteActions} from "./debit-note.action";
import {AssignedDebitNoteTaskListComponent} from "./component/assigned-debit-note-task-list.component";
import {PooledDebitNoteTaskListComponent} from "./component/pooled-debit-note-task-list.component";
import {DebitNoteEffects} from "./debit-note.effect";
import {ArchivedDebitNoteListComponent} from "./component/archived-debit-note-list.component";
import {DebitNoteTaskDetailPage} from "./debit-note-task-detail.page";
import {DebitNoteRegisterTaskPanel} from "./panel/debit-note-register-task.panel";
import {DebitNoteItemEditorDialog} from "./dialog/debit-note-item-editor.dialog";
import {DebitNoteDetailPage} from "./debit-note-detail.page";
import {DebitNoteDraftTaskPanel} from "./panel/debit-note-draft-task.panel";
import {DebitNoteVerifyTaskPanel} from "./panel/debit-note-verify-task.panel";
import {DebitNoteTaskWorkflowPanel} from "./panel/debit-note-task-workflow.panel";
import {DebitNoteStatusComponent} from "./component/debit-note-status.component";
//import {DebitNoteItemListComponent} from "./component/debit-note-item-list.component";
import { ChargeCodeSubModule } from "../../account/charge-codes/index";
import { DebitNoteActionComponent } from "./component/debit-note-action.component";
import {PipeModule} from "../../app.pipe.module";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    IdentityModule.forRoot(),
    EffectsModule.run(DebitNoteEffects),
    PipeModule
  ],
  declarations: [
    //page
    DebitNoteCenterPage,
    DebitNoteTaskDetailPage,
    DebitNoteDetailPage,

    //component
    DebitNoteCreatorDialog,
    AssignedDebitNoteTaskListComponent,
    PooledDebitNoteTaskListComponent,
    ArchivedDebitNoteListComponent,
    DebitNoteRegisterTaskPanel,
    DebitNoteItemEditorDialog,
    DebitNoteDraftTaskPanel,
    DebitNoteRegisterTaskPanel,
    DebitNoteVerifyTaskPanel,
    DebitNoteTaskWorkflowPanel,
    DebitNoteStatusComponent,
    // DebitNoteItemListComponent,
    DebitNoteActionComponent,


  ],
  exports: [
    DebitNoteCreatorDialog,
  ],
  entryComponents: [
    DebitNoteCreatorDialog,
    DebitNoteRegisterTaskPanel,
    DebitNoteItemEditorDialog,
    DebitNoteDraftTaskPanel,
    DebitNoteStatusComponent,
    DebitNoteRegisterTaskPanel,
    DebitNoteVerifyTaskPanel,
    // DebitNoteItemListComponent,
    DebitNoteActionComponent,

  ],

})
export class DebitNoteSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: DebitNoteSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        DebitNoteActions,
      ],
    };
  }
}

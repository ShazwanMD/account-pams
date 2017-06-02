import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {AccountService} from "../../../services/account.service";
import {IdentityModule} from "../../identity/index";
import {DebitNoteCenterPage} from "./debit-note-center.page";
import {DebitNoteCreatorDialog} from "./dialog/debit-note-creator.dialog";
import {DebitNoteActions} from "./debit-note.action";
import {InvoiceSubModule} from "../invoices/index"
import {AssignedDebitNoteTaskListComponent} from "./component/assigned-debit-note-task-list.component";
import {PooledDebitNoteTaskListComponent} from "./component/pooled-debit-note-task-list.component";
import {DebitNoteEffects} from "./debit-note.effect";
import {ArchivedDebitNoteListComponent} from "./component/archived-debit-note-list.component";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    EffectsModule.run(DebitNoteEffects),
  ],
  declarations: [
    //page
    DebitNoteCenterPage,

    //component
    DebitNoteCreatorDialog,
    AssignedDebitNoteTaskListComponent,
    PooledDebitNoteTaskListComponent,
    ArchivedDebitNoteListComponent,

  ],
  exports: [
    DebitNoteCreatorDialog,
  ],
  entryComponents: [
    DebitNoteCreatorDialog,
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

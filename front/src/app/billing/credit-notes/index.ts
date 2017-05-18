import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import { IdentityService, AccountService } from '../../../services';
import {IdentityModule} from "../../identity/index";
import { CreditNoteCenterPage } from "./credit-note-center.page";
import { CreditNoteCreatorDialog } from "./dialog/credit-note-creator.dialog";
import { CreditNoteActions } from "./credit-note.action";
import { InvoiceSubModule } from "../invoices/index";
import { AssignedCreditNoteTaskListComponent } from "./component/assigned-credit-note-task-list.component";
//import { CreditNoteCreatorDialog } from "./dialog/debit-note-creator.dialog";
//import { CreditNoteActions } from "./debit-note.action";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    InvoiceSubModule.forRoot(),
    //EffectsModule.run(ChargeCodeEffects),
  ],
  declarations: [
  //page
  CreditNoteCenterPage,
    

//component
CreditNoteCreatorDialog,
AssignedCreditNoteTaskListComponent,
  ],
  exports: [
    //ChargeCodeSelectComponent,
  ],
  entryComponents: [
  CreditNoteCreatorDialog,
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

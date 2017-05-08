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
import { CreditNoteCenterPage } from "./credit-note-center.page";
//import { CreditNoteCreatorDialog } from "./dialog/debit-note-creator.dialog";
//import { CreditNoteActions } from "./debit-note.action";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    //EffectsModule.run(ChargeCodeEffects),
  ],
  declarations: [
  //page
  CreditNoteCenterPage,
    

//component
//DebitNoteCreatorDialog,
  ],
  exports: [
    //ChargeCodeSelectComponent,
  ],
  entryComponents: [
    //DebitNoteCreatorDialog,
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
        //CreditNoteActions,
      ],
    };
  }
}

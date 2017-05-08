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
import { DebitNoteCenterPage } from "./debit-note-center.page";
import { DebitNoteCreatorDialog } from "./dialog/debit-note-creator.dialog";
import { DebitNoteActions } from "./debit-note.action";


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
  DebitNoteCenterPage,
    

//component
DebitNoteCreatorDialog,
  ],
  exports: [
    //ChargeCodeSelectComponent,
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

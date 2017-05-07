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
//ChargeCodeCreatorDialog,
  ],
  exports: [
    //ChargeCodeSelectComponent,
  ],
  entryComponents: [
    //ChargeCodeCreatorDialog,
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
        //ChargeCodeActions,
      ],
    };
  }
}

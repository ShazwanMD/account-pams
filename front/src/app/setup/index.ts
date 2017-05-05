import { SetupPage } from './setup.page';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';
import {ActionReducer, combineReducers} from "@ngrx/store";
import {NgModule, ModuleWithProviders} from "@angular/core";

import {CovalentCoreModule} from '@covalent/core';



@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
  ],
  declarations: [
    SetupPage,

  ],
  exports: [],
  entryComponents: [

  ],

})
export class SetupModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SetupModule,
      providers: [
        appRoutingProviders,
      ],
    };
  }
}

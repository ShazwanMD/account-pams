import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {CovalentCoreModule,CovalentFileModule} from '@covalent/core';
import {CommonService} from '../../../services';
import {ReportService} from '../../../services';
import {CommonModule} from '../../common/index';
import { ReportActions } from "./report.action";
import { ReportEffects } from "./report.effect";
import { EffectsModule } from "@ngrx/effects";
import { PipeModule } from "../../app.pipe.module";
import { AppComponent } from "../../app.component";

export interface ReportModuleState {
}

export const INITIAL_REPORT_STATE: ReportModuleState = <ReportModuleState>{

};

@NgModule({
  imports: [
    appRoutes,
    ReactiveFormsModule,
    BrowserModule,
    CovalentFileModule.forRoot(),
    CovalentCoreModule.forRoot(),
    CommonModule.forRoot(),
    EffectsModule.run(ReportEffects),
    PipeModule,
  ],
  declarations: [

    
  ],
  exports: [

  ],

  entryComponents: [

  ],
})
export class ReportModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ReportModule,
      providers: [
        appRoutingProviders,
        ReportService,
        ReportActions,
      ],
    };
  }
}
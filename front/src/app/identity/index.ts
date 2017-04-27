import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {ActorActions} from "./actor.action";
import {actorListReducer, ActorListState} from "./actor-list.reducer";
import {studentListReducer, StudentListState} from "./student-list.reducer";
import {sponsorListReducer, SponsorListState} from "./sponsor-list.reducer";
import {SponsorActions} from "./sponsor.action";
import {StudentActions} from "./student.action";
import {ActorSelectComponent} from "./component/actor-select.component";
import {StudentSelectComponent} from "./component/student-select.component";
import {SponsorSelectComponent} from "./component/sponsor-select.component";
import {SponsorEffects} from "./sponsor.effect";
import {EffectsModule} from "@ngrx/effects";
import {StudentEffects} from "./student.effect";
import {ActorEffects} from "./actor.effect";
export interface IdentityModuleState {
  actors: ActorListState;
  students: StudentListState;
  sponsors: SponsorListState;
};

export const INITIAL_IDENTITY_STATE: IdentityModuleState = <IdentityModuleState>{};
export const identityModuleReducers = {
  actors:actorListReducer,
  students:studentListReducer,
  sponsors:sponsorListReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(ActorEffects),
    EffectsModule.run(StudentEffects),
    EffectsModule.run(SponsorEffects),
  ],
  declarations: [
    ActorSelectComponent,
    StudentSelectComponent,
    SponsorSelectComponent,
  ],
  exports: [
    ActorSelectComponent,
    StudentSelectComponent,
    SponsorSelectComponent,
  ],
})
export class IdentityModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: IdentityModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        IdentityService,
        ActorActions,
        StudentActions,
        SponsorActions,
      ],
    };
  }
}

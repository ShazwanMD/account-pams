import {NgModule, Type} from '@angular/core';
import {BrowserModule, Title}  from '@angular/platform-browser';
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {NgxChartsModule} from '@swimlane/ngx-charts';
import {StoreModule, combineReducers, ActionReducer} from "@ngrx/store";

import {CovalentCoreModule} from '@covalent/core';
import {CovalentHttpModule, IHttpInterceptor} from '@covalent/http';
import {CovalentHighlightModule} from '@covalent/highlight';
import {CovalentMarkdownModule} from '@covalent/markdown';
import {CovalentChartsModule} from '@covalent/charts';
import {AppComponent} from './app.component';
import {MainComponent} from './main/main.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {appRoutes, appRoutingProviders} from './app.routes';

import {RequestInterceptor} from '../config/interceptors/request.interceptor';

import {HomeComponent} from "./home/home.component";
import {AccountModule, accountModuleReducer, AccountModuleState} from "./account/index";
import {CustomUrlSerializer} from "./common/custom-url-serializer";
import {UrlSerializer} from "@angular/router";
import {BillingModule, billingModuleReducer, BillingModuleState} from "./billing/index";

const httpInterceptorProviders: Type<any>[] = [
  RequestInterceptor,
];


// state
interface ApplicationState {
  accountModuleState: AccountModuleState;
}
;

// reducer
const INITIAL_APPLICATION_STATE: ApplicationState = <ApplicationState>{accountModuleState:<AccountModuleState>{}};
const reducers = {accountModuleState: accountModuleReducer};
const productionReducer: ActionReducer<ApplicationState> = combineReducers(reducers);
function applicationReducer(state: any = INITIAL_APPLICATION_STATE, action: any) {
  return productionReducer(state, action);
}

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    HomeComponent,
    DashboardComponent,
    LoginComponent,

  ], // directives, components, and pipes owned by this NgModule
  imports: [
    appRoutes,
    BrowserModule,
    CovalentCoreModule.forRoot(),
    CovalentChartsModule.forRoot(),
    CovalentHttpModule.forRoot({
      interceptors: [{
        interceptor: RequestInterceptor, paths: ['**'],
      }],
    }),
    CovalentHighlightModule.forRoot(),
    CovalentMarkdownModule.forRoot(),
    NgxChartsModule,

    AccountModule.forRoot(),
    BillingModule.forRoot(),
    StoreModule.provideStore(applicationReducer),
    StoreDevtoolsModule.instrumentOnlyWithExtension(),

  ], // modules needed to run this module
  providers: [
    appRoutingProviders,
    httpInterceptorProviders,
    Title,
    {provide: UrlSerializer, useClass: CustomUrlSerializer}
  ], // additional providers needed for this module
  entryComponents: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}

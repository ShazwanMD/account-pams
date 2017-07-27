import {NgModule, Type} from '@angular/core';
import {BrowserModule, Title}  from '@angular/platform-browser';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import {StoreModule, combineReducers, ActionReducer} from '@ngrx/store';

import {CovalentCoreModule} from '@covalent/core';
import {CovalentHttpModule, IHttpInterceptor} from '@covalent/http';
import {CovalentHighlightModule} from '@covalent/highlight';
import {CovalentMarkdownModule} from '@covalent/markdown';
import {CovalentChartsModule} from '@covalent/charts';
import {AppComponent} from './app.component';
import {appRoutes, appRoutingProviders} from './app.routes';

import {RequestInterceptor} from '../config/interceptors/request.interceptor';

import {HomeComponent} from './home/home.component';
import {CustomUrlSerializer} from './common/custom-url-serializer';
import {UrlSerializer} from '@angular/router';
import {INITIAL_SETUP_STATE, SetupModule, setupModuleReducers, SetupModuleState} from './secure/setup/index';
import {AccountModuleState, INITIAL_ACCOUNT_STATE, AccountModule, accountModuleReducers} from './secure/account/index';
import {BillingModuleState, INITIAL_BILLING_STATE, BillingModule, billingModuleReducers} from './secure/billing/index';
import {
  FinancialaidModule, INITIAL_FINANCIALAID_STATE, FinancialaidModuleState,
  financialaidModuleReducers,
} from './secure/financialaid/index';
import {MarketingModule, INITIAL_MARKETING_STATE, marketingModuleReducers} from './secure/marketing/index';
import {
  IdentityModule,
  identityModuleReducers,
  IdentityModuleState,
  INITIAL_IDENTITY_STATE,
} from './secure/identity/index';
import {CommonModule, commonModuleReducers, CommonModuleState, INITIAL_COMMON_STATE} from './common/index';
import {DashboardModule} from './dashboard/index';
import {PipeModule} from './app.pipe.module';
import {environment} from '../environments/environment';
import {LoginPage} from './login/login.page';
import {
  applicationContextReducer, ApplicationContextState,
  INITIAL_APPLICATION_CONTEXT_STATE,
} from './application-context.reducer';

const httpInterceptorProviders: Type<any>[] = [
  RequestInterceptor,
];

// state
export interface ApplicationState {
  applicationContextState: ApplicationContextState;
  setupModuleState: SetupModuleState;
  commonModuleState: CommonModuleState;
  identityModuleState: IdentityModuleState;
  accountModuleState: AccountModuleState;
  billingModuleState: BillingModuleState;
  financialaidModuleState: FinancialaidModuleState;
}

// reducer
export const INITIAL_APPLICATION_STATE: ApplicationState =
  <ApplicationState>{
    applicationContextState: INITIAL_APPLICATION_CONTEXT_STATE,
    setupModuleState: INITIAL_SETUP_STATE,
    commonModuleState: INITIAL_COMMON_STATE,
    identityModuleState: INITIAL_IDENTITY_STATE,
    accountModuleState: INITIAL_ACCOUNT_STATE,
    billingModuleState: INITIAL_BILLING_STATE,
    financialaidModuleState: INITIAL_FINANCIALAID_STATE,
    marketingModuleState: INITIAL_MARKETING_STATE,
  };

export const applicationReducers = {
  applicationContextState: applicationContextReducer,
  setupModuleState: combineReducers({...setupModuleReducers}),
  commonModuleState: combineReducers({...commonModuleReducers}),
  identityModuleState: combineReducers({...identityModuleReducers}),
  accountModuleState: combineReducers({...accountModuleReducers}),
  billingModuleState: combineReducers({...billingModuleReducers}),
  financialaidModuleState: combineReducers({...financialaidModuleReducers}),
  marketingModuleState: combineReducers({...marketingModuleReducers}),
};

export const productionReducer: ActionReducer<ApplicationState> = combineReducers(applicationReducers);
export function applicationReducer(applicationState: any = INITIAL_APPLICATION_STATE, action: any) {
  return productionReducer(applicationState, action);
}

@NgModule({
  declarations: [
    AppComponent,
    LoginPage,

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

    StoreModule.provideStore(applicationReducer),
    environment.imports,
    CommonModule.forRoot(),

    PipeModule.forRoot(),
    IdentityModule.forRoot(),
    AccountModule.forRoot(),
    BillingModule.forRoot(),
    FinancialaidModule.forRoot(),
    MarketingModule.forRoot(),
    SetupModule.forRoot(),
    DashboardModule.forRoot(),
  ], // modules needed to run this module
  providers: [
    appRoutingProviders,
    httpInterceptorProviders,
    Title,
    {provide: UrlSerializer, useClass: CustomUrlSerializer},
  ], // additional providers needed for this module
  entryComponents: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}

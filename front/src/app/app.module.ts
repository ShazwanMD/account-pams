import { SponsorDashboardPanel } from './secure/sponsor-dashboard.panel';
import { ManagementDashboardPanel } from './secure/management-dashboard.panel';
import { StudentDashboardPanel } from './secure/student-dashboard.panel';
import { PtjDashboardPanel } from './secure/ptj-dashboard.panel';
import {NgModule, Type} from '@angular/core';
import {BrowserModule, Title} from '@angular/platform-browser';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import {ActionReducer, combineReducers, StoreModule} from '@ngrx/store';

import {CovalentCoreModule} from '@covalent/core';
import {CovalentHttpModule} from '@covalent/http';
import {CovalentHighlightModule} from '@covalent/highlight';
import {CovalentMarkdownModule} from '@covalent/markdown';
import {CovalentChartsModule} from '@covalent/charts';
import {AppComponent} from './app.component';
import {appRoutes, appRoutingProviders} from './app.routes';

import {RequestInterceptor} from '../config/interceptors/request.interceptor';
import {INITIAL_SETUP_STATE, SetupModule, setupModuleReducers, SetupModuleState} from './secure/setup/index';
import {AccountModule, accountModuleReducers, AccountModuleState, INITIAL_ACCOUNT_STATE} from './secure/account/index';
import {BillingModule, billingModuleReducers, BillingModuleState, INITIAL_BILLING_STATE} from './secure/billing/index';
import {
  FinancialaidModule,
  financialaidModuleReducers,
  FinancialaidModuleState,
  INITIAL_FINANCIALAID_STATE
} from './secure/financialaid/index';
import {INITIAL_MARKETING_STATE, MarketingModule, marketingModuleReducers} from './secure/marketing/index';
import {
  IdentityModule,
  identityModuleReducers,
  IdentityModuleState,
  INITIAL_IDENTITY_STATE
} from './secure/identity/index';
import {CommonModule, commonModuleReducers, CommonModuleState, INITIAL_COMMON_STATE} from './common/index';
import {DashboardModule} from './dashboard/index';
import {PipeModule} from './app.pipe.module';
import {environment} from '../environments/environment';
import {LoginPage} from './login/login.page';
import {
  applicationContextReducer,
  ApplicationContextState,
  INITIAL_APPLICATION_CONTEXT_STATE
} from './application-context.reducer';
import {ForgetPasswordPage} from './login/forget-password.page';
import {SecurePage} from './secure/secure.page';
import {HomePage} from './home/home.page';
import {AuthorizationGuard} from './secure/identity/guard/authorization.guard';
import {AuthenticationGuard} from './secure/identity/guard/authentication.guard';
import {SystemService} from '../services/system.service';
import {AuthorizationService} from '../services/authorization.service';
import {AlertService} from '../services/alert.service';
import {AuthenticationService} from '../services/authentication.service';
import {ApplicationContextActions} from './application-context.action';
import {ReactiveFormsModule} from '@angular/forms';
import {DashboardPage} from './secure/dashboard.page';
import {AdministratorDashboardPanel} from './secure/administrator-dashboard.panel';
import {AuthorizedShowDirective} from './secure/identity/directive/authorized-show.directive';
import {AuthenticatedShowDirective} from './secure/identity/directive/authenticated-show.directive';
import {NotAuthenticatedShowDirective} from './secure/identity/directive/not-authenticated-show.directive';
import { BursaryDashboardPanel } from './secure/bursary-dashboard.panel';

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
    HomePage,
    SecurePage,
    LoginPage,
    ForgetPasswordPage,
    DashboardPage,
    ForgetPasswordPage,
    AdministratorDashboardPanel,
    BursaryDashboardPanel,
    PtjDashboardPanel,
    StudentDashboardPanel,
    ManagementDashboardPanel,
    SponsorDashboardPanel,
    AuthorizedShowDirective,
    AuthenticatedShowDirective,
    NotAuthenticatedShowDirective,
  ], // directives, components, and pipes owned by this NgModule
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
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
    AuthenticationService,
    AlertService,
    AuthorizationService,
    SystemService,
    AuthenticationGuard,
    AuthorizationGuard,
    Title,
    ApplicationContextActions,
  ], // additional providers needed for this module
  entryComponents: [
    AdministratorDashboardPanel,
    PtjDashboardPanel,
    StudentDashboardPanel,
    ManagementDashboardPanel,
    SponsorDashboardPanel,
    BursaryDashboardPanel,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {
}

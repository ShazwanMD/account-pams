import {RouterModule, Routes} from '@angular/router';
import {AccountModuleRoutes} from './secure/account/account-module.routes';
import {BillingModuleRoutes} from './secure/billing/billing-module.routes';
import {FinancialaidModuleRoutes} from './secure/financialaid/financialaid-module.routes';
import {MarketingModuleRoutes} from './secure/marketing/marketing-module.routes';
import {ListingModuleRoutes} from './secure/listing/listing-module.routes';
import {SetupModuleRoutes} from './secure/setup/setup-module.routes';
import {SecurePage} from './secure/secure.page';
import {ForgetPasswordPage} from './login/forget-password.page';
import {LoginPage} from './login/login.page';
import {AuthenticationGuard} from './secure/identity/guard/authentication.guard';
import {HomePage} from './home/home.page';
import {DashboardPage} from './secure/dashboard.page';

const routes: Routes = [
  {path: '', component: LoginPage},
  {path: 'login', component: LoginPage},
  {path: 'forget-password', component: ForgetPasswordPage},
  {
    path: 'secure', component: SecurePage, canActivate: [AuthenticationGuard],
    children: [
      {path: '', component: DashboardPage},
      ...AccountModuleRoutes,
      ...BillingModuleRoutes,
      ...FinancialaidModuleRoutes,
      ...MarketingModuleRoutes,
      ...ListingModuleRoutes,
      ...SetupModuleRoutes,
    ],
  },
];

export const appRoutingProviders: any[] = [];

export const appRoutes: any = RouterModule.forRoot(routes, {useHash: false});

import {Routes} from '@angular/router';
import {MarketingPage} from './marketing.page';
import {PromoCodeRoutes} from './promo-codes/promo-code.routes';

export const MarketingModuleRoutes: Routes = [
  {path: 'marketing', component: MarketingPage},
  ...PromoCodeRoutes,
];

import {Routes, RouterModule} from '@angular/router';
import {PromoCodeCenterPage} from './promo-code-center.page';
import {PromoCodeDetailPage} from './promo-code-detail.page';

export const PromoCodeRoutes: Routes = [
  {path: 'marketing/promo-codes', component: PromoCodeCenterPage},
  {path: 'marketing/promo-codes/:referenceNo', component: PromoCodeDetailPage},
];

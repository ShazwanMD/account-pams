import {Routes} from '@angular/router';

import {BillingPage} from "./billing.page";

// Route Configuration
export const BillingModuleRoutes: Routes = [
  {path: 'billing', component: BillingPage},
  // ...InvoiceRoutes,
  // ...ReceiptRoutes,
  // ...VoucherRoutes,
];

import {Routes} from '@angular/router';

import {BillingPage} from "./billing.page";
import {VoucherRoutes} from "./vouchers/voucher.routes";
import {ReceiptRoutes} from "./receipts/receipt.routes";
import {InvoiceRoutes} from "./invoices/invoice.routes";

// Route Configuration
export const BillingModuleRoutes: Routes = [
  {path: 'billing', component: BillingPage},
  ...InvoiceRoutes,
  ...ReceiptRoutes,
  ...VoucherRoutes,
];

import {Routes} from '@angular/router';
import {ListingPage} from './listing.page';
import { ListingInvoiceModuleRoutes } from './listing-invoice/listing-invoice-module.routes';
import { ListingReceiptModuleRoutes } from './listing-receipt/listing-receipt-module.routes';
import { ListingDebitNoteModuleRoutes } from './listing-debit-note/listing-debit-note-module.routes';
import { ListingCreditNoteModuleRoutes } from './listing-credit-note/listing-credit-note-module.routes';
import { ListingRefundPaymentModuleRoutes } from './listing-refund-payment/listing-refund-payment-module.routes';
import { ListingKnockoffPaymentModuleRoutes } from './listing-knockoff-payment/listing-knockoff-payment-module.routes';
import { AgeingModuleRoutes } from './ageing/ageing-module.routes';
import { DebtorsModuleRoutes } from './debtors/debtors-module.routes';

export const ListingModuleRoutes: Routes = [
  {path: 'listing', component: ListingPage},
   ...ListingInvoiceModuleRoutes,
   ...ListingReceiptModuleRoutes,
   ...ListingDebitNoteModuleRoutes,
   ...ListingCreditNoteModuleRoutes,
   ...ListingRefundPaymentModuleRoutes,
   ...ListingKnockoffPaymentModuleRoutes,
   ...AgeingModuleRoutes,
   ...DebtorsModuleRoutes,
];
import {Routes} from '@angular/router';
import {ListingPage} from './listing.page';
import { ListingInvoiceModuleRoutes } from './listing-invoice/listing-invoice-module.routes';
import { ListingReceiptModuleRoutes } from './listing-receipt/listing-receipt-module.routes';

export const ListingModuleRoutes: Routes = [
  {path: 'listing', component: ListingPage},
   ...ListingInvoiceModuleRoutes,
   ...ListingReceiptModuleRoutes,
];
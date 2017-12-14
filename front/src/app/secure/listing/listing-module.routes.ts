import {Routes} from '@angular/router';
import {ListingPage} from './listing.page';
import { ListingInvoiceModuleRoutes } from './listing-invoice/listing-module.routes';

export const ListingModuleRoutes: Routes = [
  {path: 'listing', component: ListingPage},
   ...ListingInvoiceModuleRoutes,
];
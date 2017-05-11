import {Routes, RouterModule} from '@angular/router';
import {InvoiceCenterPage} from "./invoice-center.page";
import {InvoiceTaskViewPage} from "./invoice-task-view.page";


export const InvoiceRoutes: Routes = [
  {path: 'billing/invoices', component: InvoiceCenterPage},
  {path: 'billing/invoices/view-task/:taskId', component: InvoiceTaskViewPage},
];

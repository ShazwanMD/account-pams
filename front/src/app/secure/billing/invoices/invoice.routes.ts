import {Routes, RouterModule} from '@angular/router';
import {InvoiceCenterPage} from './invoice-center.page';
import {InvoiceTaskDetailPage} from './invoice-task-detail.page';
import {InvoiceDetailPage} from './invoice-detail.page';

export const InvoiceRoutes: Routes = [
  {path: 'billing/invoices', component: InvoiceCenterPage},
  {path: 'billing/invoices/invoice-task-detail/:taskId', component: InvoiceTaskDetailPage},
  {path: 'billing/invoices/invoice-detail/:referenceNo', component: InvoiceDetailPage},
];

import {Routes, RouterModule} from '@angular/router';
import {InvoiceCenterPage} from "./invoice-center.page";
import {InvoiceViewTaskPage} from "./invoice-view-task.page";


// Route Configuration
export const InvoiceRoutes: Routes = [
  {path: 'billing/invoices', component: InvoiceCenterPage},
  // {path: 'billing/invoices/new-task', component: InvoiceNewTaskPage},
  // {path: 'billing/invoices/assigned-tasks', component: InvoiceAssignedTaskPage},
  // {path: 'billing/invoices/pooled-tasks', component: InvoicePooledTaskPage},
  {path: 'billing/invoices/view-task/:taskId', component: InvoiceViewTaskPage},
];

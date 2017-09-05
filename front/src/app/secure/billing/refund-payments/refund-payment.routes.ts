import {Routes} from '@angular/router';
import {RefundPaymentCenterPage} from './refund-payment-center.page';
import {RefundPaymentTaskDetailPage} from './refund-payment-task-detail.page';
import { RefundPaymentDetailPage } from "./refund-payment-detail.page";
//import {RefundPaymentDetailPage} from './refund-payment-detail.page';

export const RefundPaymentRoutes: Routes = [
  {path: 'billing/refund-payments', component: RefundPaymentCenterPage},
  {path: 'billing/refund-payments/refund-payment-task-detail/:taskId', component: RefundPaymentTaskDetailPage},
  {path: 'billing/refund-payments/refund-payment-detail/:referenceNo', component: RefundPaymentDetailPage},
];

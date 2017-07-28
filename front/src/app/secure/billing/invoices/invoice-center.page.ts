import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {InvoiceActions} from './invoice.action';
import {InvoiceTask} from '../../../shared/model/billing/invoice-task.interface';
import {BillingModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {InvoiceTaskCreatorDialog} from './dialog/invoice-task-creator.dialog';
import {Invoice} from '../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-invoice-center',
  templateUrl: './invoice-center.page.html',
})

export class InvoiceCenterPage implements OnInit {

  private ASSIGNED_INVOICE_TASKS = 'billingModuleState.assignedInvoiceTasks'.split('.');
  private POOLED_INVOICE_TASKS = 'billingModuleState.pooledInvoiceTasks'.split('.');
  private ARCHIVED_INVOICES = 'billingModuleState.archivedInvoices'.split('.');
  private assignedInvoiceTasks$: Observable<InvoiceTask[]>;
  private pooledInvoiceTasks$: Observable<InvoiceTask[]>;
  private archivedInvoices$: Observable<Invoice[]>;
  private creatorDialogRef: MdDialogRef<InvoiceTaskCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: InvoiceActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedInvoiceTasks$ = this.store.select(...this.ASSIGNED_INVOICE_TASKS);
    this.pooledInvoiceTasks$ = this.store.select(...this.POOLED_INVOICE_TASKS);
    this.archivedInvoices$ = this.store.select(...this.ARCHIVED_INVOICES);
  }

  goBack(route: string): void {
    this.router.navigate(['/invoices']);
  }

  claimTask(task: InvoiceTask) {
    console.log('invoice: ' + task.taskId);
    this.store.dispatch(this.actions.claimInvoiceTask(task));
  }

  viewTask(task: InvoiceTask) {
    console.log('invoice: ' + task.taskId);
    this.router.navigate(['/secure/billing/invoices/invoice-task-detail', task.taskId]);
  }

  viewInvoice(invoice: Invoice) {
    console.log('invoice: ' + invoice.referenceNo);
    this.router.navigate(['/secure/billing/invoices/invoice-detail', invoice.referenceNo]);
  }

  showDialog(): void {
    console.log('showDialog');
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(InvoiceTaskCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  ngOnInit(): void {
    console.log('find assigned invoice tasks');
    this.store.dispatch(this.actions.findAssignedInvoiceTasks());
    this.store.dispatch(this.actions.findPooledInvoiceTasks());
    this.store.dispatch(this.actions.findArchivedInvoices());
  }
}


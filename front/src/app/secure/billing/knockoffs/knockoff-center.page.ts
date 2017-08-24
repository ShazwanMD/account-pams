import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {KnockoffActions} from './knockoff.action';
import {KnockoffTask} from '../../../shared/model/billing/knockoff-task.interface';
import {BillingModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Invoice} from '../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-knockoff-center',
  templateUrl: './knockoff-center.page.html',
})

export class KnockoffCenterPage implements OnInit {

  private ASSIGNED_KNOCKOFF_TASKS = 'billingModuleState.assignedKnockoffTasks'.split('.');
//  private POOLED_INVOICE_TASKS = 'billingModuleState.pooledInvoiceTasks'.split('.');
//  private ARCHIVED_INVOICES = 'billingModuleState.archivedInvoices'.split('.');
  private assignedKnockoffTasks$: Observable<KnockoffTask[]>;
//  private pooledInvoiceTasks$: Observable<InvoiceTask[]>;
//  private archivedInvoices$: Observable<Invoice[]>;
//  private creatorDialogRef: MdDialogRef<InvoiceTaskCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: KnockoffActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedKnockoffTasks$ = this.store.select(...this.ASSIGNED_KNOCKOFF_TASKS);
//    this.pooledInvoiceTasks$ = this.store.select(...this.POOLED_INVOICE_TASKS);
//    this.archivedInvoices$ = this.store.select(...this.ARCHIVED_INVOICES);
  }

  goBack(route: string): void {
    this.router.navigate(['/invoices']);
  }

  claimTask(task: KnockoffTask) {
    console.log('invoice: ' + task.taskId);
    this.store.dispatch(this.actions.claimKnockoffTask(task));
  }

  viewTask(task: KnockoffTask) {
    console.log('Task: ' + task.taskId);
    this.router.navigate(['/secure/billing/knockoffs/knockoff-task-detail', task.taskId]);
  }

//  viewInvoice(invoice: Invoice) {
//    console.log('invoice: ' + invoice.referenceNo);
//    this.router.navigate(['/secure/billing/invoices/invoice-detail', invoice.referenceNo]);
//  }
//
//  showDialog(): void {
//    console.log('showDialog');
//    let config: MdDialogConfig = new MdDialogConfig();
//    config.viewContainerRef = this.vcf;
//    config.role = 'dialog';
//    config.width = '50%';
//    config.height = '65%';
//    config.position = {top: '0px'};
//    this.creatorDialogRef = this.dialog.open(InvoiceTaskCreatorDialog, config);
//    this.creatorDialogRef.afterClosed().subscribe((res) => {
//      console.log('close dialog');
//      // load something here
//    });
//  }

  ngOnInit(): void {
    console.log('find assigned knockoff tasks');
    this.store.dispatch(this.actions.findAssignedKnockoffTasks());
//    this.store.dispatch(this.actions.findPooledInvoiceTasks());
//    this.store.dispatch(this.actions.findArchivedInvoices());
  }
}


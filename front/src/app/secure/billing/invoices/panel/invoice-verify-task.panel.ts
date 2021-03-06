import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {InvoiceItemEditorDialog} from '../dialog/invoice-item-editor.dialog';
import {InvoiceActions} from '../invoice.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {InvoiceTask} from '../../../../shared/model/billing/invoice-task.interface';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';

@Component({
  selector: 'pams-invoice-verify-task',
  templateUrl: './invoice-verify-task.panel.html',
})

export class InvoiceVerifyTaskPanel implements OnInit {

  private INVOICE_ITEMS = 'billingModuleState.invoiceItems'.split('.');
  private invoiceItems$: Observable<InvoiceItem[]>;
  @Input() invoiceTask: InvoiceTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: InvoiceActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findInvoiceItems(this.invoiceTask.invoice));
  }

  editItem(item: InvoiceItem) {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(InvoiceItemEditorDialog, config);
    editorDialogRef.componentInstance.invoiceItem = item;
  }

  approve() {
    this.store.dispatch(this.actions.completeInvoiceTask(this.invoiceTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/invoices']);
  }
}

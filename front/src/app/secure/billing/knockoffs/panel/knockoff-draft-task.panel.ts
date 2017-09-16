import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {KnockoffActions} from '../knockoff.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {KnockoffTask} from '../../../../shared/model/billing/knockoff-task.interface';
import { TdDialogService } from "@covalent/core";
import { InvoiceActions } from "../../invoices/invoice.action";
import { KnockoffInvoice } from "../../../../shared/model/billing/knockoff-invoice.interface";

@Component({
  selector: 'pams-knockoff-draft-task',
  templateUrl: './knockoff-draft-task.panel.html',
})

export class KnockoffDraftTaskPanel implements OnInit {

  @Input() knockoffTask: KnockoffTask;
  
  private KNOCKOFF_INVOICE: string[] = 'billingModuleState.knockoffInvoice'.split('.');
  private knockoffInvoice$: Observable<KnockoffInvoice[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: KnockoffActions,
              private action: InvoiceActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
      this.knockoffInvoice$ = this.store.select(...this.KNOCKOFF_INVOICE);
  }

  ngOnInit(): void {
      this.store.dispatch(this.actions.findKnockoffsByInvoice(this.knockoffTask.knockoff));
      this.store.dispatch(this.action.findUnpaidInvoices(this.knockoffTask.knockoff.payments.account));
  }

  register() {
    this.store.dispatch(this.actions.completeKnockoffTask(this.knockoffTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/knockoffs']);
  }

}

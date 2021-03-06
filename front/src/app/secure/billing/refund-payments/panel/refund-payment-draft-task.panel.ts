import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {RefundPaymentActions} from '../refund-payment.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {RefundPaymentTask} from '../../../../shared/model/billing/refund-payment-task.interface';
import { TdDialogService } from "@covalent/core";

@Component({
  selector: 'pams-refund-payment-draft-task',
  templateUrl: './refund-payment-draft-task.panel.html',
})

export class RefundPaymentDraftTaskPanel implements OnInit {

  @Input() refundPaymentTask: RefundPaymentTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: RefundPaymentActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
    //this.store.dispatch(this.actions.findInvoiceItems(this.invoiceTask.invoice));
  }

  register() {
    this.store.dispatch(this.actions.completeRefundPaymentTask(this.refundPaymentTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/refund-payments']);
  }
  
  cancel(): void {
      console.log("Refund" + this.refundPaymentTask.refundPayment);
      this._dialogService.openConfirm({
        message: 'Cancel Refund Payments ' + this.refundPaymentTask.refundPayment.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeRefundPaymentTask(this.refundPaymentTask));
          this.router.navigate(['/secure/billing/refund-payments']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }

}

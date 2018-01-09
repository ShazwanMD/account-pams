import { DebitNoteActions } from './../../debit-notes/debit-note.action';
import { KnockoffDebitNote } from './../../../../shared/model/billing/knockoff-debit-note.interface';
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
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";
import { KnockoffAccountCharge } from '../../../../shared/model/billing/knockoff-account-charge.interface';
import { AccountActions } from '../../../account/accounts/account.action';

@Component({
  selector: 'pams-knockoff-draft-task',
  templateUrl: './knockoff-draft-task.panel.html',
})

export class KnockoffDraftTaskPanel implements OnInit {

  @Input() knockoffTask: KnockoffTask;
  
  private KNOCKOFF_INVOICE: string[] = 'billingModuleState.knockoffInvoice'.split('.');
  private KNOCKOFF_ACCOUNT_CHARGE: string[] = 'billingModuleState.knockoffAccountCharge'.split('.');
  private KNOCKOFF_DEBIT_NOTE: string[] = 'billingModuleState.knockoffDebitNote'.split('.');
  private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
  private knockoffInvoice$: Observable<KnockoffInvoice[]>;
  private knockoffAccountCharge$: Observable<KnockoffAccountCharge[]>;
  private knockoffDebitNote$: Observable<KnockoffDebitNote[]>;
  private knockoffItem$: Observable<KnockoffItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: KnockoffActions,
              private action: InvoiceActions,
              private actionCharge: AccountActions,
              private dbtAction: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
      this.knockoffInvoice$ = this.store.select(...this.KNOCKOFF_INVOICE);
      this.knockoffAccountCharge$ = this.store.select(...this.KNOCKOFF_ACCOUNT_CHARGE);
      this.knockoffDebitNote$ = this.store.select(...this.KNOCKOFF_DEBIT_NOTE);
      this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
  }

  ngOnInit(): void {
      this.store.dispatch(this.actions.findKnockoffsByInvoice(this.knockoffTask.knockoff));
      //this.store.dispatch(this.actions.findKnockoffItems(this.knockoffTask.knockoff));
      this.store.dispatch(this.action.findUnpaidInvoices(this.knockoffTask.knockoff.payments.account));
      this.store.dispatch(this.actions.findKnockoffsByAccountCharge(this.knockoffTask.knockoff));
      this.store.dispatch(this.actionCharge.findUnpaidAccountCharges(this.knockoffTask.knockoff.payments.account));
      this.store.dispatch(this.actions.findKnockoffsByDebitNote(this.knockoffTask.knockoff));
      this.store.dispatch(this.dbtAction.findUnpaidDebitNotes(this.knockoffTask.knockoff.payments.account));
  }

  register() {
    this.store.dispatch(this.actions.completeKnockoffTask(this.knockoffTask));
    this.goBack();
    
    let snackBarRef = this.snackBar.open( 'Knockoff Item has not been inserted', 'OK' );
    snackBarRef.afterDismissed().subscribe(() => {
    } );
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/knockoffs']);
  }

  cancel(): void {
      console.log("Knockoff " + this.knockoffTask.knockoff);
      this._dialogService.openConfirm({
        message: 'Cancel Knockoff ' + this.knockoffTask.knockoff.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeKnockoffTask(this.knockoffTask));
          this.router.navigate(['/secure/billing/knockoffs']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}

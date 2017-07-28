import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {ReceiptTask} from '../../../../shared/model/billing/receipt-task.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';

@Component({
  selector: 'pams-receipt-register-task',
  templateUrl: './receipt-register-task.panel.html',
})

export class ReceiptRegisterTaskPanel implements OnInit {

  private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
  @Input() receiptTask: ReceiptTask;
  receiptItems$: Observable<ReceiptItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findReceiptItems(this.receiptTask.receipt));
  }

  editItem(item: ReceiptItem) {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(ReceiptItemEditorDialog, config);
    editorDialogRef.componentInstance.receiptItem = item;
  }

  approve() {
    this.store.dispatch(this.actions.completeReceiptTask(this.receiptTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/receipts']);
  }
}

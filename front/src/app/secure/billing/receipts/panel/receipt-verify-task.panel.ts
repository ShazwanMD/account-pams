import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import { TdDialogService } from "@covalent/core";

@Component({
  selector: 'pams-receipt-verify-task',
  templateUrl: './receipt-verify-task.panel.html',
})

export class ReceiptVerifyTaskPanel implements OnInit {

  private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
  @Input() receipt: Receipt;
  receiptItems$: Observable<ReceiptItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
    this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findReceiptItems(this.receipt));
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

  goBack(): void {
    this.router.navigate(['/secure/billing/receipts']);
  }
  
  cancel(): void {
      console.log("Receipt " + this.receipt);
      this._dialogService.openConfirm({
        message: 'Cancel Receipt ' + this.receipt.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeReceiptTask(this.receipt));
          this.router.navigate(['/secure/billing/receipts']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}

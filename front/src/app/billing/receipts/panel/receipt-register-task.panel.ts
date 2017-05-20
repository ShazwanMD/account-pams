import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptItem} from "../receipt-item.interface";
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from "@angular/material";
import {ReceiptItemEditorDialog} from "../dialog/receipt-item-editor.dialog";
import {ReceiptTask} from "../receipt-task.interface";
import {ReceiptActions} from "../receipt.action";
import {ReceiptTaskState} from "../receipt-task.reducer";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {BillingModuleState} from "../../index";


@Component({
  selector: 'pams-receipt-register-task',
  templateUrl: './receipt-register-task.panel.html',
})

export class ReceiptRegisterTaskPanel implements OnInit {

  private RECEIPT_ITEMS = "billingModuleState.receiptItems".split(".");
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
    this.store.dispatch(this.actions.findReceiptItems(this.receiptTask.receipt))
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
    this.router.navigate(['/billing/receipts']);
  }
}

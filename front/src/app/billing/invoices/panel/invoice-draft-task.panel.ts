import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceItem} from "../invoice-item.interface";
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from "@angular/material";
import {InvoiceItemEditorDialog} from "../dialog/invoice-item-editor.dialog";
import {InvoiceTask} from "../invoice-task.interface";
import {InvoiceActions} from "../invoice.action";
import {InvoiceTaskState} from "../invoice-task.reducer";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {BillingModuleState} from "../../index";


@Component({
  selector: 'pams-invoice-draft-task',
  templateUrl: './invoice-draft-task.panel.html',
})

export class InvoiceDraftTaskPanel implements OnInit {

  private invoiceTask$: Observable<InvoiceTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialog,
              private snackBar: MdSnackBar,
              private actions: InvoiceActions,
              private store: Store<BillingModuleState>) {
    this.invoiceTask$ = this.store.select(state => state.invoiceTask);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {taskId: string}) => {
      let taskId: string = params.taskId;
      this.actions.findInvoiceTaskByTaskId(taskId);
    });

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

  draft(invoiceTask:InvoiceTask) {
    this.actions.completeInvoiceTask(invoiceTask);
      // .subscribe(res => {
      // let snackBarRef = this._snackBar.open("Invoice  completed", "OK");
      // snackBarRef.afterDismissed().subscribe(() => {
      //   this.goBack();
      // });
    // });
  }

  goBack(): void {
    this.router.navigate(['/billing/invoices']);
  }
}

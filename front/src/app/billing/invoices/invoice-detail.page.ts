import { Component, OnInit, ViewContainerRef } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceActions} from "./invoice.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";
import { InvoiceItem } from "./invoice-item.interface";
import { MdDialogConfig, MdDialogRef, MdDialog } from "@angular/material";
import { InvoiceDebitNoteCreatorDialog } from "./dialog/invoice-debit-note-creator.dialog";
import { InvoiceCreditNoteCreatorDialog } from "./dialog/invoice-credit-note-creator.dialog";


@Component({
  selector: 'pams-invoice-detail',
  templateUrl: './invoice-detail.page.html',
})
export class InvoiceDetailPage implements OnInit {

  private INVOICE = "billingModuleState.invoice".split(".");
  private INVOICE_ITEMS = "billingModuleState.invoiceItems".split(".");
  private invoice$: Observable<InvoiceTask>;
  private invoiceItems$: Observable<InvoiceItem[]>;
  private creatorDialogRef: MdDialogRef<InvoiceDebitNoteCreatorDialog>;
  private creatorDialogRef1: MdDialogRef<InvoiceCreditNoteCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: InvoiceActions) {
    this.invoice$ = this.store.select(...this.INVOICE)
    this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/invoices']);
  }

  showDialog(): void {
        console.log("showDialog");
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '65%';
        config.position = { top: '0px' };
        this.creatorDialogRef = this.dialog.open(InvoiceDebitNoteCreatorDialog, config);
        this.creatorDialogRef.afterClosed().subscribe(res => {
            console.log("close dialog");
            // load something here
        });
    }
    
      showDialog1(): void {
        console.log("showDialog");
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '65%';
        config.position = { top: '0px' };
        this.creatorDialogRef1 = this.dialog.open(InvoiceCreditNoteCreatorDialog, config);
        this.creatorDialogRef.afterClosed().subscribe(res => {
            console.log("close dialog");
            // load something here
        });
    }
}



import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
//import {InvoiceTask} from "./invoice-task.interface";
//import {InvoiceActions} from "./invoice.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";
//import {InvoiceItem} from "./invoice-item.interface";
import { MdDialogRef, MdDialog } from "@angular/material";
import { CreditNoteTask } from "./credit-note-task.interface";
import { CreditNoteItem } from "./credit-note-item.interface";
import { CreditNoteActions } from "./credit-note.action";


@Component({
  selector: 'pams-credit-note-detail',
  templateUrl: './credit-note-detail.page.html',
})
export class CreditNoteDetailPage implements OnInit {

  private CREDIT_NOTES = "billingModuleState.creditNote".split(".");
  private CREDIT_NOTE_ITEMS = "billingModuleState.creditNoteItems".split(".");
  private creditNotes$: Observable<CreditNoteTask[]>;
  private creditNoteItems$: Observable<CreditNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: CreditNoteActions) {
   
    this.creditNotes$ = this.store.select(...this.CREDIT_NOTES);
    this.creditNoteItems$ = this.store.select(...this.CREDIT_NOTE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findCreditNoteByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/credit-notes']);
  }
}



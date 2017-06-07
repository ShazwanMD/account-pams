import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
//import {InvoiceTask} from "./invoice-task.interface";
//import {InvoiceActions} from "./invoice.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";
//import {InvoiceItem} from "./invoice-item.interface";
import { MdDialogRef, MdDialog } from "@angular/material";
import { DebitNote } from "../debit-notes/debit-note.interface";
import { DebitNoteItem } from "./debit-note-item.interface";
import { DebitNoteActions } from "./debit-note.action";


@Component({
  selector: 'pams-debit-note-detail',
  templateUrl: './debit-note-detail.page.html',
})
export class DebitNoteDetailPage implements OnInit {

  private DEBIT_NOTES = "billingModuleState.debitNote".split(".");
  private DEBIT_NOTE_ITEMS = "billingModuleState.debitNoteItems".split(".");
  private debitNotes$: Observable<DebitNote[]>;
  private debitNoteItems$: Observable<DebitNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: DebitNoteActions) {
   
    this.debitNotes$ = this.store.select(...this.DEBIT_NOTES);
    this.debitNoteItems$ = this.store.select(...this.DEBIT_NOTE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findDebitNoteByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/debit-notes']);
  }
}



import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {Sponsor} from "../../../identity/sponsor.interface";
import {DebitNote} from "../debit-note.interface";
import {BillingModuleState} from "../../index";
import { DebitNoteActions } from "../debit-note.action";
import { Invoice } from "../../invoices/invoice.interface";
//import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";


@Component({
  selector: 'pams-debit-note-creator',
  templateUrl: './debit-note-creator.dialog.html',
})

export class DebitNoteCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: DebitNoteActions,
              private dialog: MdDialogRef<DebitNoteCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<DebitNote>{
      id: null,
      code: '',
      description: '',
      referenceNo: '',
      sourceNo:'',
      totalAmount:0,
      invoice: <Invoice>{},
    });
  }

  save(debitNote: DebitNote, isValid: boolean): void{
    console.log("saving fee");
    this.store.dispatch(this.actions.startDebitNoteTask(debitNote));
    this.dialog.close();
  }
}
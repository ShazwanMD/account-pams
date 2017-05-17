import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {Sponsor} from "../../../identity/sponsor.interface";
import { BillingModuleState } from "../../index";
import { CreditNote } from "../credit-note.interface";
import { CreditNoteActions } from "../credit-note.action";
import { Invoice } from "../../invoices/invoice.interface";
//import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";


@Component({
  selector: 'pams-credit-note-creator',
  templateUrl: './credit-note-creator.dialog.html',
})

export class CreditNoteCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: CreditNoteActions,
              private dialog: MdDialogRef<CreditNoteCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<CreditNote>{
      id: null,
      code: '',
      description: '',
      referenceNo: '',
      sourceNo:'',
      totalAmount:0,
      invoice: <Invoice>{},
      // todo: studyMode
      // todo: localityCode
    });
  }

  save(creditNote: CreditNote, isValid: boolean): void {
    console.log("saving fee");
    this.store.dispatch(this.actions.saveCreditNote(creditNote));
    this.dialog.close();
  }
}

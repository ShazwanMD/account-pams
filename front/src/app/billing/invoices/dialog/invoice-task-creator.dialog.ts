import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {AcademicSession} from "../../../account/academic-sessions/academic-session.interface";
import {Account} from "../../../account/accounts/account.interface";
import {InvoiceActions} from "../invoice.action";
import {BillingModuleState} from "../../index";
import {Invoice} from "../invoice.interface";
import {AccountService} from "../../../../services/account.service";


@Component({
  selector: 'pams-invoice-task-creator',
  templateUrl: './invoice-task-creator.dialog.html',
})

export class InvoiceTaskCreatorDialog implements OnInit {

  private createForm: FormGroup;
  constructor(private formBuilder: FormBuilder,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions,
              private dialog: MdDialogRef<InvoiceTaskCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<Invoice>{
      id: null,
      referenceNo: '',
      sourceNo:'',
      invoiceNo:'',
      description: '',
      totalPretaxAmount:0,
      totalTaxAmount:0,
      totalAmount:0,
      balanceAmount:0,
      paid:false,
      account:<Account>{},
      academicSession:<AcademicSession>{},
    });
  }

  save(invoice: Invoice, isValid: boolean) {
    console.log("invoice: " + invoice.description);
    console.log("account: " + invoice.account.code);
    this.store.dispatch(this.actions.startInvoiceTask(invoice));
    this.dialog.close();
  }
}

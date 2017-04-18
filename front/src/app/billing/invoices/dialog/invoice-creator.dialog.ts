import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {AcademicSession} from "../../../account/accounts/academic-session.interface";
import {Account} from "../../../account/accounts/account.interface";
import {InvoiceActions} from "../invoice.action";
import {BillingModuleState} from "../../index";
import {Invoice} from "../invoice.interface";


@Component({
  selector: 'pams-invoice-creator',
  templateUrl: './invoice-creator.dialog.html',
})

export class InvoiceCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions,
              private dialog: MdDialogRef<InvoiceCreatorDialog>) {
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

  save(settlement: Invoice, isValid: boolean) {
    this.store.dispatch(this.actions.startInvoiceTask(settlement));
    this.dialog.close();

    // .subscribe(res => {
    //   let snackBarRef = this._snackBar.open("Invoice started", "OK");
    //   snackBarRef.afterDismissed().subscribe(() => {
    //     this.goBack();
    //   });
    // });
  }
}

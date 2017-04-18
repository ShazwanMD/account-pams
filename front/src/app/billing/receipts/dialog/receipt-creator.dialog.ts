import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {AcademicSession} from "../../../account/accounts/academic-session.interface";
import {Account} from "../../../account/accounts/account.interface";
import {ReceiptActions} from "../receipt.action";
import {BillingModuleState} from "../../index";
import {Receipt} from "../receipt.interface";


@Component({
  selector: 'pams-receipt-creator',
  templateUrl: './receipt-creator.dialog.html',
})

export class ReceiptCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<ReceiptCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<Receipt>{
      id: null,
      referenceNo: '',
      sourceNo:'',
      receiptNo:'',
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

  save(settlement: Receipt, isValid: boolean) {
    this.store.dispatch(this.actions.startReceiptTask(settlement));
    this.dialog.close();

    // .subscribe(res => {
    //   let snackBarRef = this._snackBar.open("Receipt started", "OK");
    //   snackBarRef.afterDismissed().subscribe(() => {
    //     this.goBack();
    //   });
    // });
  }
}

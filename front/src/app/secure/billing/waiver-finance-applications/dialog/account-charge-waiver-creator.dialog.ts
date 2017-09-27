import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";

@Component({
  selector: 'pams-account-charge-waiver-creator',
  templateUrl: './account-charge-waiver-creator.dialog.html',
})

export class AccountChargeWaiverDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _waiverFinanceApplication: WaiverFinanceApplication;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: WaiverFinanceApplicationActions,
              private dialog: MdDialogRef<AccountChargeWaiverDialog>) {
  }

  set waiverFinanceApplication(value: WaiverFinanceApplication) {
      this._waiverFinanceApplication = value;
      this.edit = true;
    }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      //referenceNo: '',
      accountCharge: <AccountCharge>{},
      waiverFinanceApplication: <WaiverFinanceApplication>{},
    });
    if (this.edit) this.createForm.patchValue(this._waiverFinanceApplication);
  }

  save(receipt: Receipt, isValid: boolean) {
    
      console.log("receiptNo" + this._waiverFinanceApplication.referenceNo);
      console.log("Account Charge ref No" + this.createForm.get('accountCharge').value.referenceNo);
      
    this.store.dispatch(this.actions.addWaiverAccountCharge(this._waiverFinanceApplication, this.createForm.get('accountCharge').value));
    this.dialog.close();
  }
}

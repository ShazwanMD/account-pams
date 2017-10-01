import { KnockoffActions } from './../knockoff.action';
import { Knockoff } from './../../../../shared/model/billing/knockoff.interface';
import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";

@Component({
  selector: 'pams-knockoff-account-charge-creator',
  templateUrl: './knockoff-account-charge-creator.dialog.html',
})


export class AccountChargeKnockoffDialog implements OnInit {

  private createForm: FormGroup;
  private edit: boolean = false;
  private _knockoff: Knockoff;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<AccountChargeKnockoffDialog>) {
  }  

  set knockoff(value: Knockoff) {
    this._knockoff = value;
    this.edit = true;
  }

  ngOnInit(): void {
      this.createForm = this.formBuilder.group({
      accountCharge: <AccountCharge>{},
      knockoff: <Knockoff>{},
    });
    if (this.edit) this.createForm.patchValue(this._knockoff);
  }

  save(knockoff: Knockoff, isValid: boolean) {
      console.log("Knockoff ref No" + this._knockoff.referenceNo);
      console.log("Account Charge ref No" + this.createForm.get('accountCharge').value.referenceNo);
      
    this.store.dispatch(this.actions.addKnockoffAccountCharge(this._knockoff, this.createForm.get('accountCharge').value));
    this.dialog.close();
  }
}


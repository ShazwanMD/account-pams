import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {AccountCharge} from "../account-charge.interface";
import {Actor} from "../../../identity/actor.interface";
import {AccountActions} from "../account.action";
import {AccountModuleState} from "../../index";
import {IdentityService} from "../../../../services/identity.service";

@Component({
  selector: 'pams-admission-charge',
  templateUrl: './admission-charge.dialog.html',
})
export class AdmissionChargeDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private store: Store<AccountModuleState>,
              private actions: AccountActions,
              private dialog: MdDialogRef<AdmissionChargeDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<AccountCharge>{
        referenceNo: '',
        sourceNo: '',
        amount: 0,
    });
  }

  save(accountCharge: AccountCharge, isValid: boolean) {
    console.log("account: " + accountCharge.amount);
    this.dialog.close();

  }
}
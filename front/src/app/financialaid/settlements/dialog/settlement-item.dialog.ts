import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {IdentityService} from "../../../../services/identity.service";
import {CommonService} from "../../../../services/common.service";
import {ChargeCode} from "../../../account/charge-codes/charge-code.interface";
import {PromoCodeCreatorDialog} from "../../../marketing/promo-codes/dialog/promo-code-creator.dialog";
import {MdDialogRef} from "@angular/material";
import {Store} from "@ngrx/store";
import {SettlementItem} from "../settlement-item.interface";
import {Settlement} from "../settlement.interface";
import {SettlementActions} from "../settlement.action";
import {FinancialaidModuleState} from "../../index";
import {Invoice} from "../../../billing/invoices/invoice.interface";
import {Account} from "../../../account/accounts/account.interface";

@Component({
  selector: 'pams-settlement-item',
  templateUrl: './settlement-item.dialog.html',
})

export class SettlementItemDialog implements OnInit {

  private editForm: FormGroup;
  //private _settlementItem: SettlementItem;
  private referenceNo: String;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions:SettlementActions,
              private dialog: MdDialogRef<SettlementItemDialog>) {
  }
/*
  set settlementItem(value: SettlementItem) {
    this._settlementItem = value;
  }
*/
  set setReferenceNo(referenceNo: String) {
    this.referenceNo = referenceNo;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<SettlementItem>{
      balanceAmount: 0,
      account: <Account>{},
      invoice: <Invoice>{},
    });
    // this.editForm.patchValue(this.settlementItem);
  }

  save(settlementItem: SettlementItem, isValid: boolean) {
    this.store.dispatch(this.actions.addSettlementItem(this.referenceNo, settlementItem));
    this.close();
  }

  close(): void {
    this.dialog.close();
  }
}

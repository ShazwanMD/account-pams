import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {AccountActions} from "../account.action";
import {AccountModuleState} from "../../index";
import {CompoundCharge} from "../compound-charge.interface";
import {Account} from "../account.interface";
import { Router, ActivatedRoute } from "@angular/router";

@Component({
  selector: 'pams-compound-charge',
  templateUrl: './compound-charge.dialog.html',
})
export class CompoundChargeDialog implements OnInit {

  private editorForm: FormGroup;
  private edit: boolean = false;
  private _account  : Account;
  private _compoundCharge  : CompoundCharge;


  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<CompoundChargeDialog>,
              private store: Store<AccountModuleState>,
              private actions: AccountActions) {
  }

  set account(value: Account) {
    this._account = value;
    
  }

  set compoundCharge(value: CompoundCharge) {
    this.compoundCharge = value;
    this.edit = true;
  }
  
  ngOnInit(): void {
    this.editorForm = this.formBuilder.group(<CompoundCharge>{
      id:null,
      referenceNo: '',
      sourceNo: '',
      description:'',
      amount: 0,
      compoundCode : '',
      compoundDescription : '',
    });
  }

  save(charge: CompoundCharge, isValid: boolean) {
    console.log("account: " + charge.amount);
    this.store.dispatch(this.actions.addCompoundCharge(this._account, charge));
    this.dialog.close();
  }
}

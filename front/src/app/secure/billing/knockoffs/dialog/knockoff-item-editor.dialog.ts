import { AmountValidation } from './../../../../shared/component/amount-validation';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {KnockoffItem} from '../../../../shared/model/billing/knockoff-item.interface';
import {ChargeCode} from '../../../../shared/model/account/charge-code.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { KnockoffActions } from "../knockoff.action";

@Component({
  selector: 'pams-knockoff-item-editor',
  templateUrl: './knockoff-item-editor.dialog.html',
})

export class KnockoffItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private edit: boolean = false;
  private _knockoff: Knockoff;
  private _knockoffItem: KnockoffItem;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private dialog: MdDialogRef<KnockoffItemEditorDialog>) {
  }

  set knockoff(value: Knockoff) {
    this._knockoff = value;
  }

  set knockoffItem(value: KnockoffItem) {
    this._knockoffItem = value;
    this.edit = true;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: [undefined],
      description: [''],
      totalAmount: [0],
      appliedAmount: [0,Validators.required],
      dueAmount: [0,Validators.required],
      chargeCode: <ChargeCode>{},
      invoice: <Invoice>{},
    },{
      validator: AmountValidation.CheckAmount // validation method
   });
    if (this.edit) this.editForm.patchValue(this._knockoffItem);
  }

  submit(item: KnockoffItem, isValid: boolean) {
    console.log("referenceno knockoff " + item.id);
    this.store.dispatch(this.actions.updateKnockoffItems(this._knockoff, item ));
    this.dialog.close();
  }
}

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
import {FeeScheduleItem} from "../fee-schedule-item.interface";
import {FeeSchedule} from "../fee-schedule.interface";
import {FeeScheduleActions} from "../fee-schedule.action";
import {AccountModuleState} from "../../index";


@Component({
  selector: 'pams-fee-schedule-item-editor',
  templateUrl: './fee-schedule-item-editor.dialog.html',
})

export class FeeScheduleItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _feeScheduleItem: FeeScheduleItem;
  private _feeSchedule: FeeSchedule;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<AccountModuleState>,
              private actions:FeeScheduleActions,
              private dialog: MdDialogRef<FeeScheduleItemEditorDialog>) {
  }

  set feeScheduleItem(value: FeeScheduleItem) {
    this._feeScheduleItem = value;
  }

  set feeSchedule(value: FeeSchedule) {
    this._feeSchedule = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<FeeScheduleItem>{
      id: null,
      description: '',
      amount: 0,
      balanceAmount: 0,
      chargeCode: <ChargeCode>{},
    });
    // this.editForm.patchValue(this.feeScheduleItem);
  }

  save(item: FeeScheduleItem, isValid: boolean) {
    this.store.dispatch(this.actions.addFeeScheduleItem(this._feeSchedule, item))
    this.close();
  }

  close(): void {
    this.dialog.close();
  }
}

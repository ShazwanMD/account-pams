import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {FeeScheduleActions} from '../fee-schedule.action';
import {AccountModuleState} from '../../index';
import {FeeScheduleItem} from '../../../shared/model/account/fee-schedule-item.interface';
import {FeeSchedule} from '../../../shared/model/account/fee-schedule.interface';
import {ChargeCode} from '../../../shared/model/account/charge-code.interface';

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
              private actions: FeeScheduleActions,
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
      id: undefined,
      description: '',
      ordinal: 0,
      amount: 0,
      balanceAmount: 0,
      chargeCode: <ChargeCode>{},
    });
    // this.editForm.patchValue(this.feeScheduleItem);
  }

  save(item: FeeScheduleItem, isValid: boolean) {
    item.description = item.chargeCode.description;
    this.store.dispatch(this.actions.addFeeScheduleItem(this._feeSchedule, item));
    this.close();
  }

  close(): void {
    this.dialog.close();
  }
}

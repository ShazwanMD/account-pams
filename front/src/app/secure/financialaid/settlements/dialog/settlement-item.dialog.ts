import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {SettlementActions} from '../settlement.action';
import {FinancialaidModuleState} from '../../index';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';
import {SettlementItem} from '../../../../shared/model/financialaid/settlement-item.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-settlement-item',
  templateUrl: './settlement-item.dialog.html',
})

export class SettlementItemDialog implements OnInit {

  private editForm: FormGroup;
  private _settlement: Settlement;
  private _settlementItem: SettlementItem;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementItemDialog>) {
  }

  set settlement(settlement: Settlement) {
    this._settlement = settlement;
  }

  set settlementItem(settlementItem: SettlementItem) {
    this._settlementItem = settlementItem;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: [undefined],
      balanceAmount: ['',Validators.required],
      feeAmount: ['',Validators.required],
      loanAmount:['',Validators.required],
      nettAmount: ['',Validators.required],
      account: [<Account>{}],
      invoice: [<Invoice>{}],
    });

    if (this._settlementItem)
      this.editForm.patchValue(this._settlementItem);

  }


  save(settlementItem: SettlementItem, isValid: boolean) {

    if (!settlementItem.id) this.store.dispatch(this.actions.addSettlementItem(this._settlement, settlementItem));
    else  this.store.dispatch(this.actions.updateSettlementItem(this._settlement, settlementItem));
    this.dialog.close();
  }
}

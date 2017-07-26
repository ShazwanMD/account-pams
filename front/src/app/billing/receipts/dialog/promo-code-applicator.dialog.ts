import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptItem} from '../../../shared/model/billing/receipt-item.interface';
import {Receipt} from '../../../shared/model/billing/receipt.interface';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';

@Component({
  selector: 'pams-promo-code-applicator',
  templateUrl: './promo-code-applicator.dialog.html',
})

export class PromoCodeApplicatorDialog implements OnInit {

  private applyForm: FormGroup;
  private _receipt: Receipt;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<PromoCodeApplicatorDialog>) {
  }

  set receipt(value: Receipt) {
    this._receipt = value;
  }

  ngOnInit(): void {
    this.applyForm = this.formBuilder.group({
      promoCode: '',
    });
  }

  // todo(sahir): action, service, back end
  submit(item: ReceiptItem, isValid: boolean) {
    // this.store.dispatch(this.actions.applyPromoCode(this._receipt, promoCode);
    this.dialog.close();
  }
}

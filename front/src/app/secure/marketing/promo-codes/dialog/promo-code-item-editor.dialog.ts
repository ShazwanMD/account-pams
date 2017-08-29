import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialogRef} from '@angular/material';
import {MarketingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Account} from '../../../../shared/model/account/account.interface';
import {PromoCodeActions} from '../promo-code.action';
import {PromoCodeItem} from '../../../../shared/model/marketing/promo-code-item.interface';
import {PromoCode} from '../../../../shared/model/marketing/promo-code.interface';

@Component({
  selector: 'pams-promo-code-item-editor',
  templateUrl: './promo-code-item-editor.dialog.html',
})

export class PromoCodeItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private _promoCodeItem: PromoCodeItem;
  private _promoCode: PromoCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<MarketingModuleState>,
              private actions: PromoCodeActions,
              private dialog: MdDialogRef<PromoCodeItemEditorDialog>) {
  }

  set promoCodeItem(promoCodeItem: PromoCodeItem) {
    this._promoCodeItem = promoCodeItem;
  }

  set promoCode(promoCode: PromoCode) {
    this._promoCode = promoCode;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group({
      id: [null],
      code: ['',Validators.required],
      applied: [false],
      sourceNo:[0],
      account: [<Account>{},Validators.required],
    });

    if (this._promoCodeItem) {
      this.editForm.patchValue(this._promoCodeItem);
      /*
       * below line expected to assign default selected option to account select component but it's not :~)
       * */
      //this.editForm.controls['account'].patchValue(this._promoCodeItem.account);
    }
  }

  submit(promoCodeItem: PromoCodeItem, isValid: boolean) {
    if (!promoCodeItem.id) this.store.dispatch(this.actions.addPromoCodeItem(this._promoCode, promoCodeItem));
    else  this.store.dispatch(this.actions.updatePromoCodeItem(this._promoCode, promoCodeItem));
    this.dialog.close();
  }
}

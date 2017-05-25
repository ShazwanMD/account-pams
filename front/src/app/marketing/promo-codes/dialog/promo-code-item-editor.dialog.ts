import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {PromoCodeItem} from "../promo-code-item.interface";
import {Account} from "../../../account/accounts/account.interface";
import {MdDialogRef} from "@angular/material";
import {MarketingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {PromoCodeActions} from "../promo-code.action";
import {PromoCode} from "../promo-code.interface";


@Component({
  selector: 'pams-promo-code-item-editor',
  templateUrl: './promo-code-item-editor.dialog.html',
})

export class PromoCodeItemEditorDialog implements OnInit {

  private editForm: FormGroup;
  private promoCodeItem: PromoCodeItem;
  private promoCode: PromoCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<MarketingModuleState>,
              private actions:PromoCodeActions,
              private dialog: MdDialogRef<PromoCodeItemEditorDialog>) {
  }

  set setPromoCodeItem(promoCodeItem: PromoCodeItem) {
    this.promoCodeItem = promoCodeItem;
  }

  set setPromoCode(promoCode: PromoCode) {
    this.promoCode = promoCode;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<PromoCodeItem>{
      id: null,
      code: '',
      //applied: 0,
      sourceNo: 0,
      account: <Account>{},
    });
    this.editForm.patchValue(this.promoCodeItem);
    this.editForm.controls['account'].patchValue(this.promoCodeItem.account);
  }

  submit(promoCodeItem: PromoCodeItem, isValid: boolean) {
    if (!promoCodeItem.id) this.store.dispatch(this.actions.addPromoCodeItem(this.promoCode, promoCodeItem));
    else  this.store.dispatch(this.actions.updatePromoCodeItem(this.promoCode, promoCodeItem));
    this.dialog.close();
  }
}

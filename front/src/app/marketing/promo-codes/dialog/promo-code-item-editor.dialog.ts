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
  private edit: boolean = false;
  private _promoCodeItem: PromoCodeItem;
  private _promoCode: PromoCode;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<MarketingModuleState>,
              private actions:PromoCodeActions,
              private dialog: MdDialogRef<PromoCodeItemEditorDialog>) {
  }

  set promoCodeItem(value: PromoCodeItem) {
    this._promoCodeItem = value;
    this.edit = true;
  }

  set promoCode(value: PromoCode) {
    this._promoCode = value;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<PromoCodeItem>{
      id: null,
      code: '',
      //applied: 0,
      sourceNo: 0,
      account: <Account>{},
    });
    if (this.edit) this.editForm.patchValue(this._promoCodeItem);
    console.log(this._promoCode)
  }

  submit(item: PromoCodeItem, isValid: boolean) {
    if (!item.id) this.store.dispatch(this.actions.addPromoCodeItem(this._promoCode, item));
    else  this.store.dispatch(this.actions.updatePromoCodeItem(this._promoCode, item));
    this.dialog.close();
  }
}

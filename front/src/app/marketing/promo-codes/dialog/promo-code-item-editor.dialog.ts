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
  private _promoCodeItem: PromoCodeItem;
  private _promoCode: PromoCode;
  private edit: boolean = false;

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
    this.edit = true;
  }

  set promoCode(promoCode: PromoCode) {
    this._promoCode = promoCode;
  }

  ngOnInit(): void {
    this.editForm = this.formBuilder.group(<PromoCodeItem>{
      id: null,
      code: '',
      applied: false,
      sourceNo: 0,
      account: <Account>{},
    });
    
    if(this._promoCodeItem){
        this.editForm.patchValue(this._promoCodeItem);
        /*
         * below line expected to assign default selected option to account select component but it's not :~)
         * */
        //this.editForm.controls['account'].patchValue(this._promoCodeItem.account);
    }
  }

  submit(promoCodeItem: PromoCodeItem, isValid: boolean) {
    if (!this.edit) this.store.dispatch(this.actions.addPromoCodeItem(this._promoCode, promoCodeItem));
    else  this.store.dispatch(this.actions.updatePromoCodeItem(this._promoCode, promoCodeItem));
    this.dialog.close();
  }
}

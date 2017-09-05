import {Component, OnInit, ViewContainerRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {MarketingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {PromoCodeActions} from '../promo-code.action';
import {MdDialogRef} from '@angular/material';
import {PromoCode} from '../../../../shared/model/marketing/promo-code.interface';

@Component({
  selector: 'pams-promo-code-creator',
  templateUrl: './promo-code-creator.dialog.html',
})

export class PromoCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<MarketingModuleState>,
              private actions: PromoCodeActions,
              private dialog: MdDialogRef<PromoCodeCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group({
      id: null,
      referenceNo: [''],
      description: ['',Validators.required],
      value: ['0',Validators.required],
      quantity: ['0',Validators.required],
    });
  }

  save(promoCode: PromoCode, isValid: boolean) {
    this.store.dispatch(this.actions.initPromoCode(promoCode));
    this.dialog.close();
    window.location.reload();
  }
}

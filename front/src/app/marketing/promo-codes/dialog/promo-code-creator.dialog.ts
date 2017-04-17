import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {PromoCode} from "../promo-code.interface";
import {MarketingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {PromoCodeActions} from "../promo-code.action";
import {MdDialogRef} from "@angular/material";


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
    this.createForm = this.formBuilder.group(<PromoCode>{
      id: null,
      referenceNo: '',
      description: '',
      value: 0,
      quantity: 0,
    });
  }

  save(promoCode: PromoCode, isValid: boolean) {
    this.store.dispatch(this.actions.initPromoCode(promoCode));
    this.dialog.close();

    // .subscribe(res => {
    //   let snackBarRef = this._snackBar.open("Invoice started", "OK");
    //   snackBarRef.afterDismissed().subscribe(() => {
    //     this.goBack();
    //   });
    // });
  }
}

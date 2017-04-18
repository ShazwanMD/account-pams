import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {FinancialaidModuleState} from "../../index";
import {SettlementActions} from "../settlement.action";
import {Settlement} from "../settlement.interface";
import {AcademicSession} from "../../../account/accounts/academic-session.interface";
import {Sponsor} from "../../../identity/sponsor.interface";
import {PromoCodeCreatorDialog} from "../../../marketing/promo-codes/dialog/promo-code-creator.dialog";


@Component({
  selector: 'pams-settlement-creator',
  templateUrl: './settlement-creator.dialog.html',
})

export class SettlementCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementCreatorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<Settlement>{
      id: null,
      referenceNo: '',
      description: '',
      sponsor:<Sponsor>{},
      academicSession:<AcademicSession>{},
    });
  }

  save(settlement: Settlement, isValid: boolean) {
    this.store.dispatch(this.actions.initSettlement(settlement));
    this.dialog.close();

    // .subscribe(res => {
    //   let snackBarRef = this._snackBar.open("Invoice started", "OK");
    //   snackBarRef.afterDismissed().subscribe(() => {
    //     this.goBack();
    //   });
    // });
  }
}

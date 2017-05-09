import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {FinancialaidModuleState} from "../../index";
import {SettlementActions} from "../settlement.action";
import {SettlementCreator} from "../settlement-creator.interface";
import {AcademicSession} from "../../../account/academic-sessions/academic-session.interface";
import {Sponsor} from "../../../identity/sponsor.interface";
//import {PromoCodeCreatorDialog} from "../../../marketing/promo-codes/dialog/promo-code-creator.dialog";


@Component({
  selector: 'pams-settlement-creator-by-sponsor',
  templateUrl: './settlement-creator-by-sponsor.dialog.html',
})

export class SettlementCreatorBySponsorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<FinancialaidModuleState>,
              private actions: SettlementActions,
              private dialog: MdDialogRef<SettlementCreatorBySponsorDialog>) {
  }

  ngOnInit(): void {
    this.createForm = this.formBuilder.group(<SettlementCreator>{
      sourceNo:'',
      description: '',
      sponsor:<Sponsor>{},
      academicSession:<AcademicSession>{},
    });
  }

  save(settlementCreator: SettlementCreator, isValid: boolean) {
    this.store.dispatch(this.actions.initSettlementBySponsor(settlementCreator,settlementCreator.sponsor));
    this.dialog.close();

    // .subscribe(res => {
    //   let snackBarRef = this._snackBar.open("Invoice started", "OK");
    //   snackBarRef.afterDismissed().subscribe(() => {
    //     this.goBack();
    //   });
    // });
  }
}

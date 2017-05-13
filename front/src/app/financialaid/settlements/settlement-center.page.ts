import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {SettlementActions} from "./settlement.action";
import {Settlement} from "./settlement.interface";
import {SettlementListState} from "./settlement-list.reducer";
import {FinancialaidModuleState} from "../index";
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {SettlementCreatorByCohortDialog} from "./dialog/settlement-creator-by-cohort.dialog";
import {SettlementCreatorByFacultyDialog} from "./dialog/settlement-creator-by-faculty.dialog";
import {SettlementCreatorBySponsorDialog} from "./dialog/settlement-creator-by-sponsor.dialog";

@Component({
  selector: 'pams-settlement-center',
  templateUrl: './settlement-center.page.html',
})

export class SettlementCenterPage implements OnInit {

  private SETTLEMENTS = "financialaidModuleState.settlements".split(".");
  private settlements$: Observable<Settlement[]>;
  private creatorByCohortDialogRef: MdDialogRef<SettlementCreatorByCohortDialog>;
  private creatorByFacultyDialogRef: MdDialogRef<SettlementCreatorByFacultyDialog>;
  private creatorBySponsorDialogRef: MdDialogRef<SettlementCreatorBySponsorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.settlements$ = this.store.select(...this.SETTLEMENTS);
  }

  goBack(route: string): void {
    this.router.navigate(['/settlements']);
  }

  view(settlement: Settlement) {
    console.log("settlement: " + settlement.referenceNo);
    this.router.navigate(['/view', settlement.referenceNo]);
  }

  showByCohortDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.creatorByCohortDialogRef = this.dialog.open(SettlementCreatorByCohortDialog, config);
    this.creatorByCohortDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  showByFacultyDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.creatorByFacultyDialogRef = this.dialog.open(SettlementCreatorByFacultyDialog, config);
    this.creatorByFacultyDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  showBySponsorDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.creatorBySponsorDialogRef = this.dialog.open(SettlementCreatorBySponsorDialog, config);
    this.creatorBySponsorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  filter(): void {
  }

  ngOnInit(): void {
    console.log("find settlements");
    this.store.dispatch(this.actions.findSettlements());
  }
}


import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {SettlementActions} from "./settlement.action";
import {Settlement} from "./settlement.interface";
import {SettlementListState} from "./settlement-list.reducer";
import {FinancialaidModuleState} from "../index";
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {SettlementCreatorDialog} from "./dialog/settlement-creator.dialog";

@Component({
  selector: 'pams-settlement-center',
  templateUrl: './settlement-center.page.html',
})

export class SettlementCenterPage implements OnInit {

  private SETTLEMENTS = "financialaidModuleState.settlements".split(".");
  private settlements$: Observable<Settlement[]>;
  private creatorDialogRef: MdDialogRef<SettlementCreatorDialog>;

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

  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SettlementCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  ngOnInit(): void {
    console.log("find settlements");
    this.store.dispatch(this.actions.findSettlements());
  }
}


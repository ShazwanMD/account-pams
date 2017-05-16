import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {Settlement} from "../settlement.interface";
import {FinancialaidModuleState} from "../../index";
import {SettlementActions} from "../settlement.action";
import {SettlementItemDialog} from "../dialog/settlement-item.dialog";
import {SettlementItem} from "../settlement-item.interface";


@Component({
  selector: 'pams-settlement-item-list',
  templateUrl: './settlement-item-list.component.html',
})

export class SettlementItemListComponent {

  private creatorDialogRef: MdDialogRef<SettlementItemDialog>;

  @Input() settlement: Settlement;
  @Input() settlementItems: SettlementItem[];

  private columns: any[] = [
    {name: 'account', label: 'Account'},
    {name: 'invoice', label: 'Invoice'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  showDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '40%';
    config.position = {top: '65px'};
    this.creatorDialogRef = this.dialog.open(SettlementItemDialog, config);
    this.creatorDialogRef.componentInstance.setReferenceNo = this.settlement.referenceNo;

    // close
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }
}

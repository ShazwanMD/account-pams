import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {Settlement} from "../settlement.interface";
import {SettlementActions} from "../settlement.action";
import {SettlementItemDialog} from "../dialog/settlement-item.dialog";
import {SettlementItem} from "../settlement-item.interface";
import {FinancialaidModuleState} from "../../index";
import {Store} from "@ngrx/store";


@Component({
  selector: 'pams-settlement-item-list',
  templateUrl: './settlement-item-list.component.html',
})

export class SettlementItemListComponent implements OnInit{

  @Input() settlement: Settlement;
  @Input() settlementItems: SettlementItem[];

  private creatorDialogRef: MdDialogRef<SettlementItemDialog>;
  private selectedRows: SettlementItem[];
  private columns: any[] = [
    {name: 'account', label: 'Account'},
    {name: 'invoice', label: 'Invoice'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.settlementItems.filter(value => value.selected);
  }

  edit(settlementItem: SettlementItem): void {
    // this.showDialog(settlementItem);
  }

  delete(): void {
    console.log("length: " + this.selectedRows.length);
    for (var i = 0; i < this.selectedRows.length; i++) {
      // this.store.dispatch(this.actions.deleteSettlementItem(this.settlement, this.selectedRows[i]));
    }
  }

  filter(): void {
  }

  selectRow(settlementItem: SettlementItem): void {
  }

  selectAllRows(settlementItems: SettlementItem[]): void {
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

import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Settlement} from '../../../../shared/model/financialaid/settlement.interface';
import {SettlementActions} from '../settlement.action';
import {SettlementItemDialog} from '../dialog/settlement-item.dialog';
import {SettlementItem} from '../../../../shared/model/financialaid/settlement-item.interface';
import {FinancialaidModuleState} from '../../index';
import {Store} from '@ngrx/store';

@Component({
  selector: 'pams-settlement-item-list',
  templateUrl: './settlement-item-list.component.html',
})

export class SettlementItemListComponent implements OnInit {

  private editorDialogRef: MdDialogRef<SettlementItemDialog>;
  private selectedRows: SettlementItem[];
  private columns: any[] = [
    {name: 'account', label: 'Account'},
    {name: 'invoice', label: 'Invoice'},
    {name: 'loanAmount', label: 'Loan Amount'},
    {name: 'feeAmount', label: 'Fee Amount'},
    {name: 'nettAmount', label: 'Nett Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  @Input() settlement: Settlement;
  @Input() settlementItems: SettlementItem[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.settlementItems.filter((value) => value.selected);
  }

  create(): void {
    this.showDialog(null);
  }

  edit(settlementItem: SettlementItem): void {
    this.showDialog(settlementItem);
  }

  remove(settlementItem: SettlementItem): void {
    this.store.dispatch(this.actions.deleteSettlementItem(this.settlement, settlementItem));
    this.selectedRows = [];
  }

  filter(): void {
  }

  selectRow(settlementItem: SettlementItem): void {
  }

  selectAllRows(settlementItems: SettlementItem[]): void {
  }

  showDialog(settlementItem: SettlementItem): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '65px'};
    this.editorDialogRef = this.dialog.open(SettlementItemDialog, config);
    this.editorDialogRef.componentInstance.settlement = this.settlement;
    this.editorDialogRef.componentInstance.settlementItem = settlementItem;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      this.selectedRows = [];
    });
  }
}

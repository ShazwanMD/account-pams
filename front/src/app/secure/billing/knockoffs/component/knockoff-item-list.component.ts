import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceApplicatorDialog} from '../../receipts/dialog/invoice-applicator.dialog';
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../../index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { InvoiceKnockoffDialog } from "../dialog/knockoff-invoice-creator.dialog";
import { KnockoffActions } from "../knockoff.action";
import { KnockoffItemEditorDialog } from "../dialog/knockoff-item-editor.dialog";

@Component({
  selector: 'pams-knockoff-item-list',
  templateUrl: './knockoff-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class KnockoffItemListComponent implements OnInit {

    @Input() knockoffItem: KnockoffItem[];
    @Input() knockoff: Knockoff;
    @Input() invoice: Invoice;
    
    private selectedRows: KnockoffItem[];
    private editorDialogRef: MdDialogRef<KnockoffItemEditorDialog>;
    
  private columns: any[] = [
                            
                            {name: 'chargeCode.code', label: 'Charge Code'},
                            {name: 'description', label: 'Charge Code Description'},
                            {name: 'dueAmount', label: 'Amount'},
                            {name: 'appliedAmount', label: 'Received Amount'},
                            {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }
  
  edit(knockoffItem: KnockoffItem): void {
      this.showDialog(knockoffItem);
    }
  
  ngOnInit(): void {
      this.selectedRows = this.knockoffItem.filter((value) => value.selected);
    }
    
    showDialog(knockoffItem: KnockoffItem): void {
        console.log('showDialog');
        console.log("knockoff ref no" + this.knockoff.referenceNo);
        console.log("knockoff item ref no" + this.knockoffItem);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '60%';
        config.position = {top: '65px'};
        this.editorDialogRef = this.dialog.open(KnockoffItemEditorDialog, config);
        this.editorDialogRef.componentInstance.knockoff = this.knockoff;
        if (knockoffItem) this.editorDialogRef.componentInstance.knockoffItem=knockoffItem;
        this.editorDialogRef.afterClosed().subscribe((res) => {
          this.selectedRows = [];
        });
      }
    
    delete(): void {
        console.log('length: ' + this.selectedRows.length);
        for (let i: number = 0; i < this.selectedRows.length; i++) {
          this.store.dispatch(this.actions.deleteKnockoffItem(this.knockoff, this.selectedRows[i]));
        }
        this.selectedRows = [];
    }
    
    filter(): void {
    }

    selectRow(knockoffItem: KnockoffItem): void {
    }

    selectAllRows(knockoffItem: KnockoffItem[]): void {
    }
}

import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {ReceiptActions} from '../receipt.action';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';

@Component({
  selector: 'pams-receipt-item-list',
  templateUrl: './receipt-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReceiptItemListComponent implements OnInit {

  private selectedRows: ReceiptItem[];
  private editorDialogRef: MdDialogRef<ReceiptItemEditorDialog>;
  private columns: any[] = [
    {name: 'invoice.referenceNo', label: 'Invoice'},
    {name: 'accountCharge.referenceNo', label: 'Account Charge'}, 
    {name: 'debitNote.referenceNo', label: 'Debit Note'},   
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'description', label: 'Charge Code Description'},
    {name: 'dueAmount', label: 'Amount'},
    {name: 'appliedAmount', label: 'Received Amount'},
    {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  @Input() receipt: Receipt;
  @Input() receiptItems: ReceiptItem[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.receiptItems.filter((value) => value.selected);
  }

  edit(receiptItem: ReceiptItem): void {
    this.showDialog(receiptItem);
  }
  
  showDialog(receiptItem: ReceiptItem): void {
      console.log('showDialog');
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '65px'};
      this.editorDialogRef = this.dialog.open(ReceiptItemEditorDialog, config);
      this.editorDialogRef.componentInstance.receipt = this.receipt;
      if (receiptItem) this.editorDialogRef.componentInstance.receiptItem=receiptItem;
      this.editorDialogRef.afterClosed().subscribe((res) => {
        this.selectedRows = [];
      });
    }

  delete(): void {
      console.log('length: ' + this.selectedRows.length);
      for (let i: number = 0; i < this.selectedRows.length; i++) {
        this.store.dispatch(this.actions.deleteReceiptItem(this.receipt, this.selectedRows[i]));
      }
      this.selectedRows = [];
  }
  
  filter(): void {
  }

  selectRow(receiptItem: ReceiptItem): void {
  }

  selectAllRows(receiptItems: ReceiptItem[]): void {
  }

  
}

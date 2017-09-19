import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {ReceiptActions} from '../receipt.action';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';

@Component({
  selector: 'pams-receipt-item-dialog-list',
  templateUrl: './receipt-item-dialog-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReceiptItemDialogListComponent implements OnInit {

  private editorDialogRef: MdDialogRef<ReceiptItemDialogListComponent>;
  private columns: any[] = [
    {name: 'invoice.referenceNo', label: 'Invoice'},
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'description', label: 'Charge Code Description'},
    {name: 'dueAmount', label: 'Amount'},
    {name: 'appliedAmount', label: 'Received Amount'},
    {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];

  @Input() receipt: Receipt;
  @Input() invoice: Invoice;
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

  }


}

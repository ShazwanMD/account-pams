import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {InvoiceItem} from '../invoice-item.interface';
import {InvoiceItemEditorDialog} from '../dialog/invoice-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {InvoiceActions} from '../invoice.action';
import {Invoice} from '../invoice.interface';

@Component({
  selector: 'pams-invoice-item-paid-list',
  templateUrl: './invoice-item-paid-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceItemPaidListComponent implements OnInit{

  @Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];

  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: InvoiceActions,
          private store: Store<BillingModuleState>,
          private vcf: ViewContainerRef,
          private dialog: MdDialog,
          private snackbar: MdSnackBar) {
    }
    
  ngOnInit(): void {
      this.route.params.subscribe((params: { referenceNo: string }) => {
        let referenceNo: string = params.referenceNo;
        this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
      });
  }
}

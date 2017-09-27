import { KnockoffItem } from './../../../../shared/model/billing/knockoff-item.interface';
import { Knockoff } from './../../../../shared/model/billing/knockoff.interface';
import { KnockoffActions } from './../knockoff.action';
import { DebitNoteKnockoffDialog } from './../dialog/knockoff-debit-note-creator.dialog';
import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import {Account} from '../../../../shared/model/account/account.interface';


@Component({
  selector: 'pams-debit-note-knockoff-list',
  templateUrl: './debit-note-knockoff-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteKnockoffListComponent implements OnInit {

  private selectedRows: KnockoffItem[];
  private editorDialogRef: MdDialogRef<DebitNoteKnockoffDialog>;
  private columns: any[] = [
    {name: 'description', label: 'Description'},
    {name: 'dueAmount', label: 'Amount'},
    {name: 'appliedAmount', label: 'Received Amount'},
    {name: 'totalAmount', label: 'Balance Amount'},
    {name: 'action', label: ''},
  ];
  
  @Input() knockoff: Knockoff;
  @Input() knockoffItems: KnockoffItem[];
  @Input() debitNotes: DebitNote[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: KnockoffActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.knockoffItems.filter((value) => value.selected);
  }

  edit(knockoffItem: KnockoffItem): void {
    //this.showDialog(receiptItem);
  }
  filter(): void {
  }

  selectRow(knockoffItem: KnockoffItem): void {
  }

  selectAllRows(knockoffItems: KnockoffItem[]): void {
  }

  // UnpaidDebitNote(debitNotes: DebitNote): void {
  //   let config: MdDialogConfig = new MdDialogConfig();
  //   config.viewContainerRef = this.vcf;
  //   config.role = 'dialog';
  //   config.width = '50%';
  //   config.height = '60%';
  //   config.position = {top: '65px'};
  //   this.editorDialogRef = this.dialog.open(DebitNoteReceiptDialog, config);
  //   //this.editorDialogRef.componentInstance.account = this.account;
  //   this.editorDialogRef.afterClosed().subscribe((res) => {
  //     // no op
  //   });
  // }

}

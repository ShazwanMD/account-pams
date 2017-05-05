import {Component, Input, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceItem} from "../invoice-item.interface";
import {InvoiceItemEditorDialog} from "../dialog/invoice-item-editor.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {BillingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {InvoiceActions} from "../invoice.action";
import {Invoice} from "../invoice.interface";

@Component({
  selector: 'pams-invoice-item-list',
  templateUrl: './invoice-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceItemComponent {
  @Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];
  private editorDialogRef: MdDialogRef<InvoiceItemEditorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: InvoiceActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }


  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(InvoiceItemEditorDialog, config);
    this.editorDialogRef.componentInstance.invoice = this.invoice;

    // close
    this.editorDialogRef.afterClosed().subscribe(res => {
      // do something
    });
  }
}

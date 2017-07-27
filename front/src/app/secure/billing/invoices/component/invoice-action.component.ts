import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from "@angular/core";
import {Invoice} from "../../../../shared/model/billing/invoice.interface";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {BillingModuleState} from "../../index";
import {InvoiceActions} from "../invoice.action";
import {Store} from "@ngrx/store";
import {ActivatedRoute, Router} from "@angular/router";
import {CreditNoteCreatorDialog} from "../../credit-notes/dialog/credit-note-creator.dialog";
import {DebitNoteCreatorDialog} from "../../debit-notes/dialog/debit-note-creator.dialog";
import {TdDialogService} from "@covalent/core";

@Component({
  selector: 'pams-invoice-action',
  templateUrl: './invoice-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceActionComponent {

  @Input() invoice: Invoice;


  private debitCreatorDialogRef: MdDialogRef<DebitNoteCreatorDialog>;
  private creditCreatorDialogRef: MdDialogRef<CreditNoteCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private actions: InvoiceActions) {
  }

  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.debitCreatorDialogRef = this.dialog.open(DebitNoteCreatorDialog, config);
    this.debitCreatorDialogRef.componentInstance.invoice = this.invoice;
    this.debitCreatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  showDialog1(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creditCreatorDialogRef = this.dialog.open(CreditNoteCreatorDialog, config);
    this.creditCreatorDialogRef.componentInstance.invoice = this.invoice;
    this.creditCreatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  cancelDialog(): void {
    console.log(this.invoice.referenceNo);
    this._dialogService.openConfirm({
      message: 'Cancel Invoice ' + this.invoice.referenceNo + ' ?',
      disableClose: false, // defaults to false
      viewContainerRef: this.vcf,
      cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
      acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
    }).afterClosed().subscribe((accept: boolean) => {
      if (accept) {
        this.store.dispatch(this.actions.cancelInvoice(this.invoice));
      } else {
        // DO SOMETHING ELSE
      }
    });

  }

}

import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from "@angular/core";
//import {Invoice} from "../invoice.interface";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {BillingModuleState} from "../../index";
//import {InvoiceActions} from "../invoice.action";
import {Store} from "@ngrx/store";
import {ActivatedRoute, Router} from "@angular/router";
import {CreditNoteCreatorDialog} from "../../credit-notes/dialog/credit-note-creator.dialog";
import { DebitNoteCreatorDialog } from "../../debit-notes/dialog/debit-note-creator.dialog";
import { DebitNote } from "../debit-note.interface";
import { DebitNoteActions } from "../debit-note.action";
@Component({
  selector: 'pams-debit-note-action',
  templateUrl: './debit-note-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteActionComponent {
  @Input() debitNote: DebitNote;

  // private debitCreatorDialogRef: MdDialogRef<DebitNoteCreatorDialog>;
  // private creditCreatorDialogRef: MdDialogRef<CreditNoteCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: DebitNoteActions) {
  }

}

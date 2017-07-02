import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from "@angular/core";
//import {Invoice} from "../invoice.interface";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {BillingModuleState} from "../../index";
//import {InvoiceActions} from "../invoice.action";
import {Store} from "@ngrx/store";
import {ActivatedRoute, Router} from "@angular/router";
import {CreditNoteCreatorDialog} from "../../credit-notes/dialog/credit-note-creator.dialog";
import { CreditNote } from "../credit-note.interface";
import { CreditNoteActions } from "../credit-note.action";
@Component({
  selector: 'pams-credit-note-action',
  templateUrl: './credit-note-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreditNoteActionComponent {
  @Input() creditNote: CreditNote;

  // private debitCreatorDialogRef: MdDialogRef<DebitNoteCreatorDialog>;
  // private creditCreatorDialogRef: MdDialogRef<CreditNoteCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: CreditNoteActions) {
  }

}

import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import { Observable } from "rxjs";
import { BillingModuleState } from "../index";
import { CreditNote } from "./credit-note.interface";
import { CreditNoteCreatorDialog } from "./dialog/credit-note-creator.dialog";
import { CreditNoteActions } from "./credit-note.action";
import { CreditNoteTask } from "./credit-note-task.interface";
//import { DebitNoteCreatorDialog } from "./dialog/debit-note-creator.dialog";



@Component({
    selector: 'pams-credit-note-center',
    templateUrl: './credit-note-center.page.html',
})

export class CreditNoteCenterPage implements OnInit {

    private CREDIT_NOTES = "billingModuleState.creditNotes".split(".");
    private creditNotes$: Observable<CreditNoteTask[]>;
    private assignedCreditNoteTasks$: Observable<CreditNoteTask[]>;
    private pooledCreditNoteTasks$: Observable<CreditNoteTask[]>;
    private creatorDialogRef: MdDialogRef<CreditNoteCreatorDialog>;

    constructor(private router: Router,
        private route: ActivatedRoute,
        private actions: CreditNoteActions,
        private store: Store<BillingModuleState>,
        private vcf: ViewContainerRef,
        private dialog: MdDialog) {
        this.creditNotes$ = this.store.select(...this.CREDIT_NOTES);

    }

    goBack(route: string): void {
        this.router.navigate(['/credit-notes']);
    }

    view(task: CreditNoteTask) {
    console.log("CreditNote: " + task.taskId);
    this.router.navigate(['/billing/credit-notes/view-task', task.taskId]);
  }

  
 showDialog(): void {
        console.log("showDialog");
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '60%';
        config.position = { top: '0px' };
        this.creatorDialogRef = this.dialog.open(CreditNoteCreatorDialog, config);
        this.creatorDialogRef.afterClosed().subscribe(res => {
            console.log("close dialog");
            // load something here
        });
    }
 
    ngOnInit(): void {
       console.log("abc")
       this.store.dispatch(this.actions.findCreditNotes());
    }
}


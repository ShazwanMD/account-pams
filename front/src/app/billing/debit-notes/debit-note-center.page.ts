import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import { Observable } from "rxjs";
import { BillingModuleState } from "../index";
import { DebitNote } from "./debit-note.interface";
import { DebitNoteCreatorDialog } from "./dialog/debit-note-creator.dialog";
//import {ChargeCodeActions} from "./charge-code.action";
//import {ChargeCodeState} from "./charge-code.reducer";
//import {ChargeCodeListState} from "./charge-code-list.reducer";
//import {AccountModuleState} from "../index";
//import {ChargeCodeCreatorDialog} from "./dialog/charge-code-creator.dialog";

@Component({
    selector: 'pams-debit-note-center',
    templateUrl: './debit-note-center.page.html',
})

export class DebitNoteCenterPage implements OnInit {

    private DEBIT_NOTES = "billingModuleState.debitnotes".split(".");
    private debitnotes$: Observable<DebitNote[]>;
    private creatorDialogRef: MdDialogRef<DebitNoteCreatorDialog>;

    constructor(private router: Router,
        private route: ActivatedRoute,
        //private actions: ChargeCodeActions,
        private store: Store<BillingModuleState>,
        private vcf: ViewContainerRef,
        private dialog: MdDialog) {
        this.debitnotes$ = this.store.select(...this.DEBIT_NOTES);

    }

    goBack(route: string): void {
        this.router.navigate(['/debit-notes']);
    }

  
 showDialog(): void {
        console.log("showDialog");
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '60%';
        config.position = { top: '0px' };
        this.creatorDialogRef = this.dialog.open(DebitNoteCreatorDialog, config);
        this.creatorDialogRef.afterClosed().subscribe(res => {
            console.log("close dialog");
            // load something here
        });
    }
 
    ngOnInit(): void {
       // console.log("abc")
       //this.store.dispatch(this.actions.findChargeCodes());
    }
}


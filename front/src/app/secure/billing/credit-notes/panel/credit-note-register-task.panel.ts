import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {CreditNoteActions} from '../credit-note.action';
import {CreditNoteItemEditorDialog} from '../dialog/credit-note-item-editor.dialog';
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';
import {CreditNoteItem} from '../../../../shared/model/billing/credit-note-item.interface';
import { TdDialogService } from "@covalent/core";

@Component({
  selector: 'pams-credit-note-register-task',
  templateUrl: './credit-note-register-task.panel.html',
})

export class CreditNoteRegisterTaskPanel implements OnInit {

  private CREDIT_NOTE_ITEMS = 'billingModuleState.creditNoteItems'.split('.');
  @Input() creditNoteTask: CreditNoteTask;
  creditNoteItems$: Observable<CreditNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: CreditNoteActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
    this.creditNoteItems$ = this.store.select(...this.CREDIT_NOTE_ITEMS);
  }

  ngOnInit(): void {
    console.log('reference no ' + this.creditNoteTask.referenceNo);
    this.store.dispatch(this.actions.findCreditNoteItems(this.creditNoteTask));
  }

  editItem(item: CreditNoteItem) {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(CreditNoteItemEditorDialog, config);
    editorDialogRef.componentInstance.creditNoteItem = item;
  }

  verify() {
    this.store.dispatch(this.actions.completeCreditNoteTask(this.creditNoteTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/credit-notes']);
  }
  
  cancel(): void {
      console.log("Credit Note" + this.creditNoteTask.creditNote);
      this._dialogService.openConfirm({
        message: 'Cancel Credit Note ' + this.creditNoteTask.creditNote.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeCreditNoteTask(this.creditNoteTask));
          this.router.navigate(['/secure/billing/credit-notes']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}

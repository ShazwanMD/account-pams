import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {KnockoffActions} from '../knockoff.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {KnockoffTask} from '../../../../shared/model/billing/knockoff-task.interface';
import { TdDialogService } from "@covalent/core";
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";

@Component({
  selector: 'pams-knockoff-verify-task',
  templateUrl: './knockoff-verify-task.panel.html',
})

export class KnockoffVerifyTaskPanel implements OnInit {

  @Input() knockoffTask: KnockoffTask;

  private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
  private knockoffItem$: Observable<KnockoffItem[]>;
  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: KnockoffActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
      this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
  }

  ngOnInit(): void {
      this.store.dispatch(this.actions.findKnockoffItems(this.knockoffTask.knockoff));
  }

  register() {
    this.store.dispatch(this.actions.completeKnockoffTask(this.knockoffTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/knockoffs']);
  }

}

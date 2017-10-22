import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {KnockoffActions} from './knockoff.action';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {KnockoffTask} from '../../../shared/model/billing/knockoff-task.interface';
import { Knockoff } from '../../../shared/model/billing/knockoff.interface';
import { KnockoffItem } from "../../../shared/model/billing/knockoff-item.interface";

@Component({
  selector: 'pams-knockoff-detail',
  templateUrl: './knockoff-detail.page.html',
})
export class KnockoffDetailPage implements OnInit {

  private KNOCKOFF: string[] = 'billingModuleState.knockoff'.split('.');
  private knockoff$: Observable<Knockoff>;
  private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
  private knockoffItem$: Observable<KnockoffItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: KnockoffActions) {
              this.knockoff$ = this.store.select(...this.KNOCKOFF);
              this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findKnockoffByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/knockoffs']);
  }
}


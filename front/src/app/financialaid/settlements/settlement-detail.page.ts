import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {SettlementActions} from './settlement.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../index';
import {MdSnackBar} from '@angular/material';
import {Settlement} from '../../shared/model/financialaid/settlement.interface';
import {SettlementItem} from '../../shared/model/financialaid/settlement-item.interface';
@Component({
  selector: 'pams-settlement-detail',
  templateUrl: './settlement-detail.page.html',
})

export class SettlementDetailPage implements OnInit {

  private SETTLEMENT = 'financialaidModuleState.settlement'.split('.');
  private SETTLEMENT_ITEMS = 'financialaidModuleState.settlementItems'.split('.');
  private settlement$: Observable<Settlement>;
  private settlementItems$: Observable<SettlementItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>,
              private snackBar: MdSnackBar) {

    this.settlement$ = this.store.select(...this.SETTLEMENT);
    this.settlementItems$ = this.store.select(...this.SETTLEMENT_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      this.store.dispatch(this.actions.findSettlementByReferenceNo(params.referenceNo));
    });
  }

  executeSettlement() {
    this.settlement$.take(1).subscribe((settlement) => {
        if (!settlement.executed) {
          let snackBarRef = this.snackBar.open('Are you sure you want to execute this settlement?', 'YES');
          snackBarRef.afterDismissed().subscribe(() => {
            this.store.dispatch(this.actions.executeSettlement(settlement));
          });
        }
        else {
          this.snackBar.open('Sorry, this settlement has been executed', 'OK');
        }
      },
    );
  }

  goBack(): void {
    this.router.navigate(['/settlements']);
  }
}

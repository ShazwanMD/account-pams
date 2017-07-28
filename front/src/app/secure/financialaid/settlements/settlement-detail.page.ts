import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {SettlementActions} from './settlement.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Settlement} from '../../../shared/model/financialaid/settlement.interface';
import {SettlementItem} from '../../../shared/model/financialaid/settlement-item.interface';
import {SettlementFileUploaderDialog} from './dialog/settlement-file-uploader.dialog';
@Component({
  selector: 'pams-settlement-detail',
  templateUrl: './settlement-detail.page.html',
})

export class SettlementDetailPage implements OnInit {

  private SETTLEMENT: string[] = 'financialaidModuleState.settlement'.split('.');
  private SETTLEMENT_ITEMS: string[] = 'financialaidModuleState.settlementItems'.split('.');
  private settlement$: Observable<Settlement>;
  private settlementItems$: Observable<SettlementItem[]>;
  private uploaderDialogRef: MdDialogRef<SettlementFileUploaderDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: SettlementActions,
              private store: Store<FinancialaidModuleState>,
              private snackBar: MdSnackBar,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {

    this.settlement$ = this.store.select(...this.SETTLEMENT);
    this.settlementItems$ = this.store.select(...this.SETTLEMENT_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      this.store.dispatch(this.actions.findSettlementByReferenceNo(params.referenceNo));
    });
  }

  executeSettlement(): void {
    this.settlement$.take(1).subscribe((settlement) => {
        if (!settlement.executed) {
          let snackBarRef = this.snackBar.open('Are you sure you want to execute this settlement?', 'YES');
          snackBarRef.afterDismissed().subscribe(() => {
            this.store.dispatch(this.actions.executeSettlement(settlement));
          });
        } else {
          this.snackBar.open('Sorry, this settlement has been executed', 'OK');
        }
      },
    );
  }

  showUploadDialog(): void {
    let config: MdDialogConfig = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.uploaderDialogRef = this.dialog.open(SettlementFileUploaderDialog, config);
    this.uploaderDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  goBack(): void {
    this.router.navigate(['/settlements']);
  }
}

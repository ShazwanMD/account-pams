import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {PromoCodeActions} from './promo-code.action';
import {MarketingModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {PromoCodeCreatorDialog} from './dialog/promo-code-creator.dialog';
import {PromoCode} from '../../../shared/model/marketing/promo-code.interface';

@Component({
  selector: 'pams-promo-code-center',
  templateUrl: './promo-code-center.page.html',
})

export class PromoCodeCenterPage implements OnInit {

  private PROMO_CODES = 'marketingModuleState.promoCodes'.split('.');
  private promoCodes$: Observable<PromoCode[]>;
  private creatorDialogRef: MdDialogRef<PromoCodeCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: PromoCodeActions,
              private store: Store<MarketingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.promoCodes$ = this.store.select(...this.PROMO_CODES);
  }

  goBack(route: string): void {
    this.router.navigate(['/promoCodes']);
  }

  view(promoCode: PromoCode) {
    console.log('promoCode: ' + promoCode.referenceNo);
    this.router.navigate(['marketing/promo-codes/', promoCode.referenceNo]);
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(PromoCodeCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // this.loadJournalEntries(this.journalTask.referenceNo);
    });
  }

  filter(): void {
  }

  ngOnInit(): void {
    console.log('find promoCodes');
    this.store.dispatch(this.actions.findPromoCodes());
  }
}


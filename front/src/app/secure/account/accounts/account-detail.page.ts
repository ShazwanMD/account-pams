import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import {Account} from '../../../shared/model/account/account.interface';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {AccountActions} from './account.action';
import {AccountModuleState} from '../index';
import {AccountTransaction} from '../../../shared/model/account/account-transaction.interface';
import {AccountWaiver} from '../../../shared/model/account/account-waiver.interface';
import {AccountCharge} from '../../../shared/model/account/account-charge.interface';
import { AccountActivity } from "../../../shared/model/account/account-activity.interface";
import { InvoiceItemDialog } from "./dialog/invoice-item.dialog";
import { Invoice } from "../../../shared/model/billing/invoice.interface";

@Component({
  selector: 'pams-account-detail',
  templateUrl: './account-detail.page.html',
})

export class AccountDetailPage implements OnInit {

  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');
  private ACCOUNT_ACTIVITY: string[] = 'accountModuleState.accountActivities'.split('.');
  private ACCOUNT_WAIVERS: string[] = 'accountModuleState.accountWaiver'.split('.');
  private SECURITY_ACCOUNT_CHARGES: string[] = 'accountModuleState.securityAccountCharges'.split('.');
  private ADMISSION_ACCOUNT_CHARGES: string[] = 'accountModuleState.admissionAccountCharges'.split('.');
  private STUDENT_AFFAIRS_ACCOUNT_CHARGES: string[] = 'accountModuleState.studentAffairsAccountCharges'.split('.');
  private LOAN_ACCOUNT_CHARGES: string[] = 'accountModuleState.loanAccountCharges'.split('.');
  private account$: Observable<Account>;
  private accountActivities$: Observable<AccountActivity[]>;
  private accountWaivers$: Observable<AccountWaiver[]>;
  private securityAccountCharges$: Observable<AccountCharge[]>;
  private admissionAccountCharges$: Observable<AccountCharge[]>;
  private studentAffairsAccountCharges$: Observable<AccountCharge[]>;
  private loanAccountCharges$: Observable<AccountCharge[]>;
  private editorDialogRef: MdDialogRef<InvoiceItemDialog>

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog
              ) {

    this.account$ = this.store.select(...this.ACCOUNT);
    this.accountActivities$ = this.store.select(...this.ACCOUNT_ACTIVITY);
    this.accountWaivers$ = this.store.select(...this.ACCOUNT_WAIVERS);
    this.securityAccountCharges$ = this.store.select(...this.SECURITY_ACCOUNT_CHARGES);
    this.admissionAccountCharges$ = this.store.select(...this.ADMISSION_ACCOUNT_CHARGES);
    this.studentAffairsAccountCharges$ = this.store.select(...this.STUDENT_AFFAIRS_ACCOUNT_CHARGES);
    this.loanAccountCharges$ = this.store.select(...this.LOAN_ACCOUNT_CHARGES);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { code: string }) => {
      let code: string = params.code;
      this.store.dispatch(this.actions.findAccountByCode(code));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }

  viewInvoice(activity: AccountActivity): void {
      console.log('showDialog');
      console.log('Activity' + activity);
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '0px'};
      this.editorDialogRef = this.dialog.open(InvoiceItemDialog, config);
      this.editorDialogRef.componentInstance.invoice.referenceNo = activity.sourceNo;
      this.editorDialogRef.afterClosed().subscribe((res) => {
        console.log('close dialog');
      });
    }

}


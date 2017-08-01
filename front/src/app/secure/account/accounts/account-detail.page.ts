import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Account} from '../../../shared/model/account/account.interface';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {AccountActions} from './account.action';
import {AccountModuleState} from '../index';
import {AccountWaiver} from '../../../shared/model/account/account-waiver.interface';
import {AccountCharge} from '../../../shared/model/account/account-charge.interface';
import {AccountActivity} from '../../../shared/model/account/account-activity.interface';
import {InvoiceItemDialog} from './dialog/invoice-item.dialog';
import { AccountTransaction } from "../../../shared/model/account/account-transaction.interface";
import {InvoiceItem} from '../../../shared/model/billing/invoice-item.interface';
import {Invoice} from '../../../shared/model/billing/invoice.interface';

@Component({
  selector: 'pams-account-detail',
  templateUrl: './account-detail.page.html',
})

export class AccountDetailPage implements OnInit {

    @Input() account: Account;
    @Input() invoice: Invoice;
    @Input() invoiceItems: InvoiceItem[];
    @Input() activity: AccountActivity[];
    
  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');
  private ACCOUNT_ACTIVITY: string[] = 'accountModuleState.accountActivities'.split('.');
  private ACCOUNT_WAIVERS: string[] = 'accountModuleState.accountWaiver'.split('.');
  private ACCOUNT_TRANSACTIONS: string[] = 'accountModuleState.accountTransactions'.split('.');
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
  private accountTransactions$: Observable<AccountTransaction[]>;
  

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {

    this.account$ = this.store.select(...this.ACCOUNT);
    this.accountActivities$ = this.store.select(...this.ACCOUNT_ACTIVITY);
    this.accountWaivers$ = this.store.select(...this.ACCOUNT_WAIVERS);
    this.securityAccountCharges$ = this.store.select(...this.SECURITY_ACCOUNT_CHARGES);
    this.admissionAccountCharges$ = this.store.select(...this.ADMISSION_ACCOUNT_CHARGES);
    this.studentAffairsAccountCharges$ = this.store.select(...this.STUDENT_AFFAIRS_ACCOUNT_CHARGES);
    this.loanAccountCharges$ = this.store.select(...this.LOAN_ACCOUNT_CHARGES);
    this.accountTransactions$ = this.store.select(...this.ACCOUNT_TRANSACTIONS);
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

}


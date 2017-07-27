import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  state,
  ViewContainerRef,
  Input,
  EventEmitter,
  Output,
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {AccountActions} from './account.action';
import {AccountModuleState} from '../index';
import {AccountCreatorDialog} from './dialog/account-creator.dialog';
import {Account} from '../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-account-center',
  templateUrl: './account-center.page.html',

})

export class AccountCenterPage implements OnInit {

  private ACCOUNTS: string[] = 'accountModuleState.accounts'.split('.');
  private ACCOUNT_STUDENT_LIST: string[] = 'accountModuleState.accountStudentList'.split('.');
  private ACCOUNT_SPONSOR_LIST: string[] = 'accountModuleState.accountSponsorList'.split('.');
  private creatorDialogRef: MdDialogRef<AccountCreatorDialog>;
  private accountStudentList$: Observable<Account[]>;
  private accountSponsorList$: Observable<Account[]>;

  @Input() account: Account;
  @Output() view = new EventEmitter<Account>();

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {

    this.accountStudentList$ = this.store.select(...this.ACCOUNT_STUDENT_LIST);
    this.accountSponsorList$ = this.store.select(...this.ACCOUNT_SPONSOR_LIST);
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }

  viewAccount(account: Account) {
    console.log('account: ' + account.id);
    this.router.navigate(['/accounts-detail', account.id]);
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(AccountCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  ngOnInit(): void {
  this.store.dispatch(this.actions.findAccountsByActor());
  this.store.dispatch(this.actions.findAccountsByActorSponsor());
  }
}

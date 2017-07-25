import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {Account} from '../account.interface';
import {AccountWaiver} from '../account-waiver.interface';
import {Observable} from 'rxjs/Observable';
import {Router, ActivatedRoute} from '@angular/router';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {AccountModuleState} from '../../index';

@Component({
  selector: 'pams-account-status',
  templateUrl: './account-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class AccountStatusComponent {
  @Input() account: Account;
  @Input() waivers: AccountWaiver[];

  getSum(): number {
    let sum: number = 0;
    for (let i: number = 0; i < this.waivers.length; i++) {
      if (this.waivers[i].amount) {
        sum += this.waivers[i].amount;
      }
      return sum;
    }
  }

  // todo(hajar): kenapa tak decorate je?
  // todo(hajar): decorate account utk dapatkan waived and balance
  getWaived(): number {
    let effectiveBalance: number = 0;
    if (!this.getSum()) {
      effectiveBalance = this.account.balance;
    } else {
      effectiveBalance = this.account.balance - this.getSum();
    }
    return effectiveBalance;
  }
}

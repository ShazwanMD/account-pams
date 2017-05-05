import { Observable } from 'rxjs';
import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FeeSchedule} from './accounts/fee-schedule.interface'

import {IdentityService} from '../../services';
import {AccountService} from "../../services/account.service";

@Component({
  selector: 'pams-account-page',
  templateUrl: './account.page.html',
})

export class AccountPage implements OnInit {

  private feeSchedules$:Observable<FeeSchedule[]>;

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private accountService: AccountService) {
  }

  ngOnInit(): void {
     this.feeSchedules$ = this.accountService.findFeeSchedules();
  }
}

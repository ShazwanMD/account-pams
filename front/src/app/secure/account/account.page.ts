import {Observable} from 'rxjs';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountService} from '../../../services/account.service';
import {FeeSchedule} from '../../shared/model/account/fee-schedule.interface';

@Component({
  selector: 'pams-account-page',
  templateUrl: './account.page.html',
})

export class AccountPage implements OnInit {

  private feeSchedules$: Observable<FeeSchedule[]>;

  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''},
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.feeSchedules$ = this.accountService.findFeeSchedules();
  }
}

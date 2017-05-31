import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {AccountTransaction} from "../account-transaction.interface";
import {AccountWaiver} from "../account-waiver.interface";
import { Observable } from "rxjs/Observable";
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {Store} from "@ngrx/store";
import {AccountActions} from "../account.action";
import { AccountModuleState } from "../../index";
import {Account} from "../account.interface";

@Component({
  selector: 'pams-account-waiver-list',
  templateUrl: './account-waiver-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountWaiverListComponent implements OnInit {
  
  @Input() waivers: AccountWaiver[];
  @Input() account: Account;
  
  private columns: any[] = [
       {name: 'sourceNo', label: 'Source No'},
       {name: 'amount', label: 'Amount'},
       {name: 'session.code', label: 'Session'},
       {name: 'action', label: ''}
  ];
  
  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: AccountActions,
          private store: Store<AccountModuleState>,
          private vcf: ViewContainerRef,
          private dialog: MdDialog) {
}
  
  ngOnInit(): void{

    }
  
}

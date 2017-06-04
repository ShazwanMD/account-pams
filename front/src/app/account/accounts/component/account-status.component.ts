import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {Account} from "../account.interface";
import {AccountWaiver} from "../account-waiver.interface";
import { Observable } from "rxjs/Observable";
import { Router, ActivatedRoute } from "@angular/router";
import { AccountActions } from "../account.action";
import { Store } from "@ngrx/store";
import { MdDialog } from "@angular/material";
import { AccountModuleState } from "../../index";

@Component({
  selector: 'pams-account-status',
  templateUrl: './account-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class AccountStatusComponent {
  @Input() account: Account;
  @Input() waivers: AccountWaiver[];
                  
  getSum(){
      let sum = 0;
      for (var i = 0; i < this.waivers.length; i++) {
          if (this.waivers[i].amount) {
              sum +=this.waivers[i].amount;
          }
     return sum;
     }
  }
  
  getWaived(){
      let effectiveBalance = 0;
      effectiveBalance = this.account.balance-this.getSum();
      
      return effectiveBalance;
  }
}

import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Router, ActivatedRoute } from "@angular/router";
import { Store } from "@ngrx/store";
import { MdDialog } from "@angular/material";
import { AccountModuleState } from "../../index";
import { FeeScheduleItem } from "../fee-schedule-item.interface";
import { FeeScheduleActions } from "../fee-schedule.action";
import { FeeSchedule } from "../fee-schedule.interface";

@Component({
  selector: 'pams-fee-schedule-status',
  templateUrl: './fee-schedule-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class FeeScheduleStatusComponent {

  @Input() feeSchedule: FeeSchedule;
  @Input() feeScheduleItems: FeeScheduleItem[];

  constructor(private router: Router,
          private route: ActivatedRoute,
          private actions: FeeScheduleActions,
          private store: Store<AccountModuleState>,
          private vcf: ViewContainerRef){

    }
  
  getSum(){
      let sum = 0;
      for (var i = 0; i < this.feeScheduleItems.length; i++) {
          //if (this.feeScheduleItems[i].amount) {
              sum +=this.feeScheduleItems[i].amount;
          //}
      }
     return sum;
     //this.store.dispatch(this.actions.updateFeeSchedule(this.feeSchedule));
     }
  
}

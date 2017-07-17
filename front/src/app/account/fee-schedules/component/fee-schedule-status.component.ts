import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import { Observable } from "rxjs/Observable";
import { Router, ActivatedRoute } from "@angular/router";
import { Store } from "@ngrx/store";
import { MdDialog } from "@angular/material";
import { AccountModuleState } from "../../index";
import { FeeScheduleActions } from "../fee-schedule.action";
import { FeeSchedule } from "../fee-schedule.interface";

@Component({
  selector: 'pams-fee-schedule-status',
  templateUrl: './fee-schedule-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class FeeScheduleStatusComponent {

  @Input() feeSchedule: FeeSchedule;
  
}

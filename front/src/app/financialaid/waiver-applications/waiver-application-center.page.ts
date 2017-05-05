import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FinancialaidModuleState} from "../index";
import {Store} from "@ngrx/store";
import {WaiverApplicationTask} from "./waiver-application-task.interface";
import {Observable} from "rxjs/Observable";


@Component({
  selector: 'pams-waiver-application-center',
  templateUrl: './waiver-application-center.page.html',
})

export class WaiverApplicationCenterPage implements OnInit {

  private ASSIGNED_WAIVER_APPLICATION_TASKS = "financialaidModuleState.assignedWaiverApplicationTasks".split(".")
  private POOLED_WAIVER_APPLICATION_TASKS = "financialaidModuleState.pooledWaiverApplicationTasks".split(".")

  private assignedWaiverApplicationTasks$: Observable<WaiverApplicationTask>;
  private pooledWaiverApplicationTasks$: Observable<WaiverApplicationTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<FinancialaidModuleState>) {
    this.assignedWaiverApplicationTasks$ = this.store.select(...this.ASSIGNED_WAIVER_APPLICATION_TASKS);
    this.pooledWaiverApplicationTasks$ = this.store.select(...this.POOLED_WAIVER_APPLICATION_TASKS);
  }

  ngOnInit(): void {
  }
}

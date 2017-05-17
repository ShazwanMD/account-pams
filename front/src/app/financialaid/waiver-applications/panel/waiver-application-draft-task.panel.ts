import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from "@angular/material";
import {WaiverApplicationTask} from "../waiver-application-task.interface";
import {WaiverApplicationActions} from "../waiver-application.action";
import {WaiverApplicationTaskState} from "../waiver-application-task.reducer";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {FinancialaidModuleState} from "../../index";


@Component({
  selector: 'pams-waiver-application-draft-task',
  templateUrl: './waiver-application-draft-task.panel.html',
})

export class WaiverApplicationDraftTaskPanel implements OnInit {

  @Input() waiverApplicationTask: WaiverApplicationTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverApplicationActions,
              private store: Store<FinancialaidModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
  }

  register() {
    this.store.dispatch(this.actions.completeWaiverApplicationTask(this.waiverApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/billing/waiverApplications']);
  }
}

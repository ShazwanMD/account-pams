import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {DebitNoteTask} from "./debit-note-task.interface";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";
import {DebitNoteActions} from "./debit-note.action";


@Component({
  selector: 'pams-debit-note-task-detail',
  templateUrl: './debit-note-task-detail.page.html',
})
export class DebitNoteTaskDetailPage implements OnInit {

  private DEBIT_NOTE_TASK = "billingModuleState.debitNoteTask".split(".");
  private debitNoteTask$: Observable<DebitNoteTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: DebitNoteActions) {
    this.debitNoteTask$ = this.store.select(...this.DEBIT_NOTE_TASK)
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { taskId: string }) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findDebitNoteTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/debit-notes']);
  }
}



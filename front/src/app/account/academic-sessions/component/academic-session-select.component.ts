import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {AccountModuleState} from "../../index";
import {AcademicSessionActions} from "./academic-session.action";
import {AcademicSession} from "../academic-session.interface";


@Component({
  selector: 'pams-academic-session-select',
  templateUrl: './academic-session-select.component.html',
})
export class AcademicSessionSelectComponent implements OnInit {

  private ACADEMIC_SESSIONS = "accountModuleState.academicSessions".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  private academicSessions$: Observable<AcademicSession[]>;

  constructor(private store: Store<AccountModuleState>,
              private actions: AcademicSessionActions) {
    this.academicSessions$ = this.store.select(...this.ACADEMIC_SESSIONS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findAcademicSessions());
  }

  selectChangeEvent(event: AcademicSession) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {Actor} from "../actor.interface";
import {ActorActions} from "../actor.action";
import {IdentityModuleState} from "../index";
import {StudentActions} from "../student.action";


@Component({
  selector: 'pams-student-select',
  templateUrl: './student-select.component.html',
})
export class StudentSelectComponent implements OnInit {

  private STUDENTS = "identityModuleState.students".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  programLevels$: Observable<Actor[]>;

  constructor(private store: Store<IdentityModuleState>,
              private actions: StudentActions) {
    this.programLevels$ = this.store.select(...this.STUDENTS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findStudents());
  }

  selectChangeEvent(event: Actor) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


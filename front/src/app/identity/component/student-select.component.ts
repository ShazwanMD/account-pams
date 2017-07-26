import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {IdentityModuleState} from '../index';
import {StudentActions} from '../student.action';
import {Actor} from '../../shared/model/identity/actor.interface';

@Component({
  selector: 'pams-student-select',
  templateUrl: './student-select.component.html',
})
export class StudentSelectComponent implements OnInit {

  private STUDENTS: string[] = 'identityModuleState.students'.split('.');
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


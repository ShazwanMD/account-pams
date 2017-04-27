import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from "rxjs/observable/from";
import {IdentityService} from "../../services/identity.service";
import {StudentActions} from "./student.action";


@Injectable()
export class StudentEffects {
  constructor(private actions$: Actions,
              private accountActions: StudentActions,
              private identityService: IdentityService) {
  }

  @Effect() findStudents$ = this.actions$
    .ofType(StudentActions.FIND_STUDENTS)
    .switchMap(() => this.identityService.findStudents())
    .map(accounts => this.accountActions.findStudentsSuccess(accounts));

  @Effect() findStudent$ = this.actions$
    .ofType(StudentActions.FIND_STUDENT)
    .map(action => action.payload)
    .switchMap(code => this.identityService.findStudentByIdentityNo(code))
    .map(account => this.accountActions.findStudentSuccess(account));

  @Effect() updateStudent$ = this.actions$
    .ofType(StudentActions.UPDATE_STUDENT)
    .map(action => action.payload);
    // .switchMap(account => this.identityService.updateStudent(account))
    // .map(account => this.accountActions.updateStudentSuccess(account));
}

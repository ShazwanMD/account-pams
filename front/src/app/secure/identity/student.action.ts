import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class StudentActions {

  static FIND_STUDENTS = '[Student] Find Students';

  findStudents(): Action {
    return {
      type: StudentActions.FIND_STUDENTS
    };
  }

  static FIND_STUDENTS_SUCCESS = '[Student] Find Students Success';

  findStudentsSuccess(students): Action {
    console.log("findStudentsSuccess");
    return {
      type: StudentActions.FIND_STUDENTS_SUCCESS,
      payload: students
    };
  }

  static FIND_STUDENT = '[Student] Find Student';

  findStudent(identityNo): Action {
    return {
      type: StudentActions.FIND_STUDENT,
      payload: identityNo
    };
  }

  static FIND_STUDENT_SUCCESS = '[Student] Find Student Success';

  findStudentSuccess(student): Action {
    console.log("findStudentSuccess");
    return {
      type: StudentActions.FIND_STUDENT_SUCCESS,
      payload: student
    };
  }


  static UPDATE_STUDENT = '[Student] Update Student';

  updateStudent(account): Action {
    return {
      type: StudentActions.UPDATE_STUDENT,
      payload: account
    };
  }

  static UPDATE_STUDENT_SUCCESS = '[Student] Update Student Success';

  updateStudentSuccess(account): Action {
    return {
      type: StudentActions.UPDATE_STUDENT_SUCCESS,
      payload: account
    };
  }
}

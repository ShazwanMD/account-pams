import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FacultyCode} from "../faculty-code.interface";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {CommonActions} from "../../common.action";
import {CommonModuleState} from "../../index";


@Component({
  selector: 'pams-faculty-code-select',
  templateUrl: './faculty-code-select.component.html',
})
export class FacultyCodeSelectComponent implements OnInit {

  private FACULTY_CODES = "commonModuleState.facultyCodes".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  facultyCodes$: Observable<FacultyCode[]>;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.facultyCodes$ = this.store.select(...this.FACULTY_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findFacultyCodes());
  }

  selectChangeEvent(event: FacultyCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


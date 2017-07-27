import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {ProgramCode} from '../../../shared/model/common/program-code.interface';

@Component({
  selector: 'pams-program-code-select',
  templateUrl: './program-code-select.component.html',
})
export class ProgramCodeSelectComponent implements OnInit {

  private PROGRAM_CODES = 'commonModuleState.programCodes'.split('.');
  private programCodes$: Observable<ProgramCode[]>;
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.programCodes$ = this.store.select(...this.PROGRAM_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findProgramCodes());
  }

  selectChangeEvent(event: ProgramCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


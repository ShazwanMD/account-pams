import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {ProgramLevel} from '../../../shared/model/common/program-level.interface';

@Component({
  selector: 'pams-program-level-select',
  templateUrl: './program-level-select.component.html',
  styleUrls: ['./program-level-select.scss'],
})
export class ProgramLevelSelectComponent implements OnInit {

  private PROGRAM_LEVELS = 'commonModuleState.programLevels'.split('.');
  private programLevels$: Observable<ProgramLevel[]>;
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.programLevels$ = this.store.select(...this.PROGRAM_LEVELS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findProgramLevels());
  }

  selectChangeEvent(event: ProgramLevel) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


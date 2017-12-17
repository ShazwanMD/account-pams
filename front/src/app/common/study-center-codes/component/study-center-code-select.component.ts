import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {StudyCenterCode} from '../../../shared/model/common/study-center-code.interface';

@Component({
  selector: 'pams-study-center-code-select',
  templateUrl: './study-center-code-select.component.html',
   styleUrls: ['./study-center-code-select.scss'],

})
export class StudyCenterCodeSelectComponent implements OnInit {

  private STUDY_CENTER_CODES = 'commonModuleState.studyCenterCodes'.split('.');
  private studyCenterCodes$: Observable<StudyCenterCode[]>;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.studyCenterCodes$ = this.store.select(...this.STUDY_CENTER_CODES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findStudyCenterCodes());
  }

  selectChangeEvent(event: StudyCenterCode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


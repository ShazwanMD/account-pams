import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {CommonActions} from '../../common.action';
import {CommonModuleState} from '../../index';
import {StudyMode} from '../../../shared/model/common/study-mode.interface';

@Component({
  selector: 'pams-study-mode-select',
  templateUrl: './study-mode-select.component.html',
  styleUrls: ['./study-mode-select.scss'],
})
export class StudyModeSelectComponent implements OnInit {

  private STUDY_MODE: string[] = 'commonModuleState.studyModes'.split('.');
  private studyModes$: Observable<StudyMode[]>;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<CommonModuleState>,
              private actions: CommonActions) {
    this.studyModes$ = this.store.select(...this.STUDY_MODE);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findStudyModes());
  }

  selectChangeEvent(event: StudyMode) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}


import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { DebitNoteStatusType } from '../../../shared/model/common/debit-note-status-type.enum';
@Component({
 selector: 'pams-debit-note-status-type-select',
 templateUrl: './debit-note-status-type-select.component.html',
 styleUrls: ['./debit-note-status-type-select.component.scss'],

})
export class DebitNoteStatusTypeSelectComponent implements OnInit {
 private debitNoteStatusTypes: DebitNoteStatusType[] = <DebitNoteStatusType[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;
 constructor() {
  for (let n in DebitNoteStatusType) {
    if (typeof DebitNoteStatusType[n] === 'string')
      this.debitNoteStatusTypes.push(DebitNoteStatusType[n.toString()]);
  }
}
 ngOnInit() {
 }
 selectChangeEvent(event: DebitNoteStatusType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}
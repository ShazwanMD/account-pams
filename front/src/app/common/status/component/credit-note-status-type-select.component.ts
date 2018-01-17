import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { CreditNoteStatusType } from '../../../shared/model/common/credit-note-status-type';

@Component({
 selector: 'pams-credit-note-status-type-select',
 templateUrl: './credit-note-status-type-select.component.html',
 styleUrls: ['./credit-note-status-type-select.component.scss'],
})
export class CreditNoteStatusTypeSelectComponent implements OnInit {
 private creditNoteStatusTypes: CreditNoteStatusType[] = <CreditNoteStatusType[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;
 constructor() {
  for (let n in CreditNoteStatusType) {
    if (typeof CreditNoteStatusType[n] === 'string')
      this.creditNoteStatusTypes.push(CreditNoteStatusType[n.toString()]);
  }
}
 ngOnInit() {
 }
 selectChangeEvent(event: CreditNoteStatusType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}
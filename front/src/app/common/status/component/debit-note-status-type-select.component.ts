import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { FlowState } from '../../../core/flow-state.enum';

@Component({
 selector: 'pams-debit-note-status-type-select',
 templateUrl: './debit-note-status-type-select.component.html',
})
export class DebitNoteStatusTypeSelectComponent implements OnInit {

 private flowState: FlowState[] = <FlowState[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;

 constructor() {
   for (let n in FlowState) {
     if (typeof FlowState[n] === 'string')
       this.flowState.push(FlowState[n.toString()]);
   }
 }

 ngOnInit() {
 }

 selectChangeEvent(event: FlowState) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}
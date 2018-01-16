import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { KnockoffStatusType } from '../../../shared/model/common/knockoff-status-type';

@Component({
 selector: 'pams-knockoff-status-type-select',
 templateUrl: './knockoff-status-type-select.component.html',
})
export class KnockoffStatusTypeSelectComponent implements OnInit {
 private knockoffStatusTypes: KnockoffStatusType[] = <KnockoffStatusType[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;
 constructor() {
  for (let n in KnockoffStatusType) {
    if (typeof KnockoffStatusType[n] === 'string')
      this.knockoffStatusTypes.push(KnockoffStatusType[n.toString()]);
  }
}
 ngOnInit() {
 }
 selectChangeEvent(event: KnockoffStatusType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}
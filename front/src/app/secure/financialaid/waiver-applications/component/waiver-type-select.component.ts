import {WaiverApplicationType} from '../../../../shared/model/financialaid/waiver-application-type.enum';
import {Component, Input} from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'pams-waiver-type-select',
  templateUrl: './waiver-type-select.component.html',
  styleUrls: ['./waiver-type-select.component.scss'],
})
export class WaiverTypeSelectComponent {

  private waiverTypes: WaiverApplicationType[] = <WaiverApplicationType[]>[];

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in WaiverApplicationType) {
      if (typeof WaiverApplicationType[n] === 'string')
        this.waiverTypes.push(WaiverApplicationType[n.toString()]);
    }
  }

  selectChangeEvent(event: WaiverApplicationType) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

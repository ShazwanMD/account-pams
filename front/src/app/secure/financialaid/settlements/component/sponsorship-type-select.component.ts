import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import {SponsorshipType} from '../../../../shared/model/financialaid/sponsorship-type.enum';

@Component({
  selector: 'pams-sponsorship-type-select',
  templateUrl: './sponsorship-type-select.component.html',
})
export class SponsorshipTypeSelectComponent implements OnInit {

  private sponsorshipTypes: SponsorshipType[] = <SponsorshipType[]>[];
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in SponsorshipType) {
      if (typeof SponsorshipType[n] === 'string')
        this.sponsorshipTypes.push(SponsorshipType[n.toString()]);
    }
  }

  ngOnInit() {
  }

  selectChangeEvent(event: SponsorshipType) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

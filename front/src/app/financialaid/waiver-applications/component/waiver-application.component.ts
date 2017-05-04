import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {WaiverApplication} from "../waiver-application.interface";

@Component({
  selector: 'pams-waiver-application',
  templateUrl: './waiver-application.component.html',

})
export class WaiverApplicationComponent {

  @Input() waiverApplication: WaiverApplication;
  // @Output() addComment = new EventEmitter<AccountComment>();
  // @Output() addAttachment = new EventEmitter<AccountAttachment>();
}
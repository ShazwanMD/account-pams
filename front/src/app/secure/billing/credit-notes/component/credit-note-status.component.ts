import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';

@Component({
  selector: 'pams-credit-note-status',
  templateUrl: './credit-note-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class CreditNoteStatusComponent {
  @Input() creditNote: CreditNote;
}

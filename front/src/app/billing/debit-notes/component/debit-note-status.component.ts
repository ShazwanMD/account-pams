import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import { DebitNote } from "../debit-note.interface";

@Component({
  selector: 'pams-debit-note-status',
  templateUrl: './debit-note-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class DebitNoteStatusComponent {
  @Input() debitNote:DebitNote;
}

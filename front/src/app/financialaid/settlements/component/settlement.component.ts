import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {Settlement} from "../settlement.interface";

@Component({
  selector: 'pams-settlement',
  templateUrl: './settlement.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SettlementComponent {

  @Input() settlement: Settlement;
}

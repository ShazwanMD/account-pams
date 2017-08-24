import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {Knockoff} from '../../../../shared/model/billing/knockoff.interface';

@Component({
  selector: 'pams-knockoff-status',
  templateUrl: './knockoff-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class KnockoffStatusComponent {
  @Input() knockoff: Knockoff;
}

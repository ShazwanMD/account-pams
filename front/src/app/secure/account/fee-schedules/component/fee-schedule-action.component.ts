import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import { MdDialog, MdDialogRef, MdDialogConfig } from '@angular/material';
import { FeeScheduleEditorDialog } from '../dialog/fee-schedule-editor.dialog';
import {FeeSchedule} from '../../../../shared/model/account/fee-schedule.interface';

@Component({
  selector: 'pams-fee-schedule-action',
  templateUrl: './fee-schedule-action.component.html',
})

export class FeeScheduleActionComponent {

  private editorDialogRef: MdDialogRef<FeeScheduleEditorDialog>;

  @Input() feeSchedule: FeeSchedule;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }
  
  edit(): void {
      console.log('showDialog' + this.feeSchedule.code);
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '60%';
      config.position = {top: '0px'};
      this.editorDialogRef = this.dialog.open(FeeScheduleEditorDialog, config);
      this.editorDialogRef.componentInstance.feeSchedule = this.feeSchedule;
      this.editorDialogRef.afterClosed().subscribe((res) => {
        console.log('close dialog');
      });
  }
}


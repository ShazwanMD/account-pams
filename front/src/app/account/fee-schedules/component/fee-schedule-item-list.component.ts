import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {Sponsor} from "../../../identity/sponsor.interface";
import {FeeSchedule} from "../fee-schedule.interface";
import {AccountModuleState} from "../../index";
import {FeeScheduleActions} from "../fee-schedule.action";
import {CohortCode} from "../../../common/cohort-codes/cohort-code.interface";
import {FeeScheduleItemEditorDialog} from "../dialog/fee-schedule-item-editor.dialog";
import {FeeScheduleItem} from "../fee-schedule-item.interface";


@Component({
  selector: 'pams-fee-schedule-item-list',
  templateUrl: './fee-schedule-item-list.component.html',
})

export class FeeScheduleItemListComponent {

  private creatorDialogRef: MdDialogRef<FeeScheduleItemEditorDialog>;

  @Input() feeSchedule: FeeSchedule;
  @Input() feeScheduleItems: FeeScheduleItem[];

  private columns: any[] = [
    {name: 'chargeCode', label: 'ChargeCode'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: FeeScheduleActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '40%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(FeeScheduleItemEditorDialog, config);
    this.creatorDialogRef.componentInstance.feeSchedule = this.feeSchedule;

    // close
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }
}

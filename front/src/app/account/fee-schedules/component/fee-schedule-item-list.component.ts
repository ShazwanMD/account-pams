import {Component, ViewContainerRef, OnInit, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {FeeSchedule} from "../fee-schedule.interface";
import {FeeScheduleActions} from "../fee-schedule.action";
import {FeeScheduleItemEditorDialog} from "../dialog/fee-schedule-item-editor.dialog";
import {FeeScheduleItem} from "../fee-schedule-item.interface";
import {AccountModuleState} from "../../index";
import {Store} from "@ngrx/store";


@Component({
  selector: 'pams-fee-schedule-item-list',
  templateUrl: './fee-schedule-item-list.component.html',
})

export class FeeScheduleItemListComponent implements OnInit {

  @Input() feeSchedule: FeeSchedule;
  @Input() feeScheduleItems: FeeScheduleItem[];

  private creatorDialogRef: MdDialogRef<FeeScheduleItemEditorDialog>;
  private selectedRows: FeeScheduleItem[];
  private columns: any[] = [
    {name: 'chargeCode', label: 'ChargeCode'},
    {name: 'ordinal', label: 'Semester'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: FeeScheduleActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.feeScheduleItems.filter(value => value.selected);
  }

  edit(scheduleItem: FeeScheduleItem): void {
    // this.showDialog(scheduleItem);
  }

  delete(scheduleItem: FeeScheduleItem): void {
    this.store.dispatch(this.actions.deleteFeeScheduleItem(this.feeSchedule, scheduleItem));
  }

  filter(): void {
  }

  selectRow(item: FeeScheduleItem): void {
  }

  selectAllRows(item: FeeScheduleItem[]): void {
  }

  create(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
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

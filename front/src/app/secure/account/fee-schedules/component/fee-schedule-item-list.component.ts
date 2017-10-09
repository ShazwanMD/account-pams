import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {FeeScheduleActions} from '../fee-schedule.action';
import {FeeScheduleItemEditorDialog} from '../dialog/fee-schedule-item-editor.dialog';
import {AccountModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {FeeSchedule} from '../../../../shared/model/account/fee-schedule.interface';
import {FeeScheduleItem} from '../../../../shared/model/account/fee-schedule-item.interface';

@Component({
  selector: 'pams-fee-schedule-item-list',
  templateUrl: './fee-schedule-item-list.component.html',
})

export class FeeScheduleItemListComponent implements OnInit {

  private creatorDialogRef: MdDialogRef<FeeScheduleItemEditorDialog>;
  private editorDialogRef: MdDialogRef<FeeScheduleItemEditorDialog>;
  private selectedRows: FeeScheduleItem[];
  private columns: any[] = [
    {name: 'chargeCode', label: 'ChargeCode'},
    {name: 'ordinal', label: 'Semester'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''},
  ];

  @Input() feeSchedule: FeeSchedule;
  @Input() feeScheduleItems: FeeScheduleItem[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: FeeScheduleActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.feeScheduleItems.filter((value) => value.selected);
  }

  edit(scheduleItem: FeeScheduleItem): void {
    this.showDialog(scheduleItem);
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

  createDialog(): void {
    this.showDialog(null);
  }

  showDialog(scheduleItem: FeeScheduleItem): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(FeeScheduleItemEditorDialog, config);
    this.editorDialogRef.componentInstance.feeSchedule = this.feeSchedule;
    if (scheduleItem) this.editorDialogRef.componentInstance.feeScheduleItem = scheduleItem; // set
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }




}

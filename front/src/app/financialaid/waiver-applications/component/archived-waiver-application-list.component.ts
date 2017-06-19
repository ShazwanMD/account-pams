import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";
import {WaiverApplication} from "../waiver-application.interface";

@Component({
  selector: 'pams-archived-waiver-application-list',
  templateUrl: './archived-waiver-application-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedWaiverApplicationListComponent {

    @Input() waiverApplications: WaiverApplication[];
    @Output() view = new EventEmitter<WaiverApplication>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'application.waivedAmount', label: 'Waived Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewWaiverApplication(waiverApplication: WaiverApplication): void {
      console.log("Emitting task");
      let snackBarRef = this.snackBar.open("Viewing waiverApplication", "OK");
      snackBarRef.afterDismissed().subscribe(() => {
        this.view.emit(waiverApplication);
      });
    }
}

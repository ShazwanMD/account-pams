import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef} from '@angular/core';
import {ChargeCode} from "../charge-code.interface";
import {Store} from "@ngrx/store";
import {ChargeCodeActions} from "../charge-code.action";
import {AccountModuleState} from "../../index";
import {MdDialogRef, MdDialogConfig, MdDialog} from "@angular/material";
import {ChargeCodeEditorDialog} from "../dialog/charge-code-editor.dialog";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'pams-charge-code-list',
  templateUrl: './charge-code-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ChargeCodeListComponent {

  @Input() chargeCodes: ChargeCode[];
  @Output() view = new EventEmitter<ChargeCode>();

  private CHARGE_CODES: string[] = "accountModuleState.chargeCodes".split(".");
  chargeCodes$: Observable<ChargeCode[]>;
  private editorDialogRef: MdDialogRef<ChargeCodeEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'Description'},
    {name: 'priority', label: 'Priority'},
    {name: 'action', label: ''}
  ];

  constructor(private store: Store<AccountModuleState>,
              private actions: ChargeCodeActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.chargeCodes$ = this.store.select(...this.CHARGE_CODES);
  }

  editDialog(code: ChargeCode): void {
    this.showDialog(code);
  }

  delete(code: ChargeCode): void {
    this.store.dispatch(this.actions.removeChargeCode(code))
  }

  filter(): void {
  }

  private showDialog(code: ChargeCode): void {
    console.log("create");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(ChargeCodeEditorDialog, config);
    if (code) this.editorDialogRef.componentInstance.chargeCode = code; // set
    this.editorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

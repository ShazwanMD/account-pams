import {SecurityChargeCodeEditorDialog} from './dialog/security-charge-code-editor.dialog';
import {SecurityChargeCode} from '../../../shared/model/common/security-charge-code.interface';

import {ChangeDetectionStrategy, Component, OnInit, ViewContainerRef} from '@angular/core';

import {Store} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {SetupModuleState} from '../index';
import {Observable} from 'rxjs/Observable';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {
  IPageChangeEvent,
  ITdDataTableSortChangeEvent,
  TdDataTableService,
  TdDataTableSortingOrder
} from '@covalent/core';

@Component({
  selector: 'pams-security-charge-code-list-page',
  templateUrl: './security-charge-code-list.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SecurityChargeCodeListPage implements OnInit {

  private SECURITY_CHARGE_CODES = 'setupModuleState.securityChargeCodes'.split('.');
  private securityChargeCodes$: Observable<SecurityChargeCode[]>;
  private creatorDialogRef: MdDialogRef<SecurityChargeCodeEditorDialog>;

  private securityChargeCodes: SecurityChargeCode[];

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dataTableService: TdDataTableService) {
    this.securityChargeCodes$ = this.store.select(...this.SECURITY_CHARGE_CODES);
    this.securityChargeCodes$.subscribe((SecurityChargeCodes) => this.securityChargeCodes = SecurityChargeCodes);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findSecurityChargeCodes());
    this.store.dispatch(this.actions.changeTitle('Security Charge Codes'));
  }

  createDialog(): void {
    this.showDialog(null);
  }

  editDialog(code: SecurityChargeCode): void {
    this.showDialog(code);
  }

  // delete(code: SecurityChargeCode): void {
  //   this.store.dispatch(this.actions.removeSecurityChargeCode(code));
  // }

  private showDialog(code: SecurityChargeCode): void {
    console.log('create');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(SecurityChargeCodeEditorDialog, config);
    if (code) this.creatorDialogRef.componentInstance.securityChargeCode = code; // set
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}

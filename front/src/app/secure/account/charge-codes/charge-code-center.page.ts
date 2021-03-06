import { ChargeCodeEditorDialog } from './dialog/charge-code-editor.dialog';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ChargeCodeActions} from './charge-code.action';
import {AccountModuleState} from '../index';
import {ChargeCode} from '../../../shared/model/account/charge-code.interface';

@Component({
  selector: 'pams-charge-code-center',
  templateUrl: './charge-code-center.page.html',
})

export class ChargeCodeCenterPage implements OnInit {

  private CHARGE_CODES: string[] = 'accountModuleState.chargeCodes'.split('.');
  private chargeCodes$: Observable<ChargeCode[]>;
  private creatorDialogRef: MdDialogRef<ChargeCodeEditorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: ChargeCodeActions,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.chargeCodes$ = this.store.select(...this.CHARGE_CODES);
  }

  goBack(route: string): void {
    this.router.navigate(['/charge-codes']);
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(ChargeCodeEditorDialog, config);
  }

  filter(): void {
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findChargeCodes());
  }
}


import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {MarketingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {PromoCodeActions} from '../promo-code.action';
import {PromoCodeItemEditorDialog} from '../dialog/promo-code-item-editor.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {PromoCode} from '../../../../shared/model/marketing/promo-code.interface';
import {PromoCodeItem} from '../../../../shared/model/marketing/promo-code-item.interface';

@Component({
  selector: 'pams-promo-code-item-list',
  templateUrl: './promo-code-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PromoCodeItemListComponent implements OnInit {

  private selectedRows: PromoCodeItem[];
  private editorDialogRef: MdDialogRef<PromoCodeItemEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'applied', label: 'Applied'},
    {name: 'sourceNo', label: 'SourceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'action', label: ''},
  ];

  @Input() promoCode: PromoCode;
  @Input() promoCodeItems: PromoCodeItem[];

  constructor(private vcf: ViewContainerRef,
              private store: Store<MarketingModuleState>,
              private actions: PromoCodeActions,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
    this.selectedRows = this.promoCodeItems.filter((value) => value.selected);
  }

  create(): void {
    this.showDialog(null);
  }

  edit(promoCodeItem: PromoCodeItem): void {
    this.showDialog(promoCodeItem);
  }

  delete(promoCodeItem: PromoCodeItem): void {
    this.store.dispatch(this.actions.deletePromoCodeItem(this.promoCode, promoCodeItem));
    this.selectedRows = [];
  }

  filter(): void {
  }

  selectRow(promoCodeItem: PromoCodeItem): void {
  }

  selectAllRows(promoCodeItems: PromoCodeItem[]): void {
  }

  showDialog(promoCodeItem: PromoCodeItem): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(PromoCodeItemEditorDialog, config);
    this.editorDialogRef.componentInstance.promoCode = this.promoCode;
    this.editorDialogRef.componentInstance.promoCodeItem = promoCodeItem;
    this.editorDialogRef.afterClosed().subscribe((res) => {
      this.selectedRows = [];
    });
  }
}

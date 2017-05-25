import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {MarketingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {PromoCodeActions} from "../promo-code.action";
import {PromoCodeItem} from "../promo-code-item.interface";
import {PromoCodeItemEditorDialog} from "../dialog/promo-code-item-editor.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef} from "@angular/material";
import {PromoCode} from "../promo-code.interface";

@Component({
  selector: 'pams-promo-code-item-list',
  templateUrl: './promo-code-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PromoCodeItemListComponent implements OnInit {

  @Input() promoCode: PromoCode;
  @Input() promoCodeItems: PromoCodeItem[];

  private selectedRows: PromoCodeItem[];
  private editorDialogRef: MdDialogRef<PromoCodeItemEditorDialog>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'applied', label: 'Applied'},
    {name: 'sourceNo', label: 'SourceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'action', label: ''}
  ];

  constructor(private vcf: ViewContainerRef,
          private store: Store<MarketingModuleState>,
          private actions:PromoCodeActions,
              private dialog: MdDialog) {
  }

  ngOnInit(): void {
      this.selectedRows = this.promoCodeItems.filter(value => value.selected);
  }

  create(): void {
      this.showDialog(null);
  }
  
  edit(promoCodeItem: PromoCodeItem): void {
      this.showDialog(promoCodeItem);
  }

  remove(promoCodeItem: PromoCodeItem): void {
      this.store.dispatch(this.actions.deletePromoCodeItem(this.promoCode, promoCodeItem));
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
    this.editorDialogRef.componentInstance.setPromoCode = this.promoCode;
    this.editorDialogRef.componentInstance.setPromoCodeItem = promoCodeItem;
    this.editorDialogRef.afterClosed().subscribe(res => {
      this.selectedRows = [];
    });
  }
}

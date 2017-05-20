import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {PromoCodeItem} from "../promo-code-item.interface";
import {PromoCodeItemEditorDialog} from "../dialog/promo-code-item-editor.dialog";
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {MarketingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import {PromoCodeActions} from "../promo-code.action";
import {PromoCode} from "../promo-code.interface";
import {Observable} from "rxjs";

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
    //{name: 'applied', label: 'Applied'},
    {name: 'sourceNo', label: 'SourceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'action', label: ''}
  ];
  private PROMO_CODE = "marketingModuleState.promoCode".split(".");
  private promoCode$: Observable<PromoCode>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: PromoCodeActions,
              private store: Store<MarketingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
      this.promoCode$ = this.store.select(...this.PROMO_CODE)
  }

  ngOnInit(): void {
    this.selectedRows = this.promoCodeItems.filter(value => {console.log("selected value",value)}/*value.selected*/);
  }

  createDialog(): void {
    this.promoCode$.subscribe(promoCode => this.showDialog(promoCode,null));
  }

  edit(promoCodeItem: PromoCodeItem): void {
    this.promoCode$.subscribe(promoCode => this.showDialog(promoCode,promoCodeItem));
  }

  delete(): void {
    console.log("length: " + this.selectedRows.length);
    for (var i = 0; i < this.selectedRows.length; i++) {
      this.store.dispatch(this.actions.deletePromoCodeItem(this.promoCode, this.selectedRows[i]));
    }
  }

  filter(): void {
  }

  selectRow(promoCodeItem: PromoCodeItem): void {
  }

  selectAllRows(promoCodeItems: PromoCodeItem[]): void {
  }


  showDialog(promoCode: PromoCode, promoCodeItem: PromoCodeItem): void {
    console.log("showDialog",promoCode);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(PromoCodeItemEditorDialog, config);
    this.editorDialogRef.componentInstance.promoCode = promoCode;
    if (promoCodeItem) this.editorDialogRef.componentInstance.promoCodeItem = promoCodeItem; // set
    this.editorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}

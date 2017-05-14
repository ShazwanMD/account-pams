import { Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnInit, ViewContainerRef } from '@angular/core';
import { ChargeCode } from "../charge-code.interface";
import { Observable } from "rxjs/Observable";
import { Store } from "@ngrx/store";
import { ChargeCodeActions } from "../charge-code.action";
import { AccountModuleState } from "../../index";
import { MdDialogRef, MdDialogConfig, MdDialog } from "@angular/material";
import { ChargeCodeEditorDialog } from "../dialog/charge-code-editor.dialog";
import {ChargeCodeSubModule} from "../index";

@Component( {
    selector: 'pams-charge-code-list',
    templateUrl: './charge-code-list.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
} )
export class ChargeCodeListComponent implements OnInit {

    @Input() chargeCodes: ChargeCode[];
    @Output() view = new EventEmitter<ChargeCode>();
    
    private CHARGE_CODES = "accountModuleState.chargeCodes".split( "." );
    private creatorDialogRef: MdDialogRef<ChargeCodeEditorDialog>;
    private chargeCodes$: Observable<ChargeCode[]>;

    private columns: any[] = [
        { name: 'code', label: 'Code' },
        { name: 'description', label: 'Description' },
        { name: 'priority', label: 'Priority' },
        { name: 'action', label: '' }
    ];

    constructor( private store: Store<AccountModuleState>,
                private actions: ChargeCodeActions,
                private vcf: ViewContainerRef,
                private dialog: MdDialog) {
                this.chargeCodes$ = this.store.select( ...this.CHARGE_CODES );
    }
    
    ngOnInit(): void {
        this.store.dispatch(this.actions.findChargeCodes());
      }
    
    editDialog( code: ChargeCode ): void {
        this.showDialog( code );
    }

    delete( code: ChargeCode ): void {
        this.store.dispatch( this.actions.removeChargeCode( code ) )
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
        this.creatorDialogRef = this.dialog.open(ChargeCodeEditorDialog, config);
        if(code) this.creatorDialogRef.componentInstance.chargeCode = code; // set
        this.creatorDialogRef.afterClosed().subscribe(res => {
          console.log("close dialog");
        });
      }
}

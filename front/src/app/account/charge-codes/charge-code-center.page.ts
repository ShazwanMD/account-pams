import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {ChargeCode} from "./charge-code.interface";
import {ChargeCodeActions} from "./charge-code.action";
import {ChargeCodeState} from "./charge-code.reducer";
import {ChargeCodeListState} from "./charge-code-list.reducer";
import {AccountModuleState} from "../index";

@Component({
    selector: 'pams-charge-code-center',
    templateUrl: './charge-code-center.page.html',
})

export class ChargeCodeCenterPage implements OnInit {

    private CHARGE_CODES = "accountModuleState.chargeCodes".split(".");
    private chargeCodes$: Observable<ChargeCode[]>;

    constructor(private router: Router,
        private route: ActivatedRoute,
        private actions: ChargeCodeActions,
        private store: Store<AccountModuleState>) {
        this.chargeCodes$ = this.store.select(...this.CHARGE_CODES);

    }

    goBack(route: string): void {
        this.router.navigate(['/charge-codes']);
    }

  

 
    ngOnInit(): void {
        console.log("abc")
       this.store.dispatch(this.actions.findChargeCodes());
    }
}


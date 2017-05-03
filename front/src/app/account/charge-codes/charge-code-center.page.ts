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

    private ACCOUNTS = "accountModuleState.accounts".split(".");

    constructor(private router: Router,
        private route: ActivatedRoute,
        private actions: ChargeCodeActions,
        private store: Store<AccountModuleState>) {
    }

    goBack(route: string): void {
        this.router.navigate(['/accounts']);
    }

  

 
    ngOnInit(): void {
       // this.store.dispatch(this.actions.findAccounts());
    }
}


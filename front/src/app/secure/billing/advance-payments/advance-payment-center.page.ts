import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MdDialog, MdDialogConfig, MdDialogRef } from "@angular/material";
import { KnockoffCreatorDialog } from "../knockoffs/dialog/knockoff-creator.dialog";


@Component( {
    selector: 'pams-advance-payment-center',
    templateUrl: './advance-payment-center.page.html',
} )

export class AdvancePaymentCenterPage implements OnInit {

    private creatorDialogRef: MdDialogRef<KnockoffCreatorDialog>;
    constructor( private router: Router,
        private route: ActivatedRoute,
        private vcf: ViewContainerRef,
        private dialog: MdDialog ) {
    }

    ngOnInit(): void {

    }

    showDialog(): void {
        console.log( 'showDialog' );
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '90%';
        config.position = { top: '0px' };
        this.creatorDialogRef = this.dialog.open( KnockoffCreatorDialog, config );
        this.creatorDialogRef.afterClosed().subscribe(( res ) => {
            console.log( 'close dialog' );
            // load something here
        } );
    }
}


import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
//import {Account} from "../account.interface";
import {Actor} from "../../../identity/actor.interface";
//import {ChargeCodeActions} from "./charge-code.action";
import {AccountModuleState} from "../../index";
import {IdentityService} from "../../../../services/identity.service";


@Component({
  selector: 'pams-charge-code-creator',
  templateUrl: './charge-code-creator.dialog.html',
})

export class ChargeCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private formBuilder: FormBuilder,
             // private store: Store<AccountModuleState>,
             // private actions: ChargeCodeActions,
              private dialog: MdDialogRef<ChargeCodeCreatorDialog>) {
  }

  ngOnInit(): void {
   /* this.createForm = this.formBuilder.group(<Account>{
      id: null,
      code: '',
      name:'',
      email:'',
      balanceAmount:0,
      actor:<Actor>{},
    });
  }

  save(account: Account, isValid: boolean) {
    console.log("account: " + account.name);
    this.store.dispatch(this.actions.saveAccount(account));
    this.dialog.close();
  }*/
}
}
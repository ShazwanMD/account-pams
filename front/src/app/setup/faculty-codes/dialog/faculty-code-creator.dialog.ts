import { FacultyCode } from './../../../common/faculty-codes/faculty-code.interface';
import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {SetupModuleState} from "../../index";
import {SetupActions} from "../../setup.action";




@Component({
  selector: 'pams-faculty-code-creator',
  templateUrl: './faculty-code-creator.dialog.html',
})

export class FacultyCodeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<FacultyCodeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions
  ) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group(<FacultyCode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
    });
  }

    save(code: FacultyCode, isValid: boolean) {
    this.store.dispatch(this.actions.saveFacultyCode(code));
    this.dialog.close();
  }

}

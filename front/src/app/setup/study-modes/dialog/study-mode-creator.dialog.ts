import { StudyMode } from './../../../common/study-modes/study-mode.interface';
import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Store} from "@ngrx/store";
import {MdDialogRef} from "@angular/material";
import {SetupModuleState} from "../../index";
import {SetupActions} from "../../setup.action";



@Component({
  selector: 'pams-study-mode-creator',
  templateUrl: './study-mode-creator.dialog.html',
})

export class StudyModeCreatorDialog implements OnInit {

  private createForm: FormGroup;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialogRef<StudyModeCreatorDialog>,
              private store: Store<SetupModuleState>,
              private actions: SetupActions
  ) {
  }

  ngOnInit(): void {

    this.createForm = this.formBuilder.group(<StudyMode>{
      id: null,
      code: '',
      descriptionMs: '',
      descriptionEn: '',
      
     

    });
  }

    save(code: StudyMode, isValid: boolean) {
    this.store.dispatch(this.actions.saveStudyMode(code));
    this.dialog.close();
  }
}
    
    
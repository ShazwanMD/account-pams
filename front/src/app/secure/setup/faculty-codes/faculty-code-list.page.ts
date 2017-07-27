import { FacultyCode } from '../../../shared/model/common/faculty-code.interface';
import { FacultyCodeCreatorDialog } from './dialog/faculty-code-creator.dialog';
import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Store} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {SetupModuleState} from '../index';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'pams-faculty-list-page',
  templateUrl: './faculty-code-list.page.html',
})
export class FacultyCodeListPage implements OnInit {

  private FACULTY_CODES = 'setupModuleState.facultyCodes'.split('.');
  private creatorDialogRef: MdDialogRef<FacultyCodeCreatorDialog>;
  private facultyCodes$: Observable<FacultyCode>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'description', label: 'DescriptionEn'},
    {name: 'action', label: ''},
  ];

  constructor(private actions: SetupActions,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private store: Store<SetupModuleState>){
    this.facultyCodes$ = this.store.select(...this.FACULTY_CODES);
  }

    showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(FacultyCodeCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {

      console.log('close dialog');
      // load something here
    });
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findFacultyCodes());
    this.store.dispatch(this.actions.changeTitle('Faculty Codes'));
  }

}

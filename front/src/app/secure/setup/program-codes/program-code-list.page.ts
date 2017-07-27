import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import { ProgramCodeCreatorDialog } from './dialog/program-code-creator.dialog';
import { ProgramCode } from '../../../shared/model/common/program-code.interface';
import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Store} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {SetupModuleState} from '../index';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'pams-program-list-page',
  templateUrl: './program-code-list.page.html',
})
export class ProgramCodeListPage implements OnInit {

  private PROGRAM_CODES = 'setupModuleState.programCodes'.split('.');
  private creatorDialogRef: MdDialogRef<ProgramCodeCreatorDialog>;
  private programCodes$: Observable<ProgramCode>;
  private columns: any[] = [
    {name: 'code', label: 'Code'},
    {name: 'descriptionMs', label: 'DescriptionMs'},
    {name: 'descriptionEn', label: 'DescriptionEn'},
    {name: 'action', label: ''},
  ];

  constructor(private actions: SetupActions,
              private store: Store<SetupModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog){
    this.programCodes$ = this.store.select(...this.PROGRAM_CODES);
  }

    showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(ProgramCodeCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findProgramCodes());
    this.store.dispatch(this.actions.changeTitle('Program Codes'));
  }

  filter(filter: string): void {
    console.log('filter');
  }

  delete(code: ProgramCode): void {
    console.log('deleting code: ' + code.code);
    console.log('deleting code: ' + code.descriptionEn);
    this.store.dispatch(this.actions.removeProgramCode(code));
  }
}

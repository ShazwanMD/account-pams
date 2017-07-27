import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {WaiverApplicationActions} from '../waiver-application.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationEditorDialog} from '../dialog/waiver-application-editor.dialog';
import { Observable } from 'rxjs/Observable';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';

@Component({
  selector: 'pams-waiver-application-draft-task',
  templateUrl: './waiver-application-draft-task.panel.html',
})

export class WaiverApplicationDraftTaskPanel implements OnInit {

  @Input() waiverApplicationTask: WaiverApplicationTask;

  private WAIVER_APPLICATION: string[] = 'financialaidModuleState.waiverApplication'.split('.');
  private waiverApplication$: Observable<WaiverApplication>;
  private creatorDialogRef: MdDialogRef<WaiverApplicationEditorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverApplicationActions,
              private store: Store<FinancialaidModuleState>,
              private dialog: MdDialog,
              private vcf: ViewContainerRef,
              private snackBar: MdSnackBar) {

      this.waiverApplication$ = this.store.select(...this.WAIVER_APPLICATION);
  }

  ngOnInit(): void {
      //this.store.dispatch(this.actions.findW(this.invoice))
  }

  register() {
    this.store.dispatch(this.actions.completeWaiverApplicationTask(this.waiverApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/financialaid/waiver-applications']);
  }

  showDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '40%';
    config.position = {top: '65px'};
    this.creatorDialogRef = this.dialog.open(WaiverApplicationEditorDialog, config);
    this.creatorDialogRef.componentInstance.application = this.waiverApplicationTask.application;

    // close
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

}

import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';
import { from } from 'rxjs/observable/from';
import { Store } from '@ngrx/store';
import { BillingService } from '../../../../services/billing.service';
import { BillingModuleState } from '../index';
import { KnockoffActions } from './knockoff.action';

@Injectable()
export class KnockoffEffects {

    //private KNOCKOFF: string[] = 'billingModuleState.knockoffs'.split( '.' );
    //private KNOCKOFF_TASK: string[] = 'billingModuleState.knockoffTask'.split('.');

    constructor( private actions$: Actions,
        private knockoffActions: KnockoffActions,
        private billingService: BillingService,
        private store$: Store<BillingModuleState> ) {
    }

    @Effect() findKnockoffs$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFFS )
        .map(( action ) => action.payload )
        .switchMap(() => this.billingService.findKnockoffs() )
        .map(( knockoff ) => this.knockoffActions.findKnockoffsSuccess( knockoff ) );

    @Effect() findKnockoffByReferenceNo$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO )
        .map(( action ) => action.payload )
        .switchMap(( referenceNo ) => this.billingService.findKnockoffByReferenceNo( referenceNo ) )
        .map(( knockoff ) => this.knockoffActions.findKnockoffByReferenceNoSuccess( knockoff ) );

    @Effect() findCompletedKnockoffs$ = this.actions$
        .ofType( KnockoffActions.FIND_COMPLETED_KNOCKOFFS )
        .switchMap(() => this.billingService.findCompletedKnockoffs() )
        .map(( knockoffs ) => this.knockoffActions.findCompletedKnockoffsSuccess( knockoffs ) );

    @Effect() findArchivedknockoffs$ = this.actions$
        .ofType( KnockoffActions.FIND_ARCHIVED_KNOCKOFFS )
        .switchMap(() => this.billingService.findArchivedknockoffs() )
        .map(( knockoffs ) => this.knockoffActions.findArchivedknockoffsSuccess( knockoffs ) );

    @Effect() findAssignedKnockoffTasks$ = this.actions$
    .ofType(KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS)
    .switchMap(() => this.billingService.findAssignedKnockoffTasks())
    .map((knockoffs) => this.knockoffActions.findAssignedKnockoffTasksSuccess(knockoffs));

    
    @Effect() findPooledKnockoffTasks$ = this.actions$
        .ofType( KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS )
        .switchMap(() => this.billingService.findPooledKnockoffTasks() )
        .map(( knockoffs ) => this.knockoffActions.findPooledKnockoffTasksSuccess(knockoffs) );

    @Effect() findKnockoffTaskByTaskId$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID )
        .map(( action ) => action.payload )
        .switchMap(( taskId ) => this.billingService.findKnockoffTaskByTaskId(taskId))
        .map(( task ) => this.knockoffActions.findKnockoffTaskByTaskIdSuccess(task) );
    
    @Effect() startKnockoffTask$ = this.actions$
    .ofType(KnockoffActions.START_KNOCKOFF_TASK)
    .map((action) => action.payload)
    .switchMap((knockoff) => this.billingService.startKnockoffTask(knockoff))
    .map((referenceNo) => this.knockoffActions.startKnockoffTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.knockoffActions.findAssignedKnockoffTasks(),
        this.knockoffActions.findPooledKnockoffTasks(),
      ],
    ));

  @Effect() completeKnockoffTask$ = this.actions$
    .ofType(KnockoffActions.COMPLETE_KNOCKOFF_TASK)
    .map((action) => action.payload)
    .switchMap((knockoffTask) => this.billingService.completeKnockoffTask(knockoffTask))
    .map((message) => this.knockoffActions.completeKnockoffTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.knockoffActions.findAssignedKnockoffTasks(),
        this.knockoffActions.findPooledKnockoffTasks(),
        this.knockoffActions.findArchivedknockoffs(),
      ],
    ));

  @Effect() claimKnockoffTask$ = this.actions$
    .ofType(KnockoffActions.CLAIM_KNOCKOFF_TASK)
    .map((action) => action.payload)
    .switchMap((knockoffTask) => this.billingService.claimKnockoffTask(knockoffTask))
    .map((message) => this.knockoffActions.claimKnockoffTaskSuccess(message))
    .mergeMap((action) => from([action,
                                this.knockoffActions.findAssignedKnockoffTasks(),
                                this.knockoffActions.findPooledKnockoffTasks(),
      ],
    ));

  @Effect() releaseKnockoffTask$ = this.actions$
    .ofType(KnockoffActions.RELEASE_KNOCKOFF_TASK)
    .map((action) => action.payload)
    .switchMap((knockoffTask) => this.billingService.releaseKnockoffTask(knockoffTask))
    .map((message) => this.knockoffActions.releaseKnockoffTaskSuccess(message))
    .mergeMap((action) => from([action,
                                this.knockoffActions.findAssignedKnockoffTasks(),
                                this.knockoffActions.findPooledKnockoffTasks(),
      ],
    ));
}
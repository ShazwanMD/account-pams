import {Pipe, PipeTransform} from '@angular/core';
import {FlowState} from "./flow-state.enum";

@Pipe({name: 'flowStatePipe'})
export class FlowStatePipe implements PipeTransform {

    transform(value: FlowState): any {
        if (!value) return value;
        switch (FlowState[value.toString()]) {
            case FlowState.DRAFTED : {
                return 'New';
            }
            case FlowState.REGISTERED : {
                return 'Preparer';
            }
            case FlowState.VERIFIED : {
                return 'Reviewver';
            }
            case FlowState.APPROVED : {
                return 'Posted';
            }
            case FlowState.COMPLETED : {
                return 'Final';
            }
            default: {
                return value;
            }
        }
    }
}
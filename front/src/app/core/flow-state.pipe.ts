import {Pipe, PipeTransform} from '@angular/core';
import {FlowState} from './flow-state.enum';

@Pipe({name: 'flowStatePipe'})
export class FlowStatePipe implements PipeTransform {

  transform(value: FlowState): any {
    if (!value) {
      return value;
    }
    switch (FlowState[value.toString()]) {
      case FlowState.DRAFTED : {
        return 'NEW';
      }
      case FlowState.REGISTERED : {
        return 'PREPARER';
      }
      case FlowState.VERIFIED : {
        return 'REVIEWER';
      }
      case FlowState.APPROVED : {
        return 'POSTED';
      }
      case FlowState.COMPLETED : {
        return 'FINAL';
      }
      default: {
        return value;
      }
    }
  }
}

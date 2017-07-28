import {NgModule} from '@angular/core';
import {FlowStatePipe} from './core/flow-state.pipe';

@NgModule({
  imports: [],
  declarations: [
    FlowStatePipe,

  ],
  exports: [
    FlowStatePipe,

  ],
})

export class PipeModule {

  static forRoot() {
    return {
      ngModule: PipeModule,
      providers: [],
    };
  }
}

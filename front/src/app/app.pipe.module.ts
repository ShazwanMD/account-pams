import { NgModule }      from '@angular/core';
import {FlowStatePipe} from "./core/flow-state.pipe";
// import {AssessmentCategoryPipe} from "./term/assessments/assessment-category.pipe";
// import {AssessmentTypePipe} from "./term/assessments/assessment-type.pipe";
// import {AdmissionStatusPipe} from "./term/admissions/admission-status.pipe";
// import {AcademicStandingPipe} from "./term/admissions/academic-standing.pipe";

@NgModule({
    imports:        [],
    declarations:   [
        FlowStatePipe,

        // AcademicStandingPipe,
        // AdmissionStatusPipe,
        // AssessmentCategoryPipe,
        // AssessmentTypePipe
    ],
    exports:        [
        FlowStatePipe,

        // AcademicStandingPipe,
        // AdmissionStatusPipe,
        // AssessmentCategoryPipe,
        // AssessmentTypePipe
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
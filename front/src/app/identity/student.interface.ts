import { CohortCode } from "../common/cohort-codes/cohort-code.interface";
import { ResidencyCode } from "../common/residency-codes/residency-code.interface";
import { Actor } from "./actor.interface";
export interface Student extends Actor {
    id:number;
    cohortCode: CohortCode;
    residencyCode?: ResidencyCode;
}

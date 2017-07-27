import {CohortCode} from '../common/cohort-code.interface';
import {ResidencyCode} from '../common/residency-code.interface';
import {Actor} from './actor.interface';
export interface Student extends Actor {
  id: number;
  cohortCode: CohortCode;
  residencyCode?: ResidencyCode;
}

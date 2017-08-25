import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from './charge-code.interface';
import {AcademicSession} from './academic-session.interface';
import { StudyMode } from "../common/study-mode.interface";
import { Actor } from "../identity/actor.interface";
import { Sponsor } from "../identity/sponsor.interface";
export interface AccountSponsorship extends MetaObject {

  referenceNo: string;
  accountNo: string;
  description: string;
  amount: number;
  session: AcademicSession;
  startDate: Date;
  endDate: Date;
  sponsor: Sponsor;


  // admission
  studyMode?: StudyMode;
  ordinal: number;

  // selection
  selected?: boolean;
}

import {AcademicSession} from '../account/academic-session.interface';
export interface Settlement {
  referenceNo: string;
  sourceNo: string;
  description: string;
  executed: boolean;
  academicSession: AcademicSession;
}

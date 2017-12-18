import {Account} from '../account/account.interface';
import {MetaObject} from '../../../core/meta-object.interface';
export interface DateRange extends MetaObject {
    Start_date: Date;
    End_date: Date;
}

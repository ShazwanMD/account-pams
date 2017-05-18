import {MetaObject} from "../../core/meta-object.interface";
export interface BankCode extends MetaObject{
    code:string;
    name:string;
    swiftCode:string;
    ibgCode:string;


}

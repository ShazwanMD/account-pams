import {MetaObject} from "../../core/meta-object.interface";
export interface BankCode extends MetaObject{
    code:string;
    swiftCode:string;
    ibgCode:string;
    name:string;
    descriptionEn:string;
    descriptionMs:string;
}

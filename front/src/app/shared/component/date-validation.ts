import {AbstractControl} from '@angular/forms';
export class DateValidation {

    static CheckDate(AC: AbstractControl) {
       let startDate = AC.get('startDate').value; // to get value in input tag
       let endDate = AC.get('endDate').value; // to get value in input tag
        if(endDate <= startDate) {
            console.log('false');
            AC.get('endDate').setErrors( {CheckDate: true} )
        } else {
            console.log('true');
            return null
        }
    }
}
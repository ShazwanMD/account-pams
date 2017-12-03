import {AbstractControl} from '@angular/forms';
export class AmountValidation {
    static CheckAmount(AC: AbstractControl) {
       let totalPayment = AC.get ('totalPaid').value;
       let appliedAmount = AC.get('appliedAmount').value; // to get value in input tag
       let dueAmount = AC.get('dueAmount').value; // to get value in input tag
        if(appliedAmount > dueAmount || appliedAmount > totalPayment ) {
            console.log('false');
            AC.get('appliedAmount').setErrors( {CheckAmount: true} )
        } else {
            console.log('true');
            return null
        }
    }

    static CheckAmountWaiver(AC: AbstractControl) {
        // let waivedAmount = AC.get ('waivedAmount').value;
        let appliedAmount = AC.get('appliedAmount').value; // to get value in input tag
        let dueAmount = AC.get('dueAmount').value; // to get value in input tag
        // console.log('waivedAmount :'+waivedAmount);
        console.log('dueAmount :'+dueAmount);
        console.log('appliedAmount :'+appliedAmount);
         if(appliedAmount > dueAmount) {
             console.log('false');
             AC.get('appliedAmount').setErrors( {CheckAmount: true} )
         } else {
             console.log('true');
             return null
         }
     }
}
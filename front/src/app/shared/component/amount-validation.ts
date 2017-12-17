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

    static CheckAmount2(AC: AbstractControl) {
        let balanceAmount = AC.get('balanceAmount').value; // to get value in input tag
        let amount = AC.get('amount').value; // to get value in input tag
        console.log('amount :'+amount);
        console.log('balanceAmount :'+balanceAmount);
         if(balanceAmount > amount) {
             console.log('false');
             AC.get('balanceAmount').setErrors( {CheckAmount2: true} )
         } else {
             console.log('true');
             return null
         }
     }
}
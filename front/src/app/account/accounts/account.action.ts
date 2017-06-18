import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class AccountActions {

  static FIND_ACCOUNTS = '[Account] Find Accounts';
  findAccounts(): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS
    };
  }

  static FIND_ACCOUNTS_SUCCESS = '[Account] Find Accounts Success';
  findAccountsSuccess(accounts): Action {
    console.log("findAccountsSuccess");
    console.log("accounts: " + accounts.length);
    return {
      type: AccountActions.FIND_ACCOUNTS_SUCCESS,
      payload: accounts
    };
  }

  static FIND_ACCOUNTS_BY_FILTER = '[Account] Find Accounts By Filter';
  findAccountsByFilter(filter): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_FILTER,
      payload:filter
    };
  }

  static FIND_ACCOUNTS_BY_FILTER_SUCCESS = '[Account] Find Accounts By FilterSuccess';
  findAccountsByFilterSuccess(accounts): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_FILTER_SUCCESS,
      payload: accounts
    };
  }
  
  static FIND_ACCOUNTS_BY_ACTOR = '[Account] Find Accounts By Actor Student';
  findAccountsByActor(): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_ACTOR,
    };
  }

  static FIND_ACCOUNTS_BY_ACTOR_SUCCESS = '[Account] Find Accounts By Actor Student Success';
  findAccountsByActorSuccess(accounts): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_ACTOR_SUCCESS,
      payload: accounts
    };
  }
  
  static FIND_ACCOUNTS_BY_ACTOR_SPONSOR = '[Account] Find Accounts By Actor Sponsor';
  findAccountsByActorSponsor(): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_ACTOR_SPONSOR,
    };
  }

  static FIND_ACCOUNTS_BY_ACTOR_SPONSOR_SUCCESS = '[Account] Find Accounts By Actor Sponsor Success';
  findAccountsByActorSponsorSuccess(accounts): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_ACTOR_SPONSOR_SUCCESS,
      payload: accounts
    };
  }

  static FIND_ACCOUNTS_BY_ACTOR_STAFF = '[Account] Find Accounts By Actor Staff';
  findAccountsByActorStaff(): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_ACTOR_STAFF,
    };
  }

  static FIND_ACCOUNTS_BY_ACTOR_STAFF_SUCCESS = '[Account] Find Accounts By Actor Staff Success';
  findAccountsByActorStaffSuccess(accounts): Action {
    return {
      type: AccountActions.FIND_ACCOUNTS_BY_ACTOR_STAFF_SUCCESS,
      payload: accounts
    };
  }
  
  static FIND_ACCOUNT_BY_CODE = '[Account] Find Account By Code';
  findAccountByCode(code): Action {
    return {
      type: AccountActions.FIND_ACCOUNT_BY_CODE,
      payload: code
    };
  }

  static FIND_ACCOUNT_BY_CODE_SUCCESS = '[Account] Find Account By Code Success';
  findAccountByCodeSuccess(account): Action {
    console.log("findAccountSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_BY_CODE_SUCCESS,
      payload: account
    };
  }

  static FIND_ACCOUNT_TRANSACTIONS = '[Account] Find Account Transactions';
  findAccountTransactions(account): Action {
    console.log("findAccountTransactions");
    return {
      type: AccountActions.FIND_ACCOUNT_TRANSACTIONS,
      payload: account
    };
  }

  static FIND_ACCOUNT_TRANSACTIONS_SUCCESS = '[Account] Find Account Transactions Success';
  findAccountTransactionsSuccess(accountTransactions): Action {
    console.log("findAccountTransactionsSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_TRANSACTIONS_SUCCESS,
      payload: accountTransactions
    };
  }

  static FIND_ACCOUNT_CHARGES = '[Account] Find Account Charges';
  findAccountCharges(account): Action {
    console.log("findAccountCharges");
    return {
      type: AccountActions.FIND_ACCOUNT_CHARGES,
      payload: account
    };
  }

    static FIND_COMPOUND_CHARGES = '[Account] Find Compound Charges';
  findCompoundCharges(account): Action {
    console.log("findCompoundCharges");
    return {
      type: AccountActions.FIND_COMPOUND_CHARGES,
      payload: account
    };
  }

    static FIND_COMPOUND_CHARGES_SUCCESS = '[Account] Find Compound Charges Success';
  findCompoundChargesSuccess(accountCharges): Action {
    console.log("findCompoundChargesSuccess");
    return {
      type: AccountActions.FIND_COMPOUND_CHARGES_SUCCESS,
      payload: accountCharges
    };
  }

  static FIND_ACCOUNT_CHARGES_SUCCESS = '[Account] Find Account Charges Success';
  findAccountChargesSuccess(accountCharges): Action {
    console.log("findAccountChargesSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_CHARGES_SUCCESS,
      payload: accountCharges
    };
  }


  static FIND_ACCOUNT_WAIVERS = '[Account] Find Account Waivers';
  findAccountWaivers(account): Action {
    console.log("findAccountWaivers: " + account.code);
    return {
      type: AccountActions.FIND_ACCOUNT_WAIVERS,
      payload: account
    };
  }

  static FIND_ACCOUNT_WAIVERS_SUCCESS = '[Account] Find Account Waivers Success';
  findAccountWaiversSuccess(accountWaivers): Action {
    console.log("findAccountWaiversSuccess: " + accountWaivers.id);
    return {
      type: AccountActions.FIND_ACCOUNT_WAIVERS_SUCCESS,
      payload: accountWaivers
    };
  }

  static SAVE_ACCOUNT = '[Account] Save Account';
  saveAccount(account): Action {
    return {
      type: AccountActions.SAVE_ACCOUNT,
      payload: account
    };
  }

  static SAVE_ACCOUNT_SUCCESS = '[Account] Save Account Success';
  saveAccountSuccess(account): Action {
    return {
      type: AccountActions.SAVE_ACCOUNT_SUCCESS,
      payload: account
    };
  }

  static UPDATE_ACCOUNT = '[Account] Update Account';
  updateAccount(account): Action {
    return {
      type: AccountActions.UPDATE_ACCOUNT,
      payload: account
    };
  }

  static UPDATE_ACCOUNT_SUCCESS = '[Account] Update Account Success';
  updateAccountSuccess(account): Action {
    return {
      type: AccountActions.UPDATE_ACCOUNT_SUCCESS,
      payload: account
    };
  }

  static CREATE_ACCOUNT = '[Account] Create Account';
  createAccount(account): Action {
    return {
      type: AccountActions.CREATE_ACCOUNT,
      payload: account
    };
  }

  static CREATE_ACCOUNT_SUCCESS = '[Account] Create Account Success';
  createAccountSuccess(account): Action {
    return {
      type: AccountActions.CREATE_ACCOUNT_SUCCESS,
      payload: account
    };
  }

  static REMOVE_ACCOUNT = '[Account] Remove Account';
  removeAccount(account): Action {
    return {
      type: AccountActions.REMOVE_ACCOUNT,
      payload: account
    };
  }

  static REMOVE_ACCOUNT_SUCCESS = '[Account] Remove Account Success';
  removeAccountSuccess(account): Action {
    return {
      type: AccountActions.REMOVE_ACCOUNT_SUCCESS,
      payload: account
    };
  }

  static ADD_ACCOUNT_CHARGE = '[Account] Add AccountCharge ';

  addAccountCharge(account, charge): Action {
    return {
      type: AccountActions.ADD_ACCOUNT_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static ADD_ACCOUNT_CHARGE_SUCCESS = '[Account] Add AccountCharge  Success';

  addAccountChargeSuccess(message): Action {
    return {
      type: AccountActions.ADD_ACCOUNT_CHARGE_SUCCESS,
      payload: message
    };
  }

  static ADD_ADMISSION_CHARGE = '[Admission] Add AdmissionCharge ';

  addAdmissionCharge(account, charge): Action {
    return {
      type: AccountActions.ADD_ADMISSION_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static ADD_ADMISSION_CHARGE_SUCCESS = '[Admission] Add AdmissionCharge  Success';

  addAdmissionChargeSuccess(message): Action {
    return {
      type: AccountActions.ADD_ADMISSION_CHARGE_SUCCESS,
      payload: message
    };
  }

  static UPDATE_ADMISSION_CHARGE = '[Admission] Update AdmissionCharge';

  updateAdmissionCharge(account, charge): Action {
    console.log("updateAdmissionCharge");
    return {
      type: AccountActions.UPDATE_ADMISSION_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static UPDATE_ADMISSION_CHARGE_SUCCESS = '[Admission] Update AdmissionCharge  Success';

  updateAdmissionChargeSuccess(message): Action {
      console.log("updateAdmissionChargeSuccess");
    return {
      type: AccountActions.UPDATE_ADMISSION_CHARGE,
      payload: message
    };
  }

   static REMOVE_ADMISSION_CHARGE = '[Admission] Remove AdmissionCharge';

  removeAdmissionCharge(account, charge): Action {
    console.log('remove admissionCharge');
    return {
      type: AccountActions.REMOVE_ADMISSION_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static REMOVE_ADMISSION_CHARGE_SUCCESS = '[Admission] Remove AdmissionCharge Success';

  removeAdmissionChargeSuccess(message): Action {
    console.log('remove admissionCharge SUCCESS');
    return {
      type: AccountActions.REMOVE_ADMISSION_CHARGE_SUCCESS,
      payload: message
    };
  }

  static ADD_ACCOUNT_TRANSACTION_ = '[Account] Add AccountTransaction ';

  addAccountTransaction(account, accountTransaction): Action {
    return {
      type: AccountActions.ADD_ACCOUNT_TRANSACTION_,
      payload: {account:account, accountTransaction:accountTransaction}
    };
  }

  static ADD_ACCOUNT_TRANSACTION_SUCCESS = '[Account] Add AccountTransaction Success';

  addAccountTransactionSuccess(message): Action {
    return {
      type: AccountActions.ADD_ACCOUNT_TRANSACTION_SUCCESS,
      payload: message
    };
  }

  static ADD_COMPOUND_CHARGE = '[Compound] Add CompoundCharge ';

  addCompoundCharge(account, charge): Action {
    return {
      type: AccountActions.ADD_COMPOUND_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

static ADD_COMPOUND_CHARGE_SUCCESS = '[Compound] Add CompoundCharge  Success';

  addCompoundChargeSuccess(message): Action {
    return {
      type: AccountActions.ADD_COMPOUND_CHARGE_SUCCESS,
      payload: message
    };
  }
  
 static UPDATE_COMPOUND_CHARGE = '[Compound] Update CompoundCharge';

  updateCompoundCharge(account, charge): Action {
    console.log("updateAdmissionCharge");
    return {
      type: AccountActions.UPDATE_COMPOUND_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static UPDATE_COMPOUND_CHARGE_SUCCESS = '[Compound] Update CompoundCharge  Success';

  updateCompoundChargeSuccess(message): Action {
      console.log("updateCompoundChargeSuccess");
    return {
      type: AccountActions.UPDATE_COMPOUND_CHARGE,
      payload: message
    };
  }
  
   static REMOVE_COMPOUND_CHARGE = '[Compound] Remove CompoundCharge';

  removeCompoundCharge(account, charge): Action {
    console.log('remove CompoundCharge');
    return {
      type: AccountActions.REMOVE_COMPOUND_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static REMOVE_COMPOUND_CHARGE_SUCCESS = '[Compound] Remove CompoundCharge Success';

  removeCompoundChargeSuccess(message): Action {
    console.log('remove CompoundCharge SUCCESS');
    return {
      type: AccountActions.REMOVE_COMPOUND_CHARGE_SUCCESS,
      payload: message
    };
  }
}

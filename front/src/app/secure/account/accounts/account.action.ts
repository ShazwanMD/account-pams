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
      console.log("findAccountsByActorSuccess");
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
      console.log("findAccountsByActorSponsorSuccess");
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

  static FIND_SECURITY_ACCOUNT_CHARGES = '[Account] Find Security Account Charges';
  findSecurityAccountCharges(account): Action {
    console.log("findSecurityAccountCharges");
    return {
      type: AccountActions.FIND_SECURITY_ACCOUNT_CHARGES,
      payload: account
    };
  }

  static FIND_SECURITY_ACCOUNT_CHARGES_SUCCESS = '[Account] Find Security Account Charges Success';
  findSecurityAccountChargesSuccess(accountCharges): Action {
    console.log("findSecurityAccountChargesSuccess");
    return {
      type: AccountActions.FIND_SECURITY_ACCOUNT_CHARGES_SUCCESS,
      payload: accountCharges
    };
  }

  static FIND_ADMISSION_ACCOUNT_CHARGES = '[Account] Find Admission Account Charges';
  findAdmissionAccountCharges(account): Action {
    console.log("findAdmissionAccountCharges");
    return {
      type: AccountActions.FIND_ADMISSION_ACCOUNT_CHARGES,
      payload: account
    };
  }

  static FIND_ADMISSION_ACCOUNT_CHARGES_SUCCESS = '[Account] Find Admission Account Charges Success';
  findAdmissionAccountChargesSuccess(accountCharges): Action {
    console.log("findAdmissionAccountChargesSuccess");
    return {
      type: AccountActions.FIND_ADMISSION_ACCOUNT_CHARGES_SUCCESS,
      payload: accountCharges
    };
  }

  static FIND_LOAN_ACCOUNT_CHARGES = '[Account] Find Loan Account Charges';
  findLoanAccountCharges(account): Action {
    console.log("findLoanAccountCharges");
    return {
      type: AccountActions.FIND_LOAN_ACCOUNT_CHARGES,
      payload: account
    };
  }

  static FIND_LOAN_ACCOUNT_CHARGES_SUCCESS = '[Account] Find Loan Account Charges Success';
  findLoanAccountChargesSuccess(accountCharges): Action {
    console.log("findLoanAccountChargesSuccess");
    return {
      type: AccountActions.FIND_LOAN_ACCOUNT_CHARGES_SUCCESS,
      payload: accountCharges
    };
  }

  static FIND_STUDENT_AFFAIRS_ACCOUNT_CHARGES = '[Account] Find Student Affairs Account Charges';
  findStudentAffairsAccountCharges(account): Action {
    console.log("findStudentAffairsAccountCharges");
    return {
      type: AccountActions.FIND_STUDENT_AFFAIRS_ACCOUNT_CHARGES,
      payload: account
    };
  }

  static FIND_STUDENT_AFFAIRS_ACCOUNT_CHARGES_SUCCESS = '[Account] Find Student Affairs Account Charges Success';
  findStudentAffairsAccountChargesSuccess(accountCharges): Action {
    console.log("findStudentAffairsAccountChargesSuccess");
    return {
      type: AccountActions.FIND_STUDENT_AFFAIRS_ACCOUNT_CHARGES_SUCCESS,
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
  
  static FIND_INVOICES_BY_ACCOUNT = '[Account] Find Invoices By account';
  findInvoicesByAccount(account): Action {
    return {
      type: AccountActions.FIND_INVOICES_BY_ACCOUNT,
      payload: account
    };
  }

  static FIND_INVOICES_BY_ACCOUNT_SUCCESS = '[Account] Find Accounts By Actor Student Success';
  findInvoicesByAccountSuccess(accounts): Action {
      console.log("findAccountsByActorSuccess");
    return {
      type: AccountActions.FIND_INVOICES_BY_ACCOUNT_SUCCESS,
      payload: accounts
    };
  }
  
  static FIND_ACCOUNT_ACTIVITIES = '[Account] Find Account Activities';
  findAccountActivities(account): Action {
    console.log("findAccountActivities");
    return {
      type: AccountActions.FIND_ACCOUNT_ACTIVITIES,
      payload: account
    };
  }

  static FIND_ACCOUNT_ACTIVITIES_SUCCESS = '[Account] Find Account Activities Success';
  findAccountActivitiesSuccess(account): Action {
    console.log("findAccountTransactionsSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_ACTIVITIES_SUCCESS,
      payload: account
    };
  }
  
  static FIND_ACCOUNT_ACTIVITIES_BY_SESSION = '[Account] Find Account Activities';
  findAccountActivitiesByAcademicSession(account): Action {
    console.log("findAccountActivitiesByAcademicSession");
    return {
      type: AccountActions.FIND_ACCOUNT_ACTIVITIES_BY_SESSION,
      payload: account
    };
  }

  static FIND_ACCOUNT_ACTIVITIES_BY_SESSION_SUCCESS = '[Account] Find Account Activities Success';
  findAccountActivitiesByAcademicSessionSuccess(account): Action {
    console.log("findAccountActivitiesByAcademicSessionSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_ACTIVITIES_BY_SESSION_SUCCESS,
      payload: account
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

  static UPDATE_ACCOUNT_CHARGE = '[Account] Update AccountCharge';

  updateAccountCharge(account, charge): Action {
    console.log("updateAccountCharge");
    return {
      type: AccountActions.UPDATE_ACCOUNT_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static UPDATE_ACCOUNT_CHARGE_SUCCESS = '[Account] Update AccountCharge  Success';

  updateAccountChargeSuccess(message): Action {
      console.log("updateAccountChargeSuccess");
    return {
      type: AccountActions.UPDATE_ACCOUNT_CHARGE,
      payload: message
    };
  }

   static REMOVE_ACCOUNT_CHARGE = '[Account] Remove AccountCharge';

  removeAccountCharge(account, charge): Action {
    console.log('remove accountCharge');
    return {
      type: AccountActions.REMOVE_ACCOUNT_CHARGE,
      payload: {account:account, charge:charge}
    };
  }

  static REMOVE_ACCOUNT_CHARGE_SUCCESS = '[Account] Remove AccountCharge Success';

  removeAccountChargeSuccess(message): Action {
    console.log('remove accountCharge SUCCESS');
    return {
      type: AccountActions.REMOVE_ACCOUNT_CHARGE_SUCCESS,
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
}

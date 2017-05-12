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

  static FIND_ACCOUNT = '[Account] Find Account';
  findAccount(code): Action {
    return {
      type: AccountActions.FIND_ACCOUNT,
      payload: code
    };
  }

  static FIND_ACCOUNT_SUCCESS = '[Account] Find Account Success';
  findAccountSuccess(account): Action {
    console.log("findAccountSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_SUCCESS,
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

  static FIND_ACCOUNT_CHARGES_SUCCESS = '[Account] Find Account Charges Success';
  findAccountChargesSuccess(accountCharges): Action {
    console.log("findAccountChargesSuccess");
    return {
      type: AccountActions.FIND_ACCOUNT_CHARGES_SUCCESS,
      payload: accountCharges
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

  static RESET_ACCOUNT = '[Account] Reset Account';
  resetAccount(account): Action {
    return {
      type: AccountActions.RESET_ACCOUNT,
      payload: account
    };
  }

  static RESET_ACCOUNT_SUCCESS = '[Account] Reset Account Success';
  resetAccountSuccess(account): Action {
    return {
      type: AccountActions.RESET_ACCOUNT_SUCCESS,
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

  static ADD_ACCOUNT_TRANSACTION_ = '[Account] Add AccountTransaction ';

  addAccountTransaction(account, accountTransaction): Action {
    return {
      type: AccountActions.ADD_ACCOUNT_TRANSACTION_,
      payload: {account:account, accountTransaction:accountTransaction}
    };
  }

  static ADD_ACCOUNT_TRANSACTION_SUCCESS = '[Account] Add AccountTransaction  Success';

  addAccountTransactionSuccess(message): Action {
    return {
      type: AccountActions.ADD_ACCOUNT_TRANSACTION_SUCCESS,
      payload: message
    };
  }
}

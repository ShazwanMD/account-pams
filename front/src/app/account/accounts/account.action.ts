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

  static FIND_ACCOUNT = '[Account] Find Account';
  findAccount(code): Action {
    return {
      type: AccountActions.FIND_ACCOUNT,
      payload: code
    };
  }

  static FIND_ACCOUNT_SUCCESS = '[Account] Find Account Success';
  getAccountSuccess(account): Action {
    return {
      type: AccountActions.FIND_ACCOUNT_SUCCESS,
      payload: account
    };
  }

  static RESET_ACCOUNT = '[Account] Reset Blank Account';
  resetAccount(): Action {
    return {
      type: AccountActions.RESET_ACCOUNT
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
}

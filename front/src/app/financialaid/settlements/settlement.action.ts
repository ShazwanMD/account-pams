import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class SettlementActions {

  static INIT_SETTLEMENT = '[Settlement] Init Settlement';
  initSettlement(settlement): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT,
      payload: settlement
    };
  }

  static INIT_SETTLEMENT_SUCCESS = '[Settlement] Init Settlement Success';
  initSettlementSuccess(referenceNo): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_SUCCESS,
      payload: referenceNo
    };
  }

  static INIT_SETTLEMENT_BY_SPONSOR = '[Settlement] Init Settlement By Sponsor';
  initSettlementBySponsor(settlement, sponsor): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_SPONSOR,
      payload: {settlement:settlement, sponsor:sponsor}
    };
  }

  static INIT_SETTLEMENT_BY_SPONSOR_SUCCESS = '[Settlement] Init Settlement By Sponsor Success';
  initSettlementBySponsorSuccess(referenceNo): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_SPONSOR_SUCCESS,
      payload: referenceNo
    };
  }

  static INIT_SETTLEMENT_BY_FACULTY_CODE = '[Settlement] Init Settlement By FacultyCode';
  initSettlementByFacultyCode(settlement, facultyCode): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_FACULTY_CODE,
      payload: {settlement:settlement, facultyCode:facultyCode}
    };
  }

  static INIT_SETTLEMENT_BY_FACULTY_CODE_SUCCESS = '[Settlement] Init Settlement By Faculty Code Success';
  initSettlementByFacultyCodeSuccess(referenceNo): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_FACULTY_CODE_SUCCESS,
      payload: referenceNo
    };
  }

  static INIT_SETTLEMENT_BY_COHORT_CODE = '[Settlement] Init Settlement By CohortCode';
  initSettlementByCohortCode(settlement, cohortCode): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_COHORT_CODE,
      payload: {settlement:settlement, cohortCode:cohortCode}
    };
  }

  static INIT_SETTLEMENT_BY_COHORT_CODE_SUCCESS = '[Settlement] Init Settlement By Cohort Code Success';
  initSettlementByCohortCodeSuccess(referenceNo): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_COHORT_CODE_SUCCESS,
      payload: referenceNo
    };
  }

  static INIT_SETTLEMENT_BY_ACADEMIC_SESSION = '[Settlement] Init Settlement By AcademicSession';
  initSettlementByAcademicSession(settlementCreator): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_ACADEMIC_SESSION,
      payload: settlementCreator
    };
  }

  static INIT_SETTLEMENT_BY_ACADEMIC_SESSION_SUCCESS = '[Settlement] Init Settlement By Academic Session Success';
  initSettlementByAcademicSessionSuccess(referenceNo): Action {
    return {
      type: SettlementActions.INIT_SETTLEMENT_BY_ACADEMIC_SESSION_SUCCESS,
      payload: referenceNo
    };
  }

  static PROCESS_SETTLEMENT = '[Settlement] Process Settlement';
  processSettlementTask(settlement): Action {
    return {
      type: SettlementActions.PROCESS_SETTLEMENT,
      payload: settlement
    };
  }

  static PROCESS_SETTLEMENT_SUCCESS = '[Settlement] Process Settlement Success';
  processSettlementTaskSuccess(task): Action {
    return {
      type: SettlementActions.PROCESS_SETTLEMENT_SUCCESS,
      payload: task
    };
  }

  static FIND_SETTLEMENT_BY_ID = '[Settlement] Find Settlement By Id';
  findSettlementById(id): Action {
    return {
      type: SettlementActions.FIND_SETTLEMENT_BY_ID,
      payload: id
    };
  }

  static FIND_SETTLEMENT_BY_ID_SUCCESS = '[Settlement] Find Settlement By Id Success';
  findSettlementByIdSuccess(settlement): Action {
    return {
      type: SettlementActions.FIND_SETTLEMENT_BY_ID_SUCCESS,
      payload: settlement
    };
  }

  static FIND_SETTLEMENT_BY_REFERENCE_NO = '[Settlement] Find Settlement By Reference No';
  findSettlementByReferenceNo(referenceNo): Action {
    return {
      type: SettlementActions.FIND_SETTLEMENT_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_SETTLEMENT_BY_REFERENCE_NO_SUCCESS = '[Settlement] Find Settlement By Reference No Success';
  findSettlementByReferenceNoSuccess(settlement): Action {
    return {
      type: SettlementActions.FIND_SETTLEMENT_BY_REFERENCE_NO_SUCCESS,
      payload: settlement
    };
  }

  static FIND_SETTLEMENTS = '[Settlement] Find Settlements ';
  findSettlements(): Action {
    return {
      type: SettlementActions.FIND_SETTLEMENTS,
    };
  }

  static FIND_SETTLEMENTS_SUCCESS = '[Settlement] Find Settlements Success';
  findSettlementsSuccess(settlements): Action {
    console.log("findSettlementsSuccess");
    return {
      type: SettlementActions.FIND_SETTLEMENTS_SUCCESS,
      payload: settlements
    };
  }

  static FIND_SETTLEMENT_ITEMS = '[Settlement] Find Settlement Items';
  findSettlementItems(settlement): Action {
    return {
      type: SettlementActions.FIND_SETTLEMENT_ITEMS,
      payload: settlement
    };
  }

  static FIND_SETTLEMENT_ITEMS_SUCCESS = '[Settlement] Find Settlement Items Success';
  findSettlementItemsSuccess(items): Action {
    console.log("findSettlementTransactionsSuccess");
    return {
      type: SettlementActions.FIND_SETTLEMENT_ITEMS_SUCCESS,
      payload: items
    };
  }


  static UPDATE_SETTLEMENT = '[Settlement] Update Settlement';
  updateSettlement(settlement): Action {
    return {
      type: SettlementActions.UPDATE_SETTLEMENT,
      payload: settlement
    };
  }

  static UPDATE_SETTLEMENT_SUCCESS = '[Settlement] Update Settlement Success';
  updateSettlementSuccess(settlement): Action {
    return {
      type: SettlementActions.UPDATE_SETTLEMENT_SUCCESS,
      payload: settlement
    };
  }

  static REMOVE_SETTLEMENT = '[Settlement] Remove Settlement';
  removeSettlement(settlement): Action {
    return {
      type: SettlementActions.REMOVE_SETTLEMENT,
      payload: settlement
    };
  }

  static REMOVE_SETTLEMENT_SUCCESS = '[Settlement] Remove Settlement Success';
  removeSettlementSuccess(settlement): Action {
    return {
      type: SettlementActions.REMOVE_SETTLEMENT_SUCCESS,
      payload: settlement
    };
  }
}

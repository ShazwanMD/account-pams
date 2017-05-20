import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class PromoCodeActions {

  static INIT_PROMO_CODE = '[PromoCode] Init PromoCode';
  initPromoCode(promoCode): Action {
    return {
      type: PromoCodeActions.INIT_PROMO_CODE,
      payload: promoCode
    };
  }

  static INIT_PROMO_CODE_SUCCESS = '[PromoCode] Init PromoCode Success';
  initPromoCodeSuccess(referenceNo): Action {
    return {
      type: PromoCodeActions.INIT_PROMO_CODE_SUCCESS,
      payload: referenceNo
    };
  }

  static FIND_PROMO_CODE_BY_ID = '[PromoCode] Find PromoCode By Id';
  findPromoCodeById(id): Action {
    return {
      type: PromoCodeActions.FIND_PROMO_CODE_BY_ID,
      payload: id
    };
  }

  static FIND_PROMO_CODE_BY_ID_SUCCESS = '[PromoCode] Find PromoCode By Id Success';
  findPromoCodeByIdSuccess(promoCode): Action {
    return {
      type: PromoCodeActions.FIND_PROMO_CODE_BY_ID_SUCCESS,
      payload: promoCode
    };
  }

  static FIND_PROMO_CODE_BY_REFERENCE_NO = '[PromoCode] Find PromoCode By Reference No';
  findPromoCodeByReferenceNo(referenceNo): Action {
    return {
      type: PromoCodeActions.FIND_PROMO_CODE_BY_REFERENCE_NO,
      payload: referenceNo
    };
  }

  static FIND_PROMO_CODE_BY_REFERENCE_NO_SUCCESS = '[PromoCode] Find PromoCode By Reference No Success';
  findPromoCodeByReferenceNoSuccess(promoCode): Action {
    return {
      type: PromoCodeActions.FIND_PROMO_CODE_BY_REFERENCE_NO_SUCCESS,
      payload: promoCode
    };
  }

  static FIND_PROMO_CODES = '[PromoCode] Find PromoCodes ';
  findPromoCodes(): Action {
    return {
      type: PromoCodeActions.FIND_PROMO_CODES,
    };
  }

  static FIND_PROMO_CODES_SUCCESS = '[PromoCode] Find PromoCodes Success';
  findPromoCodesSuccess(promoCodes): Action {
    console.log("findPromoCodesSuccess");
    return {
      type: PromoCodeActions.FIND_PROMO_CODES_SUCCESS,
      payload: promoCodes
    };
  }

  static FIND_PROMO_CODE_ITEMS = '[PromoCode] Find PromoCode Items';
  findPromoCodeItems(promoCode): Action {
    return {
      type: PromoCodeActions.FIND_PROMO_CODE_ITEMS,
      payload: promoCode
    };
  }

  static FIND_PROMO_CODE_ITEMS_SUCCESS = '[PromoCode] Find PromoCode Items Success';
  findPromoCodeItemsSuccess(items): Action {
    console.log("findPromoCodeTransactionsSuccess");
    return {
      type: PromoCodeActions.FIND_PROMO_CODE_ITEMS_SUCCESS,
      payload: items
    };
  }


  static UPDATE_PROMO_CODE = '[PromoCode] Update PromoCode';
  updatePromoCode(promoCode): Action {
    return {
      type: PromoCodeActions.UPDATE_PROMO_CODE,
      payload: promoCode
    };
  }

  static UPDATE_PROMO_CODE_SUCCESS = '[PromoCode] Update PromoCode Success';
  updatePromoCodeSuccess(promoCode): Action {
    return {
      type: PromoCodeActions.UPDATE_PROMO_CODE_SUCCESS,
      payload: promoCode
    };
  }

  static REMOVE_PROMO_CODE = '[PromoCode] Remove PromoCode';
  removePromoCode(promoCode): Action {
    return {
      type: PromoCodeActions.REMOVE_PROMO_CODE,
      payload: promoCode
    };
  }

  static REMOVE_PROMO_CODE_SUCCESS = '[PromoCode] Remove PromoCode Success';
  removePromoCodeSuccess(promoCode): Action {
    return {
      type: PromoCodeActions.REMOVE_PROMO_CODE_SUCCESS,
      payload: promoCode
    };
  }
  
  static ADD_PROMO_CODE_ITEM = '[PromoCode] Add PromoCode Item';

  addPromoCodeItem(promoCode, item): Action {
    return {
      type: PromoCodeActions.ADD_PROMO_CODE_ITEM,
      payload: {promoCode:promoCode, item:item}
    };
  }

  static ADD_PROMO_CODE_ITEM_SUCCESS = '[PromoCode] Add PromoCode Item Success';

  addPromoCodeItemSuccess(message): Action {
    return {
      type: PromoCodeActions.ADD_PROMO_CODE_ITEM_SUCCESS,
      payload: message
    };
  }
  
  static UPDATE_PROMO_CODE_ITEM = '[PromoCode] Update PromoCode Item';

  updatePromoCodeItem(promoCode, item): Action {
    return {
      type: PromoCodeActions.UPDATE_PROMO_CODE_ITEM,
      payload: {promoCode:promoCode, item:item}
    };
  }

  static UPDATE_PROMO_CODE_ITEM_SUCCESS = '[PromoCode] Update PromoCode Item Success';

  updatePromoCodeItemSuccess(message): Action {
    return {
      type: PromoCodeActions.UPDATE_PROMO_CODE_ITEM_SUCCESS,
      payload: message
    };
  }
  
  static DELETE_PROMO_CODE_ITEM = '[PromoCode] Delete PromoCode Item';

  deletePromoCodeItem(promoCode, item): Action {
    return {
      type: PromoCodeActions.DELETE_PROMO_CODE_ITEM,
      payload: {promoCode:promoCode, item:item}
    };
  }

  static DELETE_PROMO_CODE_ITEM_SUCCESS = '[PromoCode] Add PromoCode Item Success';

  deletePromoCodeItemSuccess(message): Action {
    return {
      type: PromoCodeActions.DELETE_PROMO_CODE_ITEM_SUCCESS,
      payload: message
    };
  }
}

import {Account} from '../account/account.interface';
export interface Voucher {

  id: number;
  referenceNo: string;
  invoiceNo: string;
  sourceNo: string;
  description: string;
  paid: Boolean;
  totalPretaxAmount: number;
  totalTaxAmount: number;
  totalAmount: number;
  balanceAmount: number;
  payer: Account;
}

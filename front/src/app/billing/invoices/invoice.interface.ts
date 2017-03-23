export interface Invoice {

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
  // period: Period;
  // requester: CostCenterCode;
  // payer: Actor;
}

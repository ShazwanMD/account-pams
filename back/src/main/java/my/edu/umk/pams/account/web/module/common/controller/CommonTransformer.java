package my.edu.umk.pams.account.web.module.common.controller;

import java.util.List;
import java.util.stream.Collectors;

import my.edu.umk.pams.account.common.model.AcBankCode;
import my.edu.umk.pams.account.web.module.common.vo.BankCode;

import org.springframework.stereotype.Component;

@Component("commonTransformer")
public class CommonTransformer {
	
	//====================================================================================================
	// BANK_CODE
	//====================================================================================================

	public BankCode toBankCodeVo(AcBankCode e) {
	    BankCode vo = new BankCode();
	        vo.setId(e.getId());
	        vo.setCode(e.getCode());
	        vo.setDescription(e.getName());
	        return vo;
	        }

	public List<BankCode> toBankCodeVos(List<AcBankCode> e) {
	        List<BankCode> vos = e.stream()
	        .map((e1) -> toBankCodeVo(e1))
	        .collect(Collectors.toList());
	        return vos;
	        }


}

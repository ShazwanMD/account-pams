package my.edu.umk.pams.account.web.module.common.controller;

import java.util.List;

import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.web.module.common.vo.BankCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/common")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private CommonTransformer commonTransformer;

	// ====================================================================================================
	// BANK_CODE
	// ====================================================================================================

	@RequestMapping(value = "/bankCodes", method = RequestMethod.GET)
	public ResponseEntity<List<BankCode>> findBankCodes() {
		return new ResponseEntity<List<BankCode>>(
				commonTransformer.toBankCodeVos(commonService.findBankCodes()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/bankCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<BankCode> findBankCodeByCode(@PathVariable String code) {
		return new ResponseEntity<BankCode>(
				commonTransformer.toBankCodeVo(commonService
						.findBankCodeByCode(code)), HttpStatus.OK);
	}

}

package my.edu.umk.pams.account.web.module.system.controller;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import my.edu.umk.pams.account.system.model.AcModule;
import my.edu.umk.pams.account.web.module.system.vo.Module;

/**
 * @author PAMS
 */
@Component("systemTransformer")
public class SystemTransformer
{
	public List<Module> toModuleVos(List<AcModule> e) {
        List<Module> vos = e.stream()
                .map((e1) -> toModuleVo(e1))
                .collect(Collectors.toList());
        return vos;
    }
	
	public Module toModuleVo(AcModule e) {
		Module vo = new Module();
        vo.setId(e.getId());
        vo.setCode(e.getCode());
        vo.setCanonicalCode(e.getCanonicalCode());
        vo.setDescription(e.getDescription());
        return vo;
    }
}

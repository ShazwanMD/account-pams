package my.edu.umk.pams.account.web.module.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.model.AcModule;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.system.vo.AuthenticatedUser;
import my.edu.umk.pams.account.web.module.system.vo.Module;


@RestController
@RequestMapping("/api/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemTransformer systemTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/modules/authorized", method = RequestMethod.GET)
    public ResponseEntity<List<Module>> findModules() {
        List<AcModule> modules = systemService.findModules(true);
        return new ResponseEntity<List<Module>>(systemTransformer.toModuleVos(modules), HttpStatus.OK);
    }

    @RequestMapping(value = "/authenticatedUser", method = RequestMethod.GET)
    public ResponseEntity<AuthenticatedUser> findAuthenticatedUser() {
        AcUser currentUser = securityService.getCurrentUser();
        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setUsername(currentUser.getUsername());
        authenticatedUser.setEmail(currentUser.getEmail());
        return new ResponseEntity<AuthenticatedUser>(authenticatedUser, HttpStatus.OK);
    }

    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================
}

package my.edu.umk.pams.account.web.module.workflow.controller;

import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.workflow.service.WorkflowService;

import java.util.List;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/workflow")
public class WorkflowController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowController.class);

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private AuthenticationManager authenticationManager;
    

    // ==================================================================================================== //
    //  Task
    // ==================================================================================================== //

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> findAssignedTasks() {
    	dummyLogin();
    	AcUser user = securityService.getCurrentUser();
    	List<Task> tasks = workflowService.findAssignedTasks(user, 0, 100);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }
    

    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================

    private void dummyLogin() {
        AcAutoLoginToken token = new AcAutoLoginToken("root");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

}

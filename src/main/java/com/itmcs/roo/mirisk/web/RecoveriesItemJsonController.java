package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.Recovery;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RecoveryService;
import io.springlets.web.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = RecoveriesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = Recovery.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/recoveries/{recovery}", name = "RecoveriesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RecoveriesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RecoveryService recoveryService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param recoveryService
     */
    @Autowired
    public RecoveriesItemJsonController(RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RecoveryService
     */
    public RecoveryService getRecoveryService() {
        return recoveryService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recoveryService
     */
    public void setRecoveryService(RecoveryService recoveryService) {
        this.recoveryService = recoveryService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Recovery
     */
    @ModelAttribute
    public Recovery getRecovery(@PathVariable("recovery") Long id) {
        Recovery recovery = recoveryService.findOne(id);
        if (recovery == null) {
            throw new NotFoundException(String.format("Recovery with identifier '%s' not found", id));
        }
        return recovery;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Recovery recovery) {
        return ResponseEntity.ok(recovery);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     * @return UriComponents
     */
    public static UriComponents showURI(Recovery recovery) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RecoveriesItemJsonController.class).show(recovery)).buildAndExpand(recovery.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedRecovery
     * @param recovery
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Recovery storedRecovery, @Valid @RequestBody Recovery recovery, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        recovery.setId(storedRecovery.getId());
        getRecoveryService().save(recovery);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param recovery
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Recovery recovery) {
        getRecoveryService().delete(recovery);
        return ResponseEntity.ok().build();
    }
}

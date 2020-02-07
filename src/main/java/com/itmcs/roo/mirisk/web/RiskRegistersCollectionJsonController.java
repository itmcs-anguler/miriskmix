package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskRegisterService;
import io.springlets.data.domain.GlobalSearch;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

/**
 * = RiskRegistersCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskRegister.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskregisters", name = "RiskRegistersCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskRegistersCollectionJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private RiskRegisterService riskRegisterService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param riskRegisterService
     */
    @Autowired
    public RiskRegistersCollectionJsonController(RiskRegisterService riskRegisterService) {
        this.riskRegisterService = riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return RiskRegisterService
     */
    public RiskRegisterService getRiskRegisterService() {
        return riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisterService
     */
    public void setRiskRegisterService(RiskRegisterService riskRegisterService) {
        this.riskRegisterService = riskRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<RiskRegister>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<RiskRegister> riskRegisters = getRiskRegisterService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(riskRegisters);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskRegistersCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegister
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody RiskRegister riskRegister, BindingResult result) {
        if (riskRegister.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        RiskRegister newRiskRegister = getRiskRegisterService().save(riskRegister);
        UriComponents showURI = RiskRegistersItemJsonController.showURI(newRiskRegister);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisters
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<RiskRegister> riskRegisters, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskRegisterService().save(riskRegisters);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegisters
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<RiskRegister> riskRegisters, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getRiskRegisterService().save(riskRegisters);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        getRiskRegisterService().delete(ids);
        return ResponseEntity.ok().build();
    }
}

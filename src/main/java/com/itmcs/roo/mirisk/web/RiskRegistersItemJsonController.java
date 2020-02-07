package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.RiskRegisterService;
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
 * = RiskRegistersItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = RiskRegister.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskregisters/{riskRegister}", name = "RiskRegistersItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskRegistersItemJsonController {

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
    public RiskRegistersItemJsonController(RiskRegisterService riskRegisterService) {
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
     * @param id
     * @return RiskRegister
     */
    @ModelAttribute
    public RiskRegister getRiskRegister(@PathVariable("riskRegister") Long id) {
        RiskRegister riskRegister = riskRegisterService.findOne(id);
        if (riskRegister == null) {
            throw new NotFoundException(String.format("RiskRegister with identifier '%s' not found", id));
        }
        return riskRegister;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegister
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute RiskRegister riskRegister) {
        return ResponseEntity.ok(riskRegister);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegister
     * @return UriComponents
     */
    public static UriComponents showURI(RiskRegister riskRegister) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(RiskRegistersItemJsonController.class).show(riskRegister)).buildAndExpand(riskRegister.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedRiskRegister
     * @param riskRegister
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute RiskRegister storedRiskRegister, @Valid @RequestBody RiskRegister riskRegister, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        riskRegister.setId(storedRiskRegister.getId());
        getRiskRegisterService().save(riskRegister);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param riskRegister
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute RiskRegister riskRegister) {
        getRiskRegisterService().delete(riskRegister);
        return ResponseEntity.ok().build();
    }
}

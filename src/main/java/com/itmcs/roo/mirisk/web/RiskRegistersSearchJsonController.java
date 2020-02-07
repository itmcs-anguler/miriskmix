package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.RiskRegister;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.finder.RooSearch;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import com.itmcs.roo.mirisk.service.api.RiskRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * = RiskRegistersSearchJsonController
 TODO Auto-generated class documentation
 *
 */
@RooController(entity = RiskRegister.class, pathPrefix = "/mirisk", type = ControllerType.SEARCH)
@RooSearch(finders = {  })
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/riskregisters/search", name = "RiskRegistersSearchJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class RiskRegistersSearchJsonController {

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
    public RiskRegistersSearchJsonController(RiskRegisterService riskRegisterService) {
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
}

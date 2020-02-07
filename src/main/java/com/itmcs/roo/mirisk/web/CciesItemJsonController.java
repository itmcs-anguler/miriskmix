package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.Ccy;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.CcyService;
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
 * = CciesItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = Ccy.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/ccies/{ccy}", name = "CciesItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class CciesItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private CcyService ccyService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param ccyService
     */
    @Autowired
    public CciesItemJsonController(CcyService ccyService) {
        this.ccyService = ccyService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return CcyService
     */
    public CcyService getCcyService() {
        return ccyService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccyService
     */
    public void setCcyService(CcyService ccyService) {
        this.ccyService = ccyService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return Ccy
     */
    @ModelAttribute
    public Ccy getCcy(@PathVariable("ccy") Long id) {
        Ccy ccy = ccyService.findOne(id);
        if (ccy == null) {
            throw new NotFoundException(String.format("Ccy with identifier '%s' not found", id));
        }
        return ccy;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Ccy ccy) {
        return ResponseEntity.ok(ccy);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return UriComponents
     */
    public static UriComponents showURI(Ccy ccy) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(CciesItemJsonController.class).show(ccy)).buildAndExpand(ccy.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedCcy
     * @param ccy
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Ccy storedCcy, @Valid @RequestBody Ccy ccy, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ccy.setId(storedCcy.getId());
        getCcyService().save(ccy);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccy
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Ccy ccy) {
        getCcyService().delete(ccy);
        return ResponseEntity.ok().build();
    }
}

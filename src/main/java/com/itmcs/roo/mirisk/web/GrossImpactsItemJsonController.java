package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.GrossImpactService;
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
 * = GrossImpactsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = GrossImpact.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/grossimpacts/{grossImpact}", name = "GrossImpactsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrossImpactsItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrossImpactService grossImpactService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param grossImpactService
     */
    @Autowired
    public GrossImpactsItemJsonController(GrossImpactService grossImpactService) {
        this.grossImpactService = grossImpactService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return GrossImpactService
     */
    public GrossImpactService getGrossImpactService() {
        return grossImpactService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpactService
     */
    public void setGrossImpactService(GrossImpactService grossImpactService) {
        this.grossImpactService = grossImpactService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return GrossImpact
     */
    @ModelAttribute
    public GrossImpact getGrossImpact(@PathVariable("grossImpact") Long id) {
        GrossImpact grossImpact = grossImpactService.findOne(id);
        if (grossImpact == null) {
            throw new NotFoundException(String.format("GrossImpact with identifier '%s' not found", id));
        }
        return grossImpact;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpact
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute GrossImpact grossImpact) {
        return ResponseEntity.ok(grossImpact);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpact
     * @return UriComponents
     */
    public static UriComponents showURI(GrossImpact grossImpact) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(GrossImpactsItemJsonController.class).show(grossImpact)).buildAndExpand(grossImpact.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedGrossImpact
     * @param grossImpact
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute GrossImpact storedGrossImpact, @Valid @RequestBody GrossImpact grossImpact, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        grossImpact.setId(storedGrossImpact.getId());
        getGrossImpactService().save(grossImpact);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param grossImpact
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute GrossImpact grossImpact) {
        getGrossImpactService().delete(grossImpact);
        return ResponseEntity.ok().build();
    }
}

package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ResidualRiskLikelihoodService;
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
 * = ResidualRiskLikelihoodsItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ResidualRiskLikelihood.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/residualrisklikelihoods/{residualRiskLikelihood}", name = "ResidualRiskLikelihoodsItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResidualRiskLikelihoodsItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResidualRiskLikelihoodService residualRiskLikelihoodService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param residualRiskLikelihoodService
     */
    @Autowired
    public ResidualRiskLikelihoodsItemJsonController(ResidualRiskLikelihoodService residualRiskLikelihoodService) {
        this.residualRiskLikelihoodService = residualRiskLikelihoodService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ResidualRiskLikelihoodService
     */
    public ResidualRiskLikelihoodService getResidualRiskLikelihoodService() {
        return residualRiskLikelihoodService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihoodService
     */
    public void setResidualRiskLikelihoodService(ResidualRiskLikelihoodService residualRiskLikelihoodService) {
        this.residualRiskLikelihoodService = residualRiskLikelihoodService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return ResidualRiskLikelihood
     */
    @ModelAttribute
    public ResidualRiskLikelihood getResidualRiskLikelihood(@PathVariable("residualRiskLikelihood") Long id) {
        ResidualRiskLikelihood residualRiskLikelihood = residualRiskLikelihoodService.findOne(id);
        if (residualRiskLikelihood == null) {
            throw new NotFoundException(String.format("ResidualRiskLikelihood with identifier '%s' not found", id));
        }
        return residualRiskLikelihood;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute ResidualRiskLikelihood residualRiskLikelihood) {
        return ResponseEntity.ok(residualRiskLikelihood);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @return UriComponents
     */
    public static UriComponents showURI(ResidualRiskLikelihood residualRiskLikelihood) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ResidualRiskLikelihoodsItemJsonController.class).show(residualRiskLikelihood)).buildAndExpand(residualRiskLikelihood.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedResidualRiskLikelihood
     * @param residualRiskLikelihood
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute ResidualRiskLikelihood storedResidualRiskLikelihood, @Valid @RequestBody ResidualRiskLikelihood residualRiskLikelihood, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        residualRiskLikelihood.setId(storedResidualRiskLikelihood.getId());
        getResidualRiskLikelihoodService().save(residualRiskLikelihood);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute ResidualRiskLikelihood residualRiskLikelihood) {
        getResidualRiskLikelihoodService().delete(residualRiskLikelihood);
        return ResponseEntity.ok().build();
    }
}

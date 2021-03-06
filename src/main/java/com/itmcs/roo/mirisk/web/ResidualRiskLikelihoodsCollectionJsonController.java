package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ResidualRiskLikelihood;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.ResidualRiskLikelihoodService;
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
 * = ResidualRiskLikelihoodsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = ResidualRiskLikelihood.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/residualrisklikelihoods", name = "ResidualRiskLikelihoodsCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResidualRiskLikelihoodsCollectionJsonController {

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
    public ResidualRiskLikelihoodsCollectionJsonController(ResidualRiskLikelihoodService residualRiskLikelihoodService) {
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
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<ResidualRiskLikelihood>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<ResidualRiskLikelihood> residualRiskLikelihoods = getResidualRiskLikelihoodService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(residualRiskLikelihoods);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(ResidualRiskLikelihoodsCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihood
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody ResidualRiskLikelihood residualRiskLikelihood, BindingResult result) {
        if (residualRiskLikelihood.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        ResidualRiskLikelihood newResidualRiskLikelihood = getResidualRiskLikelihoodService().save(residualRiskLikelihood);
        UriComponents showURI = ResidualRiskLikelihoodsItemJsonController.showURI(newResidualRiskLikelihood);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihoods
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<ResidualRiskLikelihood> residualRiskLikelihoods, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getResidualRiskLikelihoodService().save(residualRiskLikelihoods);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param residualRiskLikelihoods
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<ResidualRiskLikelihood> residualRiskLikelihoods, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getResidualRiskLikelihoodService().save(residualRiskLikelihoods);
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
        getResidualRiskLikelihoodService().delete(ids);
        return ResponseEntity.ok().build();
    }
}

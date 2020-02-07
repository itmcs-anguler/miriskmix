package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.AdditionalDetailsService;
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
 * = AdditionalDetailssItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = AdditionalDetails.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/additionaldetailss/{additionalDetails}", name = "AdditionalDetailssItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdditionalDetailssItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AdditionalDetailsService additionalDetailsService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param additionalDetailsService
     */
    @Autowired
    public AdditionalDetailssItemJsonController(AdditionalDetailsService additionalDetailsService) {
        this.additionalDetailsService = additionalDetailsService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return AdditionalDetailsService
     */
    public AdditionalDetailsService getAdditionalDetailsService() {
        return additionalDetailsService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetailsService
     */
    public void setAdditionalDetailsService(AdditionalDetailsService additionalDetailsService) {
        this.additionalDetailsService = additionalDetailsService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AdditionalDetails
     */
    @ModelAttribute
    public AdditionalDetails getAdditionalDetails(@PathVariable("additionalDetails") Long id) {
        AdditionalDetails additionalDetails = additionalDetailsService.findOne(id);
        if (additionalDetails == null) {
            throw new NotFoundException(String.format("AdditionalDetails with identifier '%s' not found", id));
        }
        return additionalDetails;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetails
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute AdditionalDetails additionalDetails) {
        return ResponseEntity.ok(additionalDetails);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetails
     * @return UriComponents
     */
    public static UriComponents showURI(AdditionalDetails additionalDetails) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(AdditionalDetailssItemJsonController.class).show(additionalDetails)).buildAndExpand(additionalDetails.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedAdditionalDetails
     * @param additionalDetails
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute AdditionalDetails storedAdditionalDetails, @Valid @RequestBody AdditionalDetails additionalDetails, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        additionalDetails.setId(storedAdditionalDetails.getId());
        getAdditionalDetailsService().save(additionalDetails);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param additionalDetails
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute AdditionalDetails additionalDetails) {
        getAdditionalDetailsService().delete(additionalDetails);
        return ResponseEntity.ok().build();
    }
}

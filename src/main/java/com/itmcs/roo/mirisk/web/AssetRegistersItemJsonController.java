package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.AssetRegisterService;
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
 * = AssetRegistersItemJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = AssetRegister.class, pathPrefix = "/mirisk", type = ControllerType.ITEM)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/assetregisters/{assetRegister}", name = "AssetRegistersItemJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssetRegistersItemJsonController {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AssetRegisterService assetRegisterService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param assetRegisterService
     */
    @Autowired
    public AssetRegistersItemJsonController(AssetRegisterService assetRegisterService) {
        this.assetRegisterService = assetRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return AssetRegisterService
     */
    public AssetRegisterService getAssetRegisterService() {
        return assetRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegisterService
     */
    public void setAssetRegisterService(AssetRegisterService assetRegisterService) {
        this.assetRegisterService = assetRegisterService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param id
     * @return AssetRegister
     */
    @ModelAttribute
    public AssetRegister getAssetRegister(@PathVariable("assetRegister") Long id) {
        AssetRegister assetRegister = assetRegisterService.findOne(id);
        if (assetRegister == null) {
            throw new NotFoundException(String.format("AssetRegister with identifier '%s' not found", id));
        }
        return assetRegister;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegister
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute AssetRegister assetRegister) {
        return ResponseEntity.ok(assetRegister);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegister
     * @return UriComponents
     */
    public static UriComponents showURI(AssetRegister assetRegister) {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(AssetRegistersItemJsonController.class).show(assetRegister)).buildAndExpand(assetRegister.getId()).encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param storedAssetRegister
     * @param assetRegister
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute AssetRegister storedAssetRegister, @Valid @RequestBody AssetRegister assetRegister, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        assetRegister.setId(storedAssetRegister.getId());
        getAssetRegisterService().save(assetRegister);
        return ResponseEntity.ok().build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegister
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute AssetRegister assetRegister) {
        getAssetRegisterService().delete(assetRegister);
        return ResponseEntity.ok().build();
    }
}

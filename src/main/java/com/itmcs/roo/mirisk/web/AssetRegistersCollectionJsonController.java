package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import org.springframework.roo.addon.web.mvc.controller.annotations.ControllerType;
import org.springframework.roo.addon.web.mvc.controller.annotations.RooController;
import org.springframework.roo.addon.web.mvc.controller.annotations.responses.json.RooJSON;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.itmcs.roo.mirisk.service.api.AssetRegisterService;
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
 * = AssetRegistersCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
@CrossOrigin
@RooController(entity = AssetRegister.class, pathPrefix = "/mirisk", type = ControllerType.COLLECTION)
@RooJSON
@RestController
@RequestMapping(value = "/mirisk/assetregisters", name = "AssetRegistersCollectionJsonController", produces = MediaType.APPLICATION_JSON_VALUE)
public class AssetRegistersCollectionJsonController {

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
    public AssetRegistersCollectionJsonController(AssetRegisterService assetRegisterService) {
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
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<AssetRegister>> list(GlobalSearch globalSearch, Pageable pageable) {
        Page<AssetRegister> assetRegisters = getAssetRegisterService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(assetRegisters);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder.fromMethodCall(MvcUriComponentsBuilder.on(AssetRegistersCollectionJsonController.class).list(null, null)).build().encode();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegister
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody AssetRegister assetRegister, BindingResult result) {
        if (assetRegister.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        AssetRegister newAssetRegister = getAssetRegisterService().save(assetRegister);
        UriComponents showURI = AssetRegistersItemJsonController.showURI(newAssetRegister);
        return ResponseEntity.created(showURI.toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegisters
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<AssetRegister> assetRegisters, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getAssetRegisterService().save(assetRegisters);
        return ResponseEntity.created(listURI().toUri()).build();
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param assetRegisters
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<AssetRegister> assetRegisters, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        getAssetRegisterService().save(assetRegisters);
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
        getAssetRegisterService().delete(ids);
        return ResponseEntity.ok().build();
    }
}

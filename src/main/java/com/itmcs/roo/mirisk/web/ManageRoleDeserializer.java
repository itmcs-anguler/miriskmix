package com.itmcs.roo.mirisk.web;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.itmcs.roo.mirisk.dta.ManageRole;
import com.itmcs.roo.mirisk.service.api.ManageRoleService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = ManageRoleDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = ManageRole.class)
@JsonComponent
public class ManageRoleDeserializer extends JsonObjectDeserializer<ManageRole> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageRoleService manageRoleService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param manageRoleService
     * @param conversionService
     */
    @Autowired
    public ManageRoleDeserializer(@Lazy ManageRoleService manageRoleService, ConversionService conversionService) {
        this.manageRoleService = manageRoleService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageRoleService
     */
    public ManageRoleService getManageRoleService() {
        return manageRoleService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageRoleService
     */
    public void setManageRoleService(ManageRoleService manageRoleService) {
        this.manageRoleService = manageRoleService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return ManageRole
     */
    public ManageRole deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        ManageRole manageRole = manageRoleService.findOne(id);
        if (manageRole == null) {
            throw new NotFoundException("ManageRole not found");
        }
        return manageRole;
    }
}

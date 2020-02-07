package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManageUser;
import com.itmcs.roo.mirisk.service.api.ManageUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.roo.addon.web.mvc.controller.annotations.config.RooDeserializer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import io.springlets.web.NotFoundException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * = ManageUserDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = ManageUser.class)
@JsonComponent
public class ManageUserDeserializer extends JsonObjectDeserializer<ManageUser> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManageUserService manageUserService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param manageUserService
     * @param conversionService
     */
    @Autowired
    public ManageUserDeserializer(@Lazy ManageUserService manageUserService, ConversionService conversionService) {
        this.manageUserService = manageUserService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManageUserService
     */
    public ManageUserService getManageUserService() {
        return manageUserService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param manageUserService
     */
    public void setManageUserService(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
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
     * @return ManageUser
     */
    public ManageUser deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        ManageUser manageUser = manageUserService.findOne(id);
        if (manageUser == null) {
            throw new NotFoundException("ManageUser not found");
        }
        return manageUser;
    }
}

package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.ManagePage;
import com.itmcs.roo.mirisk.service.api.ManagePageService;
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
 * = ManagePageDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = ManagePage.class)
@JsonComponent
public class ManagePageDeserializer extends JsonObjectDeserializer<ManagePage> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ManagePageService managePageService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param managePageService
     * @param conversionService
     */
    @Autowired
    public ManagePageDeserializer(@Lazy ManagePageService managePageService, ConversionService conversionService) {
        this.managePageService = managePageService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return ManagePageService
     */
    public ManagePageService getManagePageService() {
        return managePageService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param managePageService
     */
    public void setManagePageService(ManagePageService managePageService) {
        this.managePageService = managePageService;
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
     * @return ManagePage
     */
    public ManagePage deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        ManagePage managePage = managePageService.findOne(id);
        if (managePage == null) {
            throw new NotFoundException("ManagePage not found");
        }
        return managePage;
    }
}

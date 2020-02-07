package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.AdditionalDetails;
import com.itmcs.roo.mirisk.service.api.AdditionalDetailsService;
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
 * = AdditionalDetailsDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = AdditionalDetails.class)
@JsonComponent
public class AdditionalDetailsDeserializer extends JsonObjectDeserializer<AdditionalDetails> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AdditionalDetailsService additionalDetailsService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param additionalDetailsService
     * @param conversionService
     */
    @Autowired
    public AdditionalDetailsDeserializer(@Lazy AdditionalDetailsService additionalDetailsService, ConversionService conversionService) {
        this.additionalDetailsService = additionalDetailsService;
        this.conversionService = conversionService;
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
     * @return AdditionalDetails
     */
    public AdditionalDetails deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        AdditionalDetails additionalDetails = additionalDetailsService.findOne(id);
        if (additionalDetails == null) {
            throw new NotFoundException("AdditionalDetails not found");
        }
        return additionalDetails;
    }
}

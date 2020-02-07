package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.GrossImpact;
import com.itmcs.roo.mirisk.service.api.GrossImpactService;
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
 * = GrossImpactDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = GrossImpact.class)
@JsonComponent
public class GrossImpactDeserializer extends JsonObjectDeserializer<GrossImpact> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private GrossImpactService grossImpactService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param grossImpactService
     * @param conversionService
     */
    @Autowired
    public GrossImpactDeserializer(@Lazy GrossImpactService grossImpactService, ConversionService conversionService) {
        this.grossImpactService = grossImpactService;
        this.conversionService = conversionService;
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
     * @return GrossImpact
     */
    public GrossImpact deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        GrossImpact grossImpact = grossImpactService.findOne(id);
        if (grossImpact == null) {
            throw new NotFoundException("GrossImpact not found");
        }
        return grossImpact;
    }
}

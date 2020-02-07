package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.Ccy;
import com.itmcs.roo.mirisk.service.api.CcyService;
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
 * = CcyDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = Ccy.class)
@JsonComponent
public class CcyDeserializer extends JsonObjectDeserializer<Ccy> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private CcyService ccyService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param ccyService
     * @param conversionService
     */
    @Autowired
    public CcyDeserializer(@Lazy CcyService ccyService, ConversionService conversionService) {
        this.ccyService = ccyService;
        this.conversionService = conversionService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @return CcyService
     */
    public CcyService getCcyService() {
        return ccyService;
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ccyService
     */
    public void setCcyService(CcyService ccyService) {
        this.ccyService = ccyService;
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
     * @return Ccy
     */
    public Ccy deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Ccy ccy = ccyService.findOne(id);
        if (ccy == null) {
            throw new NotFoundException("Ccy not found");
        }
        return ccy;
    }
}

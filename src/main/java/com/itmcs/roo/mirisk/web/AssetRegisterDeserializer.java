package com.itmcs.roo.mirisk.web;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import com.itmcs.roo.mirisk.service.api.AssetRegisterService;
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
 * = AssetRegisterDeserializer
 TODO Auto-generated class documentation
 *
 */
@RooDeserializer(entity = AssetRegister.class)
@JsonComponent
public class AssetRegisterDeserializer extends JsonObjectDeserializer<AssetRegister> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private AssetRegisterService assetRegisterService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param assetRegisterService
     * @param conversionService
     */
    @Autowired
    public AssetRegisterDeserializer(@Lazy AssetRegisterService assetRegisterService, ConversionService conversionService) {
        this.assetRegisterService = assetRegisterService;
        this.conversionService = conversionService;
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
     * @return AssetRegister
     */
    public AssetRegister deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        AssetRegister assetRegister = assetRegisterService.findOne(id);
        if (assetRegister == null) {
            throw new NotFoundException("AssetRegister not found");
        }
        return assetRegister;
    }
}

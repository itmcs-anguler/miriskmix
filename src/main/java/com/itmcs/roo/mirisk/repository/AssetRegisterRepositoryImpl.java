package com.itmcs.roo.mirisk.repository;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;
import org.springframework.roo.addon.layers.repository.jpa.annotations.RooJpaRepositoryCustomImpl;
import com.itmcs.roo.mirisk.dta.AssetRegister;
import com.itmcs.roo.mirisk.dta.QAssetRegister;
import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt.AttributeMappingBuilder;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

/**
 * = AssetRegisterRepositoryImpl
 TODO Auto-generated class documentation
 *
 */
@RooJpaRepositoryCustomImpl(repository = AssetRegisterRepositoryCustom.class)
@Transactional(readOnly = true)
public class AssetRegisterRepositoryImpl extends QueryDslRepositorySupportExt<AssetRegister> implements AssetRegisterRepositoryCustom {

    /**
     * Default constructor
     */
    AssetRegisterRepositoryImpl() {
        super(AssetRegister.class);
    }

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String LOCATION = "location";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String ASSET_NAME = "assetName";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DESCRIPTION = "description";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String CURRENT_VALUE = "currentValue";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String USED_FOR = "usedFor";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PROCUREMENT_PRICE = "procurementPrice";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String PROCUREMENT_DATE = "procurementDate";

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    public static final String DEPRECIATION = "depreciation";

    /**
     * TODO Auto-generated method documentation
     *
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AssetRegister> findAll(GlobalSearch globalSearch, Pageable pageable) {
        QAssetRegister assetRegister = QAssetRegister.assetRegister;
        JPQLQuery<AssetRegister> query = from(assetRegister);
        Path<?>[] paths = new Path<?>[] { assetRegister.assetName, assetRegister.description, assetRegister.location, assetRegister.usedFor, assetRegister.procurementDate, assetRegister.procurementPrice, assetRegister.depreciation, assetRegister.currentValue };
        applyGlobalSearch(globalSearch, query, paths);
        AttributeMappingBuilder mapping = buildMapper().map(ASSET_NAME, assetRegister.assetName).map(DESCRIPTION, assetRegister.description).map(LOCATION, assetRegister.location).map(USED_FOR, assetRegister.usedFor).map(PROCUREMENT_DATE, assetRegister.procurementDate).map(PROCUREMENT_PRICE, assetRegister.procurementPrice).map(DEPRECIATION, assetRegister.depreciation).map(CURRENT_VALUE, assetRegister.currentValue);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, assetRegister);
    }

    /**
     * TODO Auto-generated method documentation
     *
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public Page<AssetRegister> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        QAssetRegister assetRegister = QAssetRegister.assetRegister;
        JPQLQuery<AssetRegister> query = from(assetRegister);
        Path<?>[] paths = new Path<?>[] { assetRegister.assetName, assetRegister.description, assetRegister.location, assetRegister.usedFor, assetRegister.procurementDate, assetRegister.procurementPrice, assetRegister.depreciation, assetRegister.currentValue };
        applyGlobalSearch(globalSearch, query, paths);
        // Also, filter by the provided ids
        query.where(assetRegister.id.in(ids));
        AttributeMappingBuilder mapping = buildMapper().map(ASSET_NAME, assetRegister.assetName).map(DESCRIPTION, assetRegister.description).map(LOCATION, assetRegister.location).map(USED_FOR, assetRegister.usedFor).map(PROCUREMENT_DATE, assetRegister.procurementDate).map(PROCUREMENT_PRICE, assetRegister.procurementPrice).map(DEPRECIATION, assetRegister.depreciation).map(CURRENT_VALUE, assetRegister.currentValue);
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        return loadPage(query, pageable, assetRegister);
    }
}

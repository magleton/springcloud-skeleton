package com.geoxus.commons.services;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.geoxus.commons.entities.GXMediaLibraryEntity;
import com.geoxus.commons.events.GXMediaLibraryEvent;
import com.geoxus.core.common.constant.GXCommonConstants;
import com.geoxus.core.common.exception.GXException;
import com.geoxus.core.common.util.GXSpringContextUtils;
import com.geoxus.core.common.vo.response.GXPagination;
import com.geoxus.core.framework.service.GXBaseService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public interface GXMediaLibraryService extends GXBaseService<GXMediaLibraryEntity> {
    /**
     * 保存数据
     *
     * @param dict 参数
     * @return int
     */
    int save(Dict dict);

    /**
     * 更新条目所关联的模块ID
     * <pre>
     * {@code
     *      List<JSONObject> param = new ArrayList<>();
     *      Dict data1 = Dict.create()
     *      .set("id", 22)
     *      .set("core_model_id", 8)
     *      .set("custom_properties", Dict.create()
     *      .set("name", "tom")
     *      .set("age", 12));
     *      Dict data2 = Dict.create()
     *      .set("id", 24)
     *      .set("core_model_id", 8)
     *      .set("resource_type", "AABB")
     *      .set("update_old", 1)
     *      .set("custom_properties", Dict.create()
     *      .set("name", "tom")
     *      .set("age", 12));
     *     param.add(JSONUtil.parseObj(data2));
     *     param.add(JSONUtil.parseObj(data1));
     *     updateOldFile(12, 10, param, Dict.create().set("resource_type", "A"));
     *  }
     * </pre>
     *
     * @param targetId    对象记录ID
     * @param coreModelId 　核心模型ID
     * @param param       　参数
     * @param condition   条件
     */
    void updateOwner(long targetId, long coreModelId, List<JSONObject> param, Dict condition);

    /**
     * 保存文件
     *
     * @param file  文件
     * @param param 参数
     * @return GXCoreMediaLibraryEntity
     */
    GXMediaLibraryEntity saveFileInfo(MultipartFile file, Dict param);

    /**
     * 通过条件删除media
     *
     * @param param 参数
     * @return boolean
     */
    boolean deleteByCondition(Dict param);

    /**
     * 通过条件获取资源文件
     *
     * @param param 参数
     * @return List
     */
    List<Dict> getMediaByCondition(Dict param);

    /**
     * 更新旧的资源
     *
     * <pre>
     * {@code
     *      List<JSONObject> objectList = new ArrayList<>();
     *      Dict data1 = Dict.create()
     *      .set("id", 22)
     *      .set("core_model_id", 8)
     *      .set("custom_properties", Dict.create()
     *      .set("name", "tom")
     *      .set("age", 12));
     *      Dict data2 = Dict.create()
     *      .set("id", 24)
     *      .set("core_model_id", 8)
     *      .set("resource_type", "AABB")
     *      .set("update_old", 1)
     *      .set("custom_properties", Dict.create()
     *      .set("name", "tom")
     *      .set("age", 12));
     *     objectList.add(JSONUtil.parseObj(data2));
     *     objectList.add(JSONUtil.parseObj(data1));
     *     Dict condition = Dict.create().set("resource_type", "A");
     *     Dict param = Dict.create().set("data" ,objectList )
     *     .set("object_id" , 100)
     *     .set("core_model_id" , 23)
     *     .set("condition" , condition);
     *     updateOldFile(param);
     *  }
     * </pre>
     *
     * @param param 参数
     */
    void updateOldFile(Dict param);

    /**
     * 获取实体对象的媒体文件
     *
     * @param objectId    实体对象模型ID
     * @param coreModelId 实体模型ID
     * @param param       其他参数
     * @return Collection
     */
    default Collection<GXMediaLibraryEntity> getMedia(int objectId, int coreModelId, Dict param) {
        final GXMediaLibraryService mediaLibraryService = GXSpringContextUtils.getBean(GXMediaLibraryService.class);
        assert mediaLibraryService != null;
        return mediaLibraryService.listByMap(param.set("object_id", objectId).set(GXCommonConstants.CORE_MODEL_PRIMARY_NAME, coreModelId));
    }

    /**
     * 处理用户上传的资源文件
     * <p>
     * {@code
     * "media_info":[
     * {
     * "id":1,
     * "resource_type":"AAAAA",
     * "oss_url":"http://www.geoxus.io/"
     * }
     * ]
     * }
     *
     * @param target   目标对象
     * @param objectId 模型ID
     * @param param    参数
     */
    default void handleMedia(GXMediaLibraryEntity target, @NotNull long objectId, @NotNull Dict param) {
        if (param.getInt(GXCommonConstants.CORE_MODEL_PRIMARY_NAME) == null) {
            throw new GXException(StrUtil.format("请在param参数中传递{}字段", GXCommonConstants.CORE_MODEL_PRIMARY_NAME));
        }
        final String mediaFieldName = "media_info";
        Object mediaObj = param.getObj(mediaFieldName);
        if (null == mediaObj) {
            //throw new GXException(StrUtil.format("请求参数param中不存在{}字段", mediaFieldName));
            logger.error(CharSequenceUtil.format("请求参数param中不存在{}字段", mediaFieldName));
            return;
        }
        final List<JSONObject> media = Convert.convert(new TypeReference<List<JSONObject>>() {
        }, mediaObj);
        if (null != media) {
            Dict data = Dict.create()
                    .set("media", media)
                    .set("object_id", objectId)
                    .set(GXCommonConstants.CORE_MODEL_PRIMARY_NAME, param.getInt(GXCommonConstants.CORE_MODEL_PRIMARY_NAME));
            final GXMediaLibraryEvent<GXMediaLibraryEntity> event = new GXMediaLibraryEvent<>(target, data);
            publishEvent(event);
        }
    }


    /**
     * 合并分页数据中的每条数据的资源文件
     * <pre>
     *     {@code
     *     mergePaginationCoreMediaLibrary(pagination,"bannerId")
     *     }
     * </pre>
     *
     * @param pagination 分页数据
     * @param modelIdKey 分页数据中模型的key,一般为数据表主键名字的驼峰名字
     * @return GXPagination
     */
    default GXPagination<Dict> mergePaginationCoreMediaLibrary(GXPagination<Dict> pagination, String modelIdKey) {
        final GXMediaLibraryService coreMediaLibraryService = GXSpringContextUtils.getBean(GXMediaLibraryService.class);
        final List<?> records = pagination.getRecords();
        for (Object record : records) {
            final Dict o = (Dict) record;
            assert coreMediaLibraryService != null;
            o.set("media", coreMediaLibraryService.getMediaByCondition(Dict.create().set("object_id", o.getLong(modelIdKey)).set(GXCommonConstants.CORE_MODEL_PRIMARY_NAME, o.getLong("coreModelId"))));
        }
        return pagination;
    }

    /**
     * 合并分页数据中的每条数据的资源文件
     * <pre>
     *     {@code
     *     mergePaginationCoreMediaLibrary(pagination)
     *     }
     * </pre>
     *
     * @param pagination 分页数据
     * @return GXPagination
     */
    default GXPagination<Dict> mergePaginationCoreMediaLibrary(GXPagination<Dict> pagination) {
        String modelIdKey = getPrimaryKey();
        final GXMediaLibraryService coreMediaLibraryService = GXSpringContextUtils.getBean(GXMediaLibraryService.class);
        final List<?> records = pagination.getRecords();
        for (Object record : records) {
            final Dict o = (Dict) record;
            assert coreMediaLibraryService != null;
            o.set("media", coreMediaLibraryService.getMediaByCondition(Dict.create().set("object_id", o.getLong(modelIdKey)).set(GXCommonConstants.CORE_MODEL_PRIMARY_NAME, o.getLong("coreModelId"))));
        }
        return pagination;
    }
}

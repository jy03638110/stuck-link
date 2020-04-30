package top.stuck.link.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import top.stuck.link.core.model.UrlModel;

/**
 * Created on 2020-04-28
 *
 * @author Octopus
 */
@Mapper
public interface UrlDao {

    /**
     * 获取短链配置
     * @param code
     * @return
     */
    UrlModel getUrl(String code);

    /**
     * 保存短链配置
     * @param model
     * @return
     */
    Integer saveUrl(UrlModel model);

    /**
     * 更新短链配置
     * @param model
     * @return
     */
    Integer updateUrl(UrlModel model);

    Integer deleteUrl(UrlModel model);

    /**
     * 删除失效短链配置
     * @return
     */
    Integer deleteInvalidUrl();


}

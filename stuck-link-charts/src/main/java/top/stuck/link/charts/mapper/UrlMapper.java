package top.stuck.link.charts.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.stuck.link.core.entity.UrlEntity;
import top.stuck.link.core.model.UrlModel;

import java.util.List;
import java.util.Map;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
@Mapper
public interface UrlMapper {

    List<UrlModel> page(Map<String, Object> params);

    UrlModel getUrlByCode(String code);

    UrlModel getUrlByShortUrl(String shortUrl);

    Integer addUrl(UrlEntity urlEntity);

    Integer removeUrl(String code);
}

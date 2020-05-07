package top.stuck.link.charts.service;

import top.stuck.link.core.entity.UrlEntity;
import top.stuck.link.core.model.UrlModel;

import java.util.List;
import java.util.Map;

/**
 * Created on 2020-05-07
 *
 * @author Octopus
 */
public interface UrlService {

    List<UrlModel> page(Map<String, Object> params);

    UrlModel loadUrlByCode(String code);

    UrlModel loadUrlByShortUrl(String shortUrl);

    Integer addUrl(UrlEntity urlEntity);

    Integer removeUrl(String code);

}

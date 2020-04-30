package top.stuck.link.redirect.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.stuck.link.core.model.UrlModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
@Mapper
public interface UrlMapper {

    UrlModel getUrlByCode(String code);

    UrlModel getUrlByShortUrl(String shortUrl);

    Integer addUrl(UrlModel urlModel);
}

package top.stuck.link.redirect.service;

import top.stuck.link.core.model.UrlModel;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public interface IndexService {

    public UrlModel loadUrlByCode(String code);

    public UrlModel loadUrlByShortUrl(String shortUrl);

    public Integer addUrl(UrlModel urlModel);
}

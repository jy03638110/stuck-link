package top.stuck.link.redirect.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.stuck.link.redirect.mapper.UrlMapper;
import top.stuck.link.redirect.service.IndexService;
import top.stuck.link.core.model.UrlModel;
import top.stuck.link.core.utils.StringUtil;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
@Service("indexService")
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UrlMapper urlMapper;

    @Override
    public UrlModel loadUrlByCode(String code) {
        if (StringUtil.isEmpty(code)) {
            return null;
        }
        return urlMapper.getUrlByCode(code);
    }

    @Override
    public UrlModel loadUrlByShortUrl(String shortUrl) {
        if (StringUtil.isEmpty(shortUrl)) {
            return null;
        }
        return urlMapper.getUrlByShortUrl(shortUrl);
    }

    @Override
    public Integer addUrl(UrlModel urlModel) {
        try {
            return urlMapper.addUrl(urlModel);
        } catch (Exception e) {
            return 0;
        }
    }
}

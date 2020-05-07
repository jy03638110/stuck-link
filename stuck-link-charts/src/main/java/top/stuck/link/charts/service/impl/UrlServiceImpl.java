package top.stuck.link.charts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.stuck.link.charts.mapper.UrlMapper;
import top.stuck.link.charts.service.UrlService;
import top.stuck.link.core.entity.UrlEntity;
import top.stuck.link.core.model.UrlModel;
import top.stuck.link.core.utils.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * Created on 2020-05-07
 *
 * @author Octopus
 */
@Service("urlService")
public class UrlServiceImpl implements UrlService {

    private static Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    @Autowired
    private UrlMapper urlMapper;

    @Override
    public List<UrlModel> page(Map<String, Object> params) {
        List<UrlModel> urlList = null;
        try {
            urlList = urlMapper.page(params);
        } catch (Exception e) {
            logger.error("查询短链失败", e);
        }
        return urlList;
    }

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
    public Integer addUrl(UrlEntity urlEntity) {
        try {
            return urlMapper.addUrl(urlEntity);
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Integer removeUrl(String code) {
        try {
            return urlMapper.removeUrl(code);
        } catch (Exception e) {
            return 0;
        }
    }

}

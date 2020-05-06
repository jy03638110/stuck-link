package top.stuck.link.charts.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import top.stuck.link.charts.mapper.ChartsMapper;
import top.stuck.link.charts.service.ChartsService;

import java.util.List;
import java.util.Map;

/**
 * 图形服务类
 * Created on 2020-04-30
 *
 * @author Octopus
 */
@Service("chartsService")
@ConditionalOnProperty(prefix = "link.charts", name = "api-enabled", havingValue = "true")
public class ChartsServiceImpl implements ChartsService {

    private static Logger logger = LoggerFactory.getLogger(ChartsServiceImpl.class);

    @Autowired
    private ChartsMapper chartsMapper;

    @Override
    public List<Map<String, Object>> visitRatio(Map<String, Object> params) {
        try {
            return chartsMapper.visitRatio(params);
        } catch (Exception e) {
            logger.error("获取短链访问占比失败", e);
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> visitDetail(Map<String, Object> params) {
        try {
            return chartsMapper.visitDetail(params);
        } catch (Exception e) {
            logger.error("获取短链访问明细失败", e);
            return null;
        }
    }
}

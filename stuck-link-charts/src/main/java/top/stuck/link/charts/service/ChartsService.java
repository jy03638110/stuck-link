package top.stuck.link.charts.service;

import java.util.List;
import java.util.Map;

/**
 * Created on 2020-04-30
 *
 * @author Octopus
 */
public interface ChartsService {

    List<Map<String, Object>> visitRatio(Map<String, Object> params);

    List<Map<String, Object>> visitDetail(Map<String, Object> params);
}

package top.stuck.link.charts.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created on 2020-04-30
 *
 * @author Octopus
 */
@Mapper
public interface ChartsMapper {

    List<Map<String, Object>> visitRatio(Map<String, Object> params);

    List<Map<String, Object>> visitDetail(Map<String, Object> params);

}

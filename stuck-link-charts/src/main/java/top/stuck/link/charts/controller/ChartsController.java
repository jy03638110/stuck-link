package top.stuck.link.charts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.stuck.link.charts.service.ChartsService;
import top.stuck.link.core.response.ReturnT;
import top.stuck.link.core.utils.StringUtil;
import top.stuck.link.core.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 2020-04-30
 *
 * @author Octopus
 */
@Controller
@RequestMapping("/charts")
@ConditionalOnProperty(prefix = "link.charts", name = "api-enabled", havingValue = "true")
public class ChartsController {

    @Autowired
    private ChartsService chartsService;

    @ResponseBody
    @RequestMapping("/ratio/{code}")
    public ReturnT<Map<String,Object>> ratio(@PathVariable("code") String code, HttpServletRequest request) {
        Map<String, Object> params = WebUtil.getReqMap(request);
        String type = WebUtil.getString("type", params);
        if (!StringUtil.isEmpty(type) && !"os".equals(type) && !"ua".equals(type)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "错误的汇总类型");
        }
        String messageType = WebUtil.getString("messageType", params);
        if (StringUtil.isEmpty(messageType)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "消息类型不能为空");
        } else if ("1".equals(messageType)) {
            params.put("requestUrl", "/" + code);
            params.put("queryString", "");
        } else if ("2".equals(messageType)) {
            params.put("requestUrl", "/img/" + code);
            params.put("queryString", "");
        } else {
            return new ReturnT<>(ReturnT.FAIL_CODE, "暂不支持的消息类型");
        }
        String startTime = WebUtil.getString("startTime", params);
        String endTime = WebUtil.getString("endTime", params);
        if (StringUtil.isEmpty(startTime)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "开始时间不能为空");
        }
        if (StringUtil.isEmpty(endTime)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "结束时间不能为空");
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setLenient(false);
        Date startDate;
        try {
            startDate = format.parse(startTime);
        } catch (ParseException e) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "开始时间格式错误");
        }
        params.put("startTime", startDate);
        Date endDate;
        try {
            endDate = format.parse(endTime);
        } catch (ParseException e) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "结束时间格式错误");
        }
        params.put("endTime", endDate);

        Map<String, Object> result = new HashMap<>(2);
        List<Map<String, Object>> visitRatio = chartsService.visitRatio(params);
        result.put("visitRatio", visitRatio);
        String needDetail = WebUtil.getString("needDetail", params);
        if ("1".equals(needDetail)){
            List<Map<String, Object>> detail = chartsService.visitDetail(params);
            if (detail != null && !detail.isEmpty()) {
                result.put("detail", detail);
            }
        }
        return new ReturnT<>(result);
    }
}


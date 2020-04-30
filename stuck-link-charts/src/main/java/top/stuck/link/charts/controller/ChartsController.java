package top.stuck.link.charts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.stuck.link.charts.service.ChartsService;
import top.stuck.link.core.response.ReturnT;

import javax.servlet.http.HttpServletRequest;
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
    public ReturnT<List<Map<String, Object>>> ratio(@PathVariable("code") String code, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        chartsService.visitRatio(params);
        return null;
    }
}


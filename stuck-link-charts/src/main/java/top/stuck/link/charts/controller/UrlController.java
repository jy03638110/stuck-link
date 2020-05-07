package top.stuck.link.charts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.stuck.link.charts.service.UrlService;
import top.stuck.link.core.contants.SystemContant;
import top.stuck.link.core.model.UrlModel;
import top.stuck.link.core.properties.SystemProperties;
import top.stuck.link.core.response.ReturnT;
import top.stuck.link.core.utils.StringUtil;
import top.stuck.link.core.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created on 2020-05-07
 *
 * @author Octopus
 */
@Controller
@RequestMapping("/url")
@ConditionalOnProperty(prefix = "link.admin", name = "api-enabled", havingValue = "true")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @Autowired
    private SystemProperties systemProperties;

    @RequestMapping("/page.do")
    @ResponseBody
    public ReturnT<List<UrlModel>> page(HttpServletRequest request) {
        ReturnT checkAccessToken = validAccessToken(request);
        if (checkAccessToken != null) {
            return checkAccessToken;
        }
        ReturnT<List<UrlModel>> result = new ReturnT<>();
        Map<String, Object> params = WebUtil.getReqMap(request);
        WebUtil.initMysqlPageParams(params);
        List<UrlModel> urlModelList = urlService.page(params);
        result.setContent(urlModelList);
        return result;
    }

    @RequestMapping("/remove.do")
    @ResponseBody
    public ReturnT<String> remove(HttpServletRequest request) {
        ReturnT checkAccessToken = validAccessToken(request);
        if (checkAccessToken != null) {
            return checkAccessToken;
        }
        Map<String, Object> params = WebUtil.getReqMap(request);
        String code = WebUtil.getString("code", params);
        if (StringUtil.isEmpty(code)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "删除code不能为空");
        }
        Integer count = urlService.removeUrl(code);
        if (count > 0) {
            return ReturnT.SUCCESS;
        } else {
            return ReturnT.FAIL;
        }
    }

    private ReturnT validAccessToken(HttpServletRequest request) {
        if (!StringUtil.isEmpty(systemProperties.getAccessToken())
                && !systemProperties.getAccessToken().equals(request.getHeader(SystemContant.RPC_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }
        return null;
    }
}

package top.stuck.link.redirect.controller;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cz.mallat.uasparser.UserAgentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.stuck.link.core.response.ReturnT;
import top.stuck.link.core.utils.*;
import top.stuck.link.redirect.manager.MessageManager;
import top.stuck.link.redirect.service.IndexService;
import top.stuck.link.core.model.UrlModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Map;

/**
 * 短链接转发控制器
 * Created on 2019-11-07
 *
 * @author Octopus
 */
@Controller
public class IndexController {

    public static final String URL_MODEL_PREFIX = "UrlModel:";

    @Value("${link.no-code-redirect:http://www.stuck.top}")
    private String noCodeRedirect;

    @Value("${stuck.server-path:}")
    private String serverPath;

    @Value("${link.cache-time:300}")
    private Integer cacheTime;

    @Autowired
    private IndexService indexService;

    @Autowired(required = false)
    private MessageManager messageManager;

    /**
     * 匿名跳转
     * @param request
     * @param code
     * @return
     */
    @GetMapping("/")
    public String anonymous(HttpServletRequest request) throws Exception {
        String redirectUrl = request.getQueryString();
        if (StringUtil.isEmpty(redirectUrl)) {
            return "redirect:" + noCodeRedirect;
        }
        // 给跳转URL不带http://头的地址做简单补全
        redirectUrl = URLUtil.normalize(redirectUrl);
        // 写入请求消息
        messageManager.sendMessage(request, MessageManager.MESSAGE_ANONYMOUS_TYPE);
        return "redirect:" + redirectUrl;
    }

    /**
     * 生成短链
     * @param request
     * @param code
     * @return
     */
    @GetMapping("/short")
    @ResponseBody
    public ReturnT<String> shortUrl(HttpServletRequest request) throws Exception {
        String url = request.getQueryString();
        if (StringUtil.isEmpty(url)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "生成链接不能为空");
        } else if (!StringUtil.isUrl(url)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "url参数格式错误");
        }
        // 构建短链对象
        UrlModel urlModel = buildUrl(request);
        if (urlModel == null) {
            // 限制插入
            return new ReturnT<>(ReturnT.FAIL_CODE, "限制生成短链");
        }
        // 给跳转URL不带http://头的地址做简单补全
        url = URLUtil.normalize(url);
        urlModel.setDefaultUrl(url);
        Integer count = indexService.addUrl(urlModel);
        if (count > 0) {
            // 插入成功
            return new ReturnT<>(urlModel.getCode());
        } else {
            // 插入失败
            return ReturnT.FAIL;
        }
    }

    /**
     * 短链还原真实连接
     * @param request
     * @param code
     * @return
     */
    @GetMapping("/restore")
    @ResponseBody
    public ReturnT<String> restore(HttpServletRequest request) throws Exception {
        String shortUrl = request.getQueryString();
        if (StringUtil.isEmpty(shortUrl)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "链接不能为空");
        } else if (!shortUrl.startsWith(serverPath)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "url格式错误");
        }
        UrlModel urlModel = indexService.loadUrlByShortUrl(shortUrl);
        if (urlModel != null) {
            // 还原成功
            return new ReturnT<>(urlModel.getDefaultUrl());
        } else {
            // 还原失败
            return ReturnT.FAIL;
        }
    }

    @GetMapping("/img/{code}")
    public void qrCode(HttpServletRequest request, HttpServletResponse response, @PathVariable("code") String code) throws Exception {
        // 写入请求消息
        messageManager.sendMessage(request, MessageManager.MESSAGE_IMG_TYPE);
        // 获取url配置信息
        UrlModel urlModel = RedisUtil.get(URL_MODEL_PREFIX + code, UrlModel.class);
        if (urlModel == null) {
            urlModel = indexService.loadUrlByCode(code);
            if (urlModel == null) {
                urlModel = new UrlModel();
            }
            RedisUtil.set(URL_MODEL_PREFIX + code, urlModel, cacheTime);
        }

        if(!StringUtil.isEmpty(urlModel.getCode())){
            BufferedImage bi;
            if (urlModel.getMulitClients() == 0) {
                // 不支持多客户端
                bi = QrCodeUtil.generate(urlModel.getDefaultUrl(), 300, 300);
            } else {
                // 支持多客户端不同跳转
                String userAgent = request.getHeader("User-Agent");
                if (StringUtil.isEmpty(userAgent)) {
                    bi = QrCodeUtil.generate(urlModel.getDefaultUrl(), 300, 300);
                } else {
                    UserAgentInfo userAgentInfo = UserAgentUtil.parse(userAgent);
                    if ("Windows".equals(userAgentInfo.getOsFamily()) && !StringUtil.isEmpty(urlModel.getPcUrl())) {
                        bi = QrCodeUtil.generate(urlModel.getPcUrl(), 300, 300);
                    } else if ("Mac".equals(userAgentInfo.getOsFamily()) && !StringUtil.isEmpty(urlModel.getAppleUrl())) {
                        bi = QrCodeUtil.generate(urlModel.getAppleUrl(), 300, 300);
                    } else {
                        bi = QrCodeUtil.generate(urlModel.getDefaultUrl(), 300, 300);
                    }
                }
            }
            IOUtil.outputImage(response, bi);
        }
    }

    /**
     * 短链跳转，可以带参数跳转
     * @param request
     * @param code
     * @return
     */
    @GetMapping("/{code}")
    public String redirect(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable("code") String code) throws Exception {
        Map<String, Object> params = WebUtil.getReqMap(request);
        if (params != null && !params.isEmpty()) {
            //传递参数
            redirectAttributes.addAllAttributes(params);
        }
        // 写入请求消息
        messageManager.sendMessage(request, MessageManager.MESSAGE_CODE_TYPE);
        // 获取url配置信息
        UrlModel urlModel = RedisUtil.get(URL_MODEL_PREFIX + code, UrlModel.class);
        if (urlModel == null) {
            urlModel = indexService.loadUrlByCode(code);
            if (urlModel == null) {
                urlModel = new UrlModel();
            }
            RedisUtil.set(URL_MODEL_PREFIX + code, urlModel, cacheTime);
        }
        if (!StringUtil.isEmpty(urlModel.getCode())) {
            if (urlModel.getMulitClients() == 0) {
                // 不支持多客户端
                return "redirect:" + urlModel.getDefaultUrl();
            } else {
                // 支持多客户端不同跳转
                String userAgent = request.getHeader("User-Agent");
                if (StringUtil.isEmpty(userAgent)) {
                    return "redirect:" + urlModel.getDefaultUrl();
                } else {
                    UserAgentInfo userAgentInfo = UserAgentUtil.parse(userAgent);
                    if ("Windows".equals(userAgentInfo.getOsFamily()) && !StringUtil.isEmpty(urlModel.getPcUrl())) {
                        return "redirect:" + urlModel.getPcUrl();
                    } else if ("Mac".equals(userAgentInfo.getOsFamily()) && !StringUtil.isEmpty(urlModel.getAppleUrl())) {
                        return "redirect:" + urlModel.getAppleUrl();
                    } else {
                        return "redirect:" + urlModel.getDefaultUrl();
                    }
                }
            }
        } else {
            return "redirect:" + noCodeRedirect;
        }
    }

    /**
     * 访问者链接生成
     * 默认不支持不同浏览器跳转不同页面
     * 默认有效期为3天
     * @return
     */
    private UrlModel buildUrl(HttpServletRequest request){
        UrlModel urlModel = new UrlModel();
        String code = CodeUtil.getCode();
        urlModel.setCode(code);
        urlModel.setShortUrl(serverPath + code);
        urlModel.setMulitClients(0);
        urlModel.setCreateUserCode("-1");
        urlModel.setCreateTime(new Date());
        Date now = new Date();
        // 有效期3天 单位（毫秒）
        now.setTime(now.getTime() + 3 * 86400L * 1000);
        urlModel.setInvalidTime(now);
        return urlModel;
    }

}

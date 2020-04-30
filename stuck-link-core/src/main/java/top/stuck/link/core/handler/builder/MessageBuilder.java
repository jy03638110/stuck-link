package top.stuck.link.core.handler.builder;

import cz.mallat.uasparser.UserAgentInfo;
import top.stuck.link.core.model.MessageModel;
import top.stuck.link.core.utils.UserAgentUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public class MessageBuilder implements Builder<MessageModel> {

    public MessageModel build(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        MessageModel model = new MessageModel();
        model.setRequestUrl(request.getRequestURI());
        model.setQueryString(request.getQueryString());
        model.setReferer(request.getHeader("referer"));
        model.setLocalName(request.getLocalName());
        model.setLocalAddr(request.getLocalAddr());
        model.setRemoteHost(request.getRemoteHost());
        model.setRemoteAddr(request.getRemoteAddr());
        model.setRemoteUser(request.getRemoteUser());
        model.setUserAgent(request.getHeader("User-Agent"));
        if (model.getUserAgent() != null) {
            UserAgentInfo userAgentInfo = UserAgentUtil.parse(model.getUserAgent());
            model.setOsFamily(userAgentInfo.getOsFamily());
            model.setOsName(userAgentInfo.getOsName());
            model.setUaName(userAgentInfo.getUaName());
            model.setUaType(userAgentInfo.getType());
            model.setUaFamily(userAgentInfo.getUaFamily());
            model.setBrowserVersion(userAgentInfo.getBrowserVersionInfo());
            model.setDeviceType(userAgentInfo.getDeviceType());
        }
        model.setVisitTime(new Date());
        return model;
    }
}

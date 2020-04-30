package top.stuck.link.redirect.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.stuck.link.core.exception.ProducerException;
import top.stuck.link.core.handler.producer.Producer;
import top.stuck.link.core.model.MessageModel;
import top.stuck.link.core.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 消息管理工具
 * Created on 2020-04-27
 *
 * @author Octopus
 */
@Component("messageManager")
public class MessageManager {

    private Logger logger = LoggerFactory.getLogger(MessageManager.class);

    public static final Integer MESSAGE_CODE_TYPE = 1;

    public static final Integer MESSAGE_IMG_TYPE = 2;

    public static final Integer MESSAGE_ANONYMOUS_TYPE = 3;

    @Autowired
    private Producer<MessageModel> producer;

    public void sendMessage(HttpServletRequest request, Integer type) {
        MessageModel messageModel = producer.build(request);
        messageModel.setMessageType(type);
        if (validate(messageModel)) {
            return;
        }
        try {
            boolean flag = producer.send(messageModel);
            if (!flag) {
                logger.info("【生产者" + Thread.currentThread().getName() + "】写入消息失败");
            }
        } catch (ProducerException e) {
            logger.error("消息生产失败！", e);
        }
        try{
            logger.debug("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + producer.length());
        } catch (ProducerException e) {
            logger.debug("暂不支持获取消息总数", e);
        }
    }

    /**
     * 验证消息是否需要消费
     * @param messageModel
     * @return
     */
    private boolean validate(MessageModel messageModel) {
        // 消息类型不能为空
        if (messageModel.getMessageType() == null) {
            return true;
        }
        // 远程地址不为空
        if (StringUtil.isEmpty(messageModel.getRemoteAddr()) || StringUtil.isEmpty(messageModel.getRemoteHost())) {
            return true;
        }
        return false;
    }
}

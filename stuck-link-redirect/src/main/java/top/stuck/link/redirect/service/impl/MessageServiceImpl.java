package top.stuck.link.redirect.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.stuck.link.redirect.mapper.MessageMapper;
import top.stuck.link.redirect.service.MessageService;
import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
@Service("messageService")
public class MessageServiceImpl implements MessageService {

    private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Integer addMessage(MessageModel messageModel) {
        if (messageModel == null) {
            return 0;
        }
        return messageMapper.addMessage(messageModel);
    }
}

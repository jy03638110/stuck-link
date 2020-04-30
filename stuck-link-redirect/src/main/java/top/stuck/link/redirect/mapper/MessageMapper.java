package top.stuck.link.redirect.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.stuck.link.core.model.MessageModel;

/**
 * Created on 2020-04-27
 *
 * @author Octopus
 */
@Mapper
public interface MessageMapper {

    Integer addMessage(MessageModel message);
}

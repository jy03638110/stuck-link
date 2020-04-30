package top.stuck.link.core.handler.builder;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
public interface Builder<T> {

    T build(HttpServletRequest request);
}

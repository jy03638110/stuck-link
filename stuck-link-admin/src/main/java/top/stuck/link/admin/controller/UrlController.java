package top.stuck.link.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.stuck.link.admin.service.UrlService;

/**
 * Created on 2020-04-28
 *
 * @author Octopus
 */
@Controller
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;


}

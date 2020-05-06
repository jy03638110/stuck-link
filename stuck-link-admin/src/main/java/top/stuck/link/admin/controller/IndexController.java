package top.stuck.link.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created on 2019-11-07
 *
 * @author Octopus
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Value("${stuck.admin-address:http://localhost:8080}")
    private String adminAddress;

    @Value("${stuck.record-no:}")
    private String recordNo;

    @GetMapping(value = {"/","/index"})
    public String index(ModelMap mmap) {
        mmap.addAttribute("recordNo", recordNo);
        mmap.addAttribute("adminAddress", adminAddress);
        return "index";
    }
}

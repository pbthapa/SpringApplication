/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.controller;

import com.yomari.telecom.model.User;
import com.yomari.telecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Dell
 */
@RequestMapping("/")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    MessageSource messageSource;

    @RequestMapping("/")
    public String loginForm(Model uiModel) {
        //QkPIOPHPZDhPCcWUjw94vF/b/VJUmtWZw4bOEzdu+o4U79T+Zi4Ibtgb5WRyMdfbd9YzaDJWXENMT32q8V4kWQ==
        User user = new User();
        uiModel.addAttribute("user", user);
        return "admin/welcome";
    }
//    @RequestMapping(value = "/login", params = "form", method = RequestMethod.POST)
//    public String login(@Valid User user, BindingResult bindingResult, Model uiModel,
//            HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
//        if (bindingResult.hasErrors()) {
//            uiModel.addAttribute("message", new Message("error", "Unable to Login"));
//            uiModel.addAttribute("user", user);
//            return "admin/welcome";
//        }
//        uiModel.asMap().clear();
//        User u = userService.findUserByCredentials(user);
//        if (u != null) {
//            return "redirect:/contacts/";
//        } else {
//            uiModel.addAttribute("message", new Message("error", "Bad username or password"));
//            uiModel.addAttribute("user", user);
//            return "admin/welcome";
//        }
//    }
}

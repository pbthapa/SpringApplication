/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.controller;

import com.google.common.collect.Lists;
import com.yomari.telecom.commons.DataGrid;
import com.yomari.telecom.commons.Message;
import com.yomari.telecom.commons.UrlUtil;
import com.yomari.telecom.model.RoleDetail;
import com.yomari.telecom.model.RoleGroup;
import com.yomari.telecom.model.User;
import com.yomari.telecom.service.RoleGroupService;
import com.yomari.telecom.service.RoleService;
import com.yomari.telecom.service.UserService;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author pushpa
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleGroupService roleGroupService;
    @Autowired
    private UserService userService;
    @Autowired
    MessageSource messageSource;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        return "role/rolelist";
    }

    @RequestMapping(value = "/listRoleDetail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<RoleDetail> listRoleDetail() {
        List<RoleDetail> roleDetails = roleService.findAll();
        //uiModel.addAttribute("role", role);
        return roleDetails;
    }

    @RequestMapping(value = "/editRoleDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RoleDetail editRoleDetail(@RequestParam(value = "id") Integer id, Model uiModel) {
        RoleDetail role = roleService.findById(id);
        //uiModel.addAttribute("role", role);
        return role;
    }

    @RequestMapping(value = "/updateRoleDetail", method = RequestMethod.POST)
    @ResponseBody
    public String updateRoleDetail(@Valid RoleDetail roleDetail, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Locale locale) {
        // redirectAttributes.addFlashAttribute("message", "Role Detail Updated Successfully");
        roleService.save(roleDetail);
        return "success";
    }

    @RequestMapping(value = "/deleteRoleDetail", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRoleDetail(@RequestParam(value = "id") Integer id) {
        roleService.deleteRoleById(id);
        //uiModel.addAttribute("role", role);
        return "success";
    }

    @RequestMapping(value = "/createRoleDetail", method = RequestMethod.POST)
    @ResponseBody
    public String createRoleDetail(@Valid RoleDetail roleDetail, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Locale locale) {
        roleService.save(roleDetail);
        return "success";
    }

    @RequestMapping(value = "/listrolegrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public DataGrid listRoleGrid(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sortBy,
            @RequestParam(value = "sord", required = false) String order) {
        // Process order by
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }
        }
        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }
        Page<RoleDetail> rolePage = roleService.findAllByPage(pageRequest);
        // Construct the grid data that will return as JSON data
        DataGrid<RoleDetail> roleGrid = new DataGrid<RoleDetail>();
        roleGrid.setCurrentPage(rolePage.getNumber() + 1);
        roleGrid.setTotalPages(rolePage.getTotalPages());
        roleGrid.setTotalRecords(rolePage.getTotalElements());
        roleGrid.setData(Lists.newArrayList(rolePage.iterator()));
        return roleGrid;
    }

    // role group starts here
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/groupList", params = "form", method = RequestMethod.GET)
    public String roleGroupList() {
        return "role/rolegrouplist";
    }

    @RequestMapping(value = "/editRoleGroupDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public RoleGroup editRoleGroupDetail(@RequestParam(value = "id") Integer id, Model uiModel) {
        RoleGroup roleGroup = roleGroupService.findRoleDetailByRoleGroupId(id);
        //uiModel.addAttribute("role", role);
        return roleGroup;
    }

    @RequestMapping(value = "/updateRoleGroupDetail", method = RequestMethod.POST)
    @ResponseBody
    public String updateRoleGroupDetail(@Valid RoleGroup roleGroup, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Locale locale) {
        // redirectAttributes.addFlashAttribute("message", "Role Detail Updated Successfully");
        roleGroupService.save(roleGroup);
        return "success";
    }

    @RequestMapping(value = "/deleteRoleGroupDetail", method = RequestMethod.POST)
    @ResponseBody
    public String deleteRoleGroupDetail(@RequestParam(value = "id") Integer id) {
        roleGroupService.deleteRoleGroupById(id);
        //uiModel.addAttribute("role", role);
        return "success";
    }

    @RequestMapping(value = "/createRoleGroupDetail", method = RequestMethod.POST)
    @ResponseBody
    public String createRoleGroupDetail(@Valid RoleGroup roleGroup, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Locale locale) {
        roleGroupService.save(roleGroup);
        return "success";
    }

    @RequestMapping(value = "/addEditRoleGroup", method = RequestMethod.POST)
    @ResponseBody
    public boolean addEditRoleGroup(RoleGroup roleGroup) {
        Set<RoleDetail> roleDetails = new HashSet<RoleDetail>();
        for (BigDecimal detailId : roleGroup.getRoleDetailIds()) {
            RoleDetail roleDetail = new RoleDetail();
            roleDetail.setId(detailId.intValue());
            roleDetails.add(roleDetail);
        }
        roleGroup.setRoleDetails(roleDetails);
        RoleGroup roleGroupSaved = roleGroupService.save(roleGroup);

        if (roleGroupSaved == null) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/listrolegroupgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public DataGrid listRoleGroupGrid(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sortBy,
            @RequestParam(value = "sord", required = false) String order) {
        // Process order by
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }
        }
        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }
        Page<RoleGroup> roleGroupPage = roleGroupService.findAllByPage(pageRequest);
        // Construct the grid data that will return as JSON data
        DataGrid<RoleGroup> roleGroupGrid = new DataGrid<RoleGroup>();
        roleGroupGrid.setCurrentPage(roleGroupPage.getNumber() + 1);
        roleGroupGrid.setTotalPages(roleGroupPage.getTotalPages());
        roleGroupGrid.setTotalRecords(roleGroupPage.getTotalElements());
        roleGroupGrid.setData(Lists.newArrayList(roleGroupPage.iterator()));
        return roleGroupGrid;
    }

    // User Management stays here
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/userList", params = "form", method = RequestMethod.GET)
    public String listUser(Model uiModel) {
        User user = new User();
        uiModel.addAttribute("user", user);
        return "role/userlist";
    }

    @RequestMapping(value = "/listusergrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public DataGrid listUserGrid(@RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sortBy,
            @RequestParam(value = "sord", required = false) String order) {
        // Process order by
        Sort sort = null;
        String orderBy = sortBy;

        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }
        }
        // Constructs page request for current page
        // Note: page number for Spring Data JPA starts with 0, while jqGrid starts with 1
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest(page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest(page - 1, rows);
        }
        Page<User> userPage = userService.findAllByPage(pageRequest);
        // Construct the grid data that will return as JSON data
        DataGrid<User> userGrid = new DataGrid<User>();
        userGrid.setCurrentPage(userPage.getNumber() + 1);
        userGrid.setTotalPages(userPage.getTotalPages());
        userGrid.setTotalRecords(userPage.getTotalElements());
        userGrid.setData(Lists.newArrayList(userPage.iterator()));
        return userGrid;
    }
}

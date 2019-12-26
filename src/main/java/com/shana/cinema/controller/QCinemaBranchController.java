package com.shana.cinema.controller;

import com.shana.cinema.pojo.CinemaBranch;
import com.shana.cinema.service.QICinemaBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/24
 * @since 1.0.0
 */
@Controller
@RequestMapping("/qbranch")
public class QCinemaBranchController {
    @Autowired
    private QICinemaBranchService branchService;

    @RequestMapping("/showonebranch/{cdid}")
    @ResponseBody
    public CinemaBranch showonebranch(@PathVariable(value = "cdid") int id){
        return branchService.findBranchByCdid(id);
    }

}

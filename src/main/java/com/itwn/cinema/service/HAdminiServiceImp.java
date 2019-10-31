package com.itwn.cinema.service;

import com.itwn.cinema.mapper.AdministratorMapper;
import com.itwn.cinema.mapper.RoleMapper;
import com.itwn.cinema.pojo.Administrator;
import com.itwn.cinema.pojo.Role;
import com.itwn.cinema.pojo.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/19
 * @since 1.0.0
 */
@Service
@Transactional
public class HAdminiServiceImp implements HIAdminService {

    @Autowired
    AdministratorMapper administratorMapper;
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Administrator login(Administrator administrator) {
        return administratorMapper.selectByUnameAndUpass(administrator);
    }

    //查询所有员工
    @Override
    public List<Administrator> showAllAdmin(){

        return administratorMapper.selectAll();
    }

    @Override
    public void addAdmin(Administrator admin) {
        administratorMapper.insert(admin);
    }

    @Override
    public List<Roles> showAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public void setAdmin(Administrator admin) {
        administratorMapper.updateById(admin);
    }

    @Override
    public Administrator findAdminById(int aid) {
        return administratorMapper.selectByPrimaryKey(aid);
    }

    @Override
    public void removeAdmin(int aid) {
        administratorMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public Administrator findByAname(String aname) {
        return administratorMapper.selectByAname(aname);
    }
}


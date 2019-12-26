package com.shana.cinema.service;

import com.shana.cinema.pojo.Administrator;
import com.shana.cinema.pojo.Roles;

import java.util.List;

public interface HIAdminService {

    Administrator login(Administrator administrator);

    //查询所有管理元
    public List<Administrator> showAllAdmin();

    void addAdmin(Administrator admin);

    List<Roles> showAllRole();

    void setAdmin(Administrator admin);

    Administrator findAdminById(int aid);

    void removeAdmin(int aid);

    Administrator findByAname(String aname);
}

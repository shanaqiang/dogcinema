package com.itwn.cinema.service;

import com.itwn.cinema.pojo.Administrator;
import com.itwn.cinema.pojo.Role;
import com.itwn.cinema.pojo.Roles;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

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

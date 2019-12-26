package com.shana.cinema.service;

import com.shana.cinema.mapper.AdevertisementMapper;
import com.shana.cinema.pojo.Adevertisement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2019/10/27
 * @since 1.0.0
 */
@Service
@Transactional
public class HAdserviceImpl implements HIAdservice {
    @Autowired
    private AdevertisementMapper adMapper;

    @Override
    public List<Adevertisement> findAll() {
        return adMapper.selectAll();
    }
}

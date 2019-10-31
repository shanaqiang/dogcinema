package com.itwn.cinema.service;

import com.itwn.cinema.mapper.CinemaBranchMapper;
import com.itwn.cinema.pojo.CinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author xiana
 * @create 2019/10/21
 * @since 1.0.0
 */
@Service
@Transactional
public class HCinemaBranchServiceImp implements HICinemaBranchService {
    @Autowired
    CinemaBranchMapper cinemaBranchMapper;

    @Override
    public List<CinemaBranch> findAll() {
        List<CinemaBranch> cinemaBranches = cinemaBranchMapper.selectAll();
        return cinemaBranches;
    }

    @Override
    public void delecteByCdid(Integer cdid) {
        cinemaBranchMapper.delecteByCdid(cdid);
        cinemaBranchMapper.deleteScreenFilmByCdid(cdid);
        cinemaBranchMapper.deleteScreensBycdid(cdid);
    }

    @Override
    public CinemaBranch findByCbid(int cbid) {
        return cinemaBranchMapper.selectByPrimaryKey(cbid);
    }

    @Override
    public CinemaBranch selectByPrimaryKey(Integer cdid) {
        return cinemaBranchMapper.selectByPrimaryKey(cdid);
    }


    @Override
    public void updataBrand(CinemaBranch cinemaBranch) {
        System.out.println(cinemaBranch.getStatus());
        if(cinemaBranch.getStatus()==1){
            if(cinemaBranch.getAddress().contains("省")){
                cinemaBranch.setProvince(cinemaBranch.getAddress().split("省")[0]+"省");
                cinemaBranch.setCity(cinemaBranch.getAddress().split("省")[1].split("市")[0]+"市");
                cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[1]);
            }else{
                cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[0]+"市");
                cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[1]);
            }
            cinemaBranchMapper.updataBrand(cinemaBranch);
        }else{
            if(cinemaBranch.getAddress().contains("省")){
                cinemaBranch.setProvince(cinemaBranch.getAddress().split("省")[0]+"省");
                cinemaBranch.setCity(cinemaBranch.getAddress().split("省")[1].split("市")[0]+"市");
                cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[1]);
            }else{
                cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[0]+"市");
                cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[1]);
            }
            cinemaBranchMapper.updataBrand(cinemaBranch);
            //修改对应影院影厅的状态
        }
    }

    @Override
    public void insertbrand(CinemaBranch cinemaBranch) {
        if(cinemaBranch.getAddress().contains("省")){
            cinemaBranch.setProvince(cinemaBranch.getAddress().split("省")[0]+"省");
            cinemaBranch.setCity(cinemaBranch.getAddress().split("省")[1].split("市")[0]);
            cinemaBranch.setArea(cinemaBranch.getAddress().split("市")[1]);
        }else{
            cinemaBranch.setCity(cinemaBranch.getAddress().split("市")[0]+"市");
            cinemaBranch.setArea(cinemaBranch.getAddress().split("市")[1]);
        }
        cinemaBranchMapper.insertbrand(cinemaBranch);
    }


}


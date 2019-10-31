package com.itwn.cinema.service;

import com.itwn.cinema.mapper.ScreenChairMapper;
import com.itwn.cinema.mapper.ScreenFilmMapper;
import com.itwn.cinema.mapper.ScreenMapper;
import com.itwn.cinema.mapper.ScreenSeatsMapper;
import com.itwn.cinema.pojo.ScreenChair;
import com.itwn.cinema.pojo.ScreenFilm;
import com.itwn.cinema.pojo.ScreenSeats;
import com.itwn.cinema.pojo.Screens;
import javafx.stage.Screen;
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
public class HScreenServiceImp implements HIScreenService {

    @Autowired
    ScreenMapper screenMapper;
    @Autowired
    ScreenChairMapper screenChairMapper;
    @Autowired
    ScreenFilmMapper screenFilmMapper;
    @Autowired
    ScreenSeatsMapper screenSeatsMapper;

    @Override
    public void addScreen(Screens screens) {
        screenMapper.insertScreen(screens);
    }

    @Override
    public Screens findScreenBySname(String sname) {
        return screenMapper.selectBySname(sname);
    }

    @Override
    public List<Screens> findScreensByCbid(int cbid) {
        return screenMapper.selectByCbid(cbid);
    }

    @Override
    public Screens findScreenBySid(int sid) {
        return screenMapper.selectBySid(sid);
    }

    @Override
    public void addMuchChairs(List<ScreenChair> screenChairs) {
        if(screenChairMapper.selectCountBySid(screenChairs.get(0).getSid())>0){
            screenChairMapper.deleteBySid(screenChairs.get(0).getSid());
        }
        for (int i=0;i<screenChairs.size();i++){
            screenChairMapper.insert(screenChairs.get(i));
        }
    }

    @Override
    public List<ScreenChair> findScreenChairBySid(int sid) {
        return screenChairMapper.selectBySid(sid);
    }

    @Override
    public List<Screen> findScreenBySnameAndCbid(String sname, int cbid) {
        return screenMapper.selectBySnameAndCbid(sname,cbid);
    }

    @Override
    public List<Screens> findAll() {
        return screenMapper.selectAll();
    }

    @Override
    public void changeScreeenStatusBySid(int sid,int status) {
        screenMapper.changeScreenStatusBySid(sid,status);
    }

    @Override
    public ScreenFilm findScreenFilmBySfid(int sfid) {
        return screenFilmMapper.selectByPrimaryKey(sfid);
    }

    @Override
    public void AddScreenSeats(ScreenSeats screenSeats) {
        screenSeatsMapper.insert(screenSeats);
    }

    @Override
    public List<Screens> findByCbidAndStatus(int cbid) {
        return screenMapper.selectByCbidAndStatus(cbid);
    }

    @Override
    public List<ScreenFilm> findScreenFilmByFid(int fid) {
        return screenFilmMapper.selectScreenFilmByFid(fid);
    }
}


package com.tongyuan.model.service.impl;


import com.github.pagehelper.PageHelper;
import com.tongyuan.model.dao.LearnMapper;
import com.tongyuan.model.domain.LearnResouce;
import com.tongyuan.model.service.LearnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by tengj on 2017/4/7.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    private LearnMapper learnMapper;

    //这里的单引号不能少，否则会报错，被识别是一个对象;
    public static final String CACHE_KEY = "'demoInfo'";

    /**
     * value属性表示使用哪个缓存策略，缓存策略在ehcache.xml
     */
    public static final String DEMO_CACHE_NAME = "demo";

    @Override
    @CacheEvict(value=DEMO_CACHE_NAME,key=CACHE_KEY)
    public int add(LearnResouce learnResouce) {
        return this.learnMapper.add(learnResouce);
    }

    @Override
    @CachePut(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#updated.getId()")
    public int update(LearnResouce learnResouce) {
        return this.learnMapper.update(learnResouce);
    }

    @Override
    @CacheEvict(value = DEMO_CACHE_NAME,key = "'demoInfo_'+#ids")//这是清除缓存.
    public int deleteByIds(String[] ids) {
        return this.learnMapper.deleteByIds(ids);
    }

    @Override
    @Cacheable(value=DEMO_CACHE_NAME,key="'demoInfo_'+#id")
    public LearnResouce queryLearnResouceById(Long id) {
        return this.learnMapper.queryLearnResouceById(id);
    }

    @Override
    public List<LearnResouce> queryLearnResouceList(Map<String,Object> params) {
        PageHelper.startPage(Integer.parseInt(params.get("page").toString()), Integer.parseInt(params.get("rows").toString()));
        return this.learnMapper.queryLearnResouceList(params);
    }
}

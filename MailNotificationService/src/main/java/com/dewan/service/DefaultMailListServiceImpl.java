package com.dewan.service;

import com.dewan.entity.DefaultMailListEntity;
import com.dewan.repository.DefaultMailListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMailListServiceImpl implements DefaultMailListService{
    @Autowired
    DefaultMailListRepository defaultMailListRepository;

    @Override
    public DefaultMailListEntity saveDefaultMailList(DefaultMailListEntity defaultMailListEntity) {
        return defaultMailListRepository.save(defaultMailListEntity);
    }

    @Override
    public DefaultMailListEntity findDefaultMailListEntityByUserid(Long userid) {
        return defaultMailListRepository.findDefaultMailListEntityByUserid(userid);
    }
}

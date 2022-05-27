package com.dewan.service;

import com.dewan.entity.DefaultMailListEntity;
import org.springframework.stereotype.Service;

@Service
public interface DefaultMailListService {

    DefaultMailListEntity saveDefaultMailList(DefaultMailListEntity defaultMailListEntity);
    DefaultMailListEntity findDefaultMailListEntityByUserid(Long userid);
}

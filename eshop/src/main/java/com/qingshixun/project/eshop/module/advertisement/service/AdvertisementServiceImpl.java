package com.qingshixun.project.eshop.module.advertisement.service;

import com.qingshixun.project.eshop.dto.AdvertisementDTO;
import com.qingshixun.project.eshop.module.advertisement.dao.AdvertisementDaoMyBatis;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementServiceImpl extends BaseService {

    @Autowired
    private AdvertisementDaoMyBatis advertisementDao;

    /**
     * 获取已启动的广告列表
     */
    public List<AdvertisementDTO> getAdvertisements() {
        return advertisementDao.getAdvertisements();
    }

}

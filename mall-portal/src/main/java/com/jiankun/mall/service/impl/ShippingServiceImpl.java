package com.jiankun.mall.service.impl;

import com.jiankun.mall.mapper.AreaMapper;
import com.jiankun.mall.mapper.CityMapper;
import com.jiankun.mall.mapper.ProvinceMapper;
import com.jiankun.mall.mapper.ShippingMapper;
import com.jiankun.mall.pojo.Area;
import com.jiankun.mall.pojo.City;
import com.jiankun.mall.pojo.Province;
import com.jiankun.mall.pojo.Shipping;
import com.jiankun.mall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/17 8:46
 */
@Service
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Shipping> list(Integer userId) {
        return shippingMapper.list(userId);
    }

    @Override
    public List<Province> selectProvince() {
        return provinceMapper.list();
    }

    @Override
    public List<City> selectCity(Integer provinceId) {
        return cityMapper.list(provinceId);
    }

    @Override
    public List<Area> selectArea(Integer cityId) {
        return areaMapper.list(cityId);
    }

    @Override
    public void add(Shipping shipping) {
        shippingMapper.insertSelective(shipping);
    }

    @Override
    public void delete(Integer id) {
        shippingMapper.deleteByPrimaryKey(id);
    }
}

package com.yx.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.Size;
import com.yx.dao.SizeDao;
import com.yx.service.SizeService;
import com.yx.yxcommon.api.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/19 13:55
 */
@Service
public class SizeServiceImpl extends ServiceImpl<SizeDao, Size> implements SizeService {

    @Resource
    SizeDao sizeDao;

    @Override
    public Result add(Size size) {
        return sizeDao.insert(size) > 0 ? Result.ok() : Result.fail();
    }

    @Override
    public Result list() {
        return Result.ok(sizeDao.selectList(new EntityWrapper<>()));
    }

    @Override
    public Result edit(Size size) {
        return Result.ok(sizeDao.update(size,new EntityWrapper<>()));
    }
}

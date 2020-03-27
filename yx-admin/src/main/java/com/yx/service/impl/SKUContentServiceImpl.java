package com.yx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.SKUContent;
import com.yx.dao.SKUContentDao;
import com.yx.service.SKUContentService;
import org.springframework.stereotype.Service;


/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/27 18:13
 */
@Service("skuContentService")
public class SKUContentServiceImpl extends ServiceImpl<SKUContentDao, SKUContent> implements SKUContentService {
}

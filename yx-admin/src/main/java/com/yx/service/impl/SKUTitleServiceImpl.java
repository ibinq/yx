package com.yx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yx.bean.SKUTitle;
import com.yx.dao.SKUTitleDao;
import com.yx.service.SKUTitleService;
import org.springframework.stereotype.Service;


/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/27 18:13
 */
@Service("skuTitleService")
public class SKUTitleServiceImpl extends ServiceImpl<SKUTitleDao, SKUTitle> implements SKUTitleService {
}

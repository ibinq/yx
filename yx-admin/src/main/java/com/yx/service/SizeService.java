package com.yx.service;

import com.baomidou.mybatisplus.service.IService;
import com.yx.bean.Size;
import com.yx.yxcommon.api.Result;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/19 13:55
 */
public interface SizeService  extends IService<Size> {
    Result add(Size size);

    Result list();

    Result edit(Size size);
}

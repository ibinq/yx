package com.yx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yx.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ZhouBing
 * @version 1.0
 * @date 2020/3/13 16:39
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}

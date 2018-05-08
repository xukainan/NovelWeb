package com.papamh.service;
import java.util.List;

import com.papamh.pojo.User;

public interface UserService {
    /**
     * 根据传入名称获取数据
     * @param name 名称
     * @return 实体类
     */
    public List<User> queryUserByUserName(String username);
}
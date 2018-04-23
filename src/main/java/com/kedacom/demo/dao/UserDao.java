package com.kedacom.demo.dao;

import java.util.List;

import com.kedacom.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户管理dao层接口
 * @author 钱其清
 */
@Repository
public interface UserDao {
    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    User selectByName(String name);

    /**
     * 更新
     * @param user
     * @return
     */
    int updateByPrimaryKey(User user);

    /**
     * 用户列表
     * @return
     */
    List<User> getAllUser();

    /**
     * 在线用户列表
     * @return
     */
    List<User> getOnlineUser();

    /**
     * 条件查询用户列表
     * @param name
     * @param status
     * @return
     */
    List<User> getQueryUser(@Param("userName") String name, @Param("userStatus") Integer status);

    /**
     * 查询单个分组下的用户列表
     * @param groupId
     * @return
     */
    List<User> selectByGroupId(Integer groupId);
}
package com.kedacom.demo.dao;

import com.kedacom.demo.model.UserGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户分组关联表操作
 * @Author 钱其清
 */
@Repository
public interface UserGroupDao {
    /**
     * 删除关联数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入关联关系
     * @param record
     * @return
     */
    int insert(UserGroup record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    UserGroup selectByPrimaryKey(Integer id);

    /**
     * 根据分组id查找关联关系
     * @param groupId
     * @return
     */
    List<UserGroup> selectByGroupId(Integer groupId);

    /**
     * 根据用户id查找关联关系
     * @param userId
     * @return
     */
    List<UserGroup> selectByUserId(Integer userId);

    /**
     * 更新关联关系
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserGroup record);
}

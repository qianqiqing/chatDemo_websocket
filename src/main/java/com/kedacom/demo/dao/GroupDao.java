package com.kedacom.demo.dao;

import com.kedacom.demo.model.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分组管理底层操作接口
 * @Author 钱其清
 */
@Repository
public interface GroupDao {
    /**
     * 所有分组
     * @return
     */
    List<Group> getGroupList();

    /**
     * 删除分组
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 创建分组
     * @param record
     * @return
     */
    int insert(Group record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Group selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Group record);
}

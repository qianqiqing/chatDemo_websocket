
import com.kedacom.demo.common.utils.JsonUtil;
import com.kedacom.demo.model.Group;
import com.kedacom.demo.model.GroupTree;
import com.kedacom.demo.service.GroupManageService;
import common.BaseJunit4;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Arrays;
import java.util.List;

/**
 * 分组管理接口测试
 */
public class GroupIntefaceTest extends BaseJunit4 {
    @Autowired
    private GroupManageService groupManageService;

    /**
     * 创建分组测试
     */
    @Test
    public void testCreate() {
        Group group = new Group();
        group = new Group();
        group.setGroupName("test");
        group.setParentId(1);
        groupManageService.create(group);
    }

    /**
     * 分配人员方法测试
     */
    @Test
    public void testAssignUser() {
        groupManageService.assignUser(1, Arrays.asList(1,2));
    }

    /**
     * 获取树结构组数据方法测试
     */
    @Test
    public void testGetTreeData() {
        List<Group> groupList = groupManageService.getGroupList();
        List<GroupTree> tree = groupManageService.getTreeData(groupList);
        System.out.println(JsonUtil.toJson(tree));
    }

    /**
     * 更新方法测试
     */
    @Test
    public void testUpdate() {
        Group updateGroup = groupManageService.getGroupList().get(0);
        updateGroup.setGroupName("test");
        groupManageService.update(updateGroup);
    }

    /**
     * 删除分组方法测试
     */
    @Test
    public void testDelete() {
        groupManageService.deleteGroup(1);
    }
}

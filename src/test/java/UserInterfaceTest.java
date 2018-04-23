import com.kedacom.demo.common.utils.JsonUtil;
import com.kedacom.demo.model.User;
import com.kedacom.demo.service.LoginInfoService;
import com.kedacom.demo.service.UserManageService;
import common.BaseJunit4;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户管理接口测试
 */
public class UserInterfaceTest extends BaseJunit4 {
    @Autowired
    private UserManageService userManageService;

    @Autowired
    LoginInfoService loginInfoService;

    /**
     * 创建或更新接口的测试
     */
    @Test
    public void testCreateOrUpdateUser() {
        User user = new User();
        user.setName("test");
        user.setPassword("test");
        user.setRole(0);
        userManageService.createOrUpdateUser(user);
        user.setId(1);
        user.setName("testUpdate");
        userManageService.createOrUpdateUser(user);
    }

    /**
     * 组可分配人员接口测试
     */
    @Test
    public void testSelectedUser() {
        List<User> userList = userManageService.selectedUser(4);
        System.out.println(JsonUtil.toJson(userList));
    }

    /**
     * 条件查询接口测试
     */
    @Test
    public void testGetUserList() {
        List<User> userList = userManageService.getUserList("qianqiqing",0);
        System.out.println(JsonUtil.toJson(userList));
    }

    /**
     * 登陆验证接口测试
     */
    @Test
    public void testLoginValidate() {
        User user = loginInfoService.validateUser("qianqiqing","123456");
        System.out.println(JsonUtil.toJson(user));
    }
}

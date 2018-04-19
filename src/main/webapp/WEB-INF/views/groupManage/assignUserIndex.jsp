<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="am-popup-inner">

    <div class="am-popup-hd">
        <h4 class="am-popup-title">分配用户</h4>
        <span data-am-modal-close
              class="am-close">
	      </span>
    </div>

    <div class="am-g">
        <div class="am-u-sm-12">
            <table class="am-table am-table-striped am-table-hover table-main">
                <thead>
                <tr>
                    <th class="table-check"><input type="checkbox" /></th>
                    <th class="table-title">用户名</th>
                    <th class="table-title">是否在线</th>
                    <th class="table-title">用户权限</th>
                    <th class="table-title">手机号</th>
                    <th class="table-title">邮箱</th>
                </tr>
                </thead>
                <tbody id="assignBody">

                </tbody>
            </table>
        </div>
    </div>
    <button style="float: bottom" type="button" class="am-btn am-btn-primary am-btn-block" id="edit_save">保存</button>

</div>

<script type="application/javascript">
    GroupManage.initUserList();
</script>
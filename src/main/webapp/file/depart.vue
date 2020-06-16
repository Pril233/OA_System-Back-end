<template>
  <div>
    <!-- 面包屑导航区域 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <!-- to属性里面是访问首页的路由 -->
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>部门管理</el-breadcrumb-item>
      <el-breadcrumb-item>部门列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片区域 -->
    <el-card>
      <!--搜索区域-->
      <el-row :gutter="20">
        <el-col :span="8">
          <el-input placeholder="请输入内容">
            <el-button @click="getUserList" slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="changeDialogVisible">添加部门</el-button>
        </el-col>
      </el-row>
      <!-- 用户列表区 -->
      <!-- :data指定数据源 border添加边框线 stripe隔行变色-->
      <el-table :data="userlist" border="true" stripe="true">
        <!-- 用label来指定列的名称 用prop来指定列的值 -->
        <!-- type='index' 意思是添加索引列 -->
        <el-table-column type="index" label="序号"></el-table-column>
        <el-table-column label="部门名称" prop="username"></el-table-column>
        <el-table-column label="管理员" prop="email"></el-table-column>
        <el-table-column label="电话" prop="mobile"></el-table-column>
        <el-table-column label="操作" width="180">
          <!-- 作用域插槽 -->
          <template>
            <!-- 鼠标点击上去文字提示 -->
            <el-tooltip content="修改" placement="top">
              <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
            </el-tooltip>
            <el-tooltip content="删除" placement="top">
              <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页区
      -->
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pagenum"
        :page-sizes="[1, 2, 3, 9]"
        :page-size="pagesize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total"
      ></el-pagination>
    </el-card>

    <!-- 部门添加对话框区域 -->
    <!--
      :visible.sync="dialogVisible" 弹框是否弹出  在data中dialogVisible = false
      点击添加部门按钮 在按钮事件中把dialogVisible = true
    -->
    <el-dialog title="部门添加" :visible.sync="dialogVisible" width="50%" @close="addDialogClosed">
      <!-- 主内容区 -->
      <!--
        :model="addDepartment" 数据绑定对象 和下面的v-model对应
        :rules="addDepartmentRules" 验证规则对象 和下面的prop对应prop指定引用的规则
        ref="addDepartmentRef" 引用对象 与清空事件有关 @close="addDialogClosed"
        prop对应规则对象的具体规则
      -->
      <el-form :model="addDepartment" :rules="addDepartmentRules" ref="addDepartmentRef">
        <el-form-item label="部门名称" prop="name">
          <el-input v-model="addDepartment.name"></el-input>
        </el-form-item>
        <el-form-item label="管理员" prop="manager">
          <el-input v-model="addDepartment.manager"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="addDepartment.tel"></el-input>
        </el-form-item>
      </el-form>
      <!-- 底部区域 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    // 验证手机号的验证规则
    var checkMobile = (rule, value, cb) => {
      // 验证手机号的正则表达式
      const regMobile = /^[1][3,4,5,7,8][0-9]{9}$/
      if (regMobile.test(value)) {
        // 合法的手机号
        return cb()
      }
      // 非法的手机号
      cb(new Error('请输入正确的手机号'))
    }
    return {
      // 每页显示多少数据
      pagesize: 2,
      // 当前的页数
      pagenum: 1,
      // 在页码区显示有多少条记录
      total: 0,
      // 部门添加是否弹出的绑定属性
      dialogVisible: false,
      // 添加部门弹框的绑定数据
      addDepartment: {
        name: '',
        manager: '',
        tel: ''
      },
      // 验证规则对象
      addDepartmentRules: {
        name: [
          { required: true, message: '部门名称不能为空', trigger: 'blur' }
        ],
        manager: [
          { required: true, message: '管理员名称不能为空', trigger: 'blur' }
        ],
        tel: [
          { required: true, message: '电话不能为空', trigger: 'blur' },
          { validator: checkMobile, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 监听 pagesize 改变的事件
    handleSizeChange(newSize) {},
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {},
    // 部门添加按钮 显示部门添加弹框的事件
    changeDialogVisible() {
      this.dialogVisible = true
    },
    addDialogClosed() {
      // addDepartmentRef是添加弹框的引用对象  使用引用对象清空添加部门的弹框
      this.$refs.addDepartmentRef.resetFields()
    }
  }
}
</script>

<style lang="less" scoped>
.el-table {
  margin-top: 15px;
  font-size: 12px;
}
.el-pagination {
  margin-top: 15px;
}
html,
body,
#app {
  height: 100%;
  margin: 0;
  padding: 0;
}

.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 12px;
}
.el-card {
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.15) !important;
}
</style>

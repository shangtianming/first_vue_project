<template>
	<div>
		<div class="box1">
			<el-container>
				<el-header>
					<div class="title">测试开发平台</div>
					<div class="loginOut">
						<el-popconfirm
							title="是否确认退出登录？"
							@confirm="loginOut"
						>
							<div slot="reference">退出登录</div>
						</el-popconfirm>
					</div>
				</el-header>
				<el-container>
					<el-aside width="200px">
						<el-menu
							:default-active="default_active"
							class="el-menu-vertical-demo"
							background-color="#545c64"
							text-color="#fff"
							active-text-color="#ffd04b"
							:router="true"
							unique-opened
						>
							<el-submenu index="1">
								<template slot="title">
									<i class="el-icon-s-home"></i>
									<span>项目管理</span>
								</template>
								<el-menu-item index="/project"
									>项目列表</el-menu-item
								>
							</el-submenu>
							<el-submenu index="2">
								<template slot="title">
									<i class="el-icon-star-off"></i>
									<span>接口管理</span>
								</template>
								<el-menu-item index="/interface"
									>接口列表</el-menu-item
								>
								<el-menu-item index="2-2"
									>添加接口</el-menu-item
								>
								<el-submenu index="2-3">
									<template slot="title">选项3</template>
									<el-menu-item index="2-3-1"
										>选项1</el-menu-item
									>
								</el-submenu>
							</el-submenu>
							<el-submenu index="3">
								<template slot="title">
									<i class="el-icon-s-help"></i>
									<span>用例管理</span>
								</template>
								<el-menu-item index="/cases"
									>用例列表</el-menu-item
								>
								<el-menu-item index="/cases/edit"
									>用例编辑</el-menu-item
								>
							</el-submenu>
						</el-menu>
					</el-aside>
					<el-main><router-view></router-view></el-main>
				</el-container>
			</el-container>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			default_active: '/project',
		}
	},
	methods: {
		loginOut() {
			window.sessionStorage.removeItem('token')
			window.sessionStorage.removeItem('username')
			this.$router.push('/login')
		},
	},
	mounted() {
		this.default_active = this.$route.path
	},
}
</script> 

<style scoped>
/* .box1 {
	overflow: hidden;
}
.title {
	float: left;
}
 */

.el-header {
	background: #545c64;
	margin-bottom: 5px;
}

.title {
	text-align: left;
	font: normal 25px/60px 'Microsoft YaHei';
	width: 90%;
	float: left;
	color: white;
}

.loginOut {
	float: right;
	width: 10%;
	float: right;
	text-align: center;
	line-height: 60px;
}

.loginOut :hover {
	background: rgb(98, 207, 247);
}

div ::v-deep .el-menu {
	height: 100%;
}

.el-main {
	padding: 10px;
}

.el-aside {
	min-height: 650px;
}
</style>
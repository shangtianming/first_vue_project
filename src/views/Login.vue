<template>
	<div>
		<div class="box">
			<el-card class="box-card">
				<h3 class="title">自动化平台登录</h3>
				<!-- 然后在template中使用定义的规则 -->
				<!-- el-form通过rules绑定校验规则 -->
				<el-form :model="form" :rules="loginRules" ref="loginRef">
					<!-- el-form-ite通过prop制定校验字段 -->
					<el-form-item prop="username">
						<el-input
							placeholder="请输入账号"
							prefix-icon="el-icon-user-solid"
							v-model="form.username"
						></el-input>
					</el-form-item>
					<el-form-item prop="password">
						<el-input
							placeholder="请输入密码"
							prefix-icon="el-icon-lock"
							v-model="form.password"
							:show-password="true"
						></el-input>
					</el-form-item>
					<!-- <el-form-item label="记住账号">
						<el-switch v-model="form.status"></el-switch>
					</el-form-item> -->
					<el-form-item class="btn">
						<el-button type="primary" @click="login"
							>点击登录</el-button
						>
					</el-form-item>
				</el-form>
			</el-card>
		</div>
	</div>
</template>

<script>
export default {
	data() {
		return {
			form: {
				username: 'musen123',
				password: '123456',
				status: false,
			},
			// 先定义字段规则
			loginRules: {
				username: [
					{ required: true, message: '请输入账号', trigger: 'blur' },
				],
				password: [
					{ required: true, message: '请输入密码', trigger: 'blur' },
				],
			},
		}
	},
	methods: {
		login() {
			// 判断是否需要记住账号
			if (this.form.status) {
				window.localStorage.setItem('username', this.form.username)
			} else {
				window.localStorage.removeItem('username')
			}
			// 在el-form标签通过ref属性来设置表单引用对象（见上一步）
			// 在点击登录的处理函数中，通过this.$refs.loginRef获取表单对象，并调用表单对象validate方法进行校验
			this.$refs.loginRef.validate(async (res) => {
				// res为验证的结果，验证通过为true，否则为false
				if (!res) return

				const r = await this.$req.loginApi(this.form)
				if (r.status === 200) {
					this.$message({
						showClose: true,
						message: '登录成功',
						type: 'success',
						duration: 1000,
					})
					const token = r.data.token
					window.sessionStorage.setItem('token', token)

					this.$router.push('/home')
				} else {
					console.log('=====测试')
					this.$message({
						showClose: true,
						message: '登录失败',
						type: 'error',
					})
				}
			})
		},
	},
	// 数据挂载之后执行的钩子函数
	// mounted() {
	// 	console.log('钩子函数mounted')
	// 	this.form.username = window.localStorage.getItem('username')
	// },
}
</script>

<style scoped>
.box {
	width: 100%;
	height: 100%;
	position: fixed;
	background: url(../assets/name.jpg);
}

.box-card {
	width: 35%;
	height: 330px;
	margin: auto;
	margin-top: 100px;
	background-color: rgba(0, 0, 0, 0.2);
	border: 0px;
}

.title {
	color: deepskyblue;
	text-align: center;
}

.btn {
	text-align: center;
}
</style>

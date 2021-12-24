<template>
	<div>
		<!-- <el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>项目管理</el-breadcrumb-item>
			<el-breadcrumb-item>项目列表</el-breadcrumb-item>
		</el-breadcrumb> -->

		<view-titles :t="titles"></view-titles>

		<el-button type="primary" icon="el-icon-plus" size="small" @click="addI"
			>添加接口</el-button
		>
		<el-card>
			<el-table :data="tableData" style="width: 100%">
				<el-table-column type="index" label="序号"> </el-table-column>
				<el-table-column prop="name" label="接口名称" width="180">
				</el-table-column>
				<el-table-column
					prop="create_time"
					label="创建时间"
					width="180"
				>
				</el-table-column>
				<el-table-column prop="desc" label="描述"> </el-table-column>
				<el-table-column prop="project" label="所属项目">
				</el-table-column>
				<el-table-column prop="tester" label="测试"> </el-table-column>
				<el-table-column fixed="right" label="操作" width="100">
					<template slot-scope="scope">
						<el-button
							@click="editI(scope.row)"
							type="text"
							size="small"
							>编辑</el-button
						>
						<el-button
							type="text"
							size="small"
							@click="deleteI(scope.row.id)"
							>删除</el-button
						>
					</template>
				</el-table-column>
			</el-table>
		</el-card>
		<el-pagination
			@size-change="handleSizeChange"
			@current-change="handleCurrentChange"
			:current-page="page"
			:page-sizes="[10, 20, 50, 100]"
			:page-size="size"
			layout="total, sizes, prev, pager, next, jumper"
			:total="total"
		>
		</el-pagination>

		<el-dialog title="编辑接口" :visible.sync="dlgEditI">
			<el-form :model="editData" label-width="100px" size="small ">
				<el-form-item label="接口名称">
					<el-input
						v-model="editData.name"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="接口描述">
					<el-input
						v-model="editData.desc"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="所属项目ID">
					<el-input
						v-model="addData.project_id"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="所属项目">
					<el-input
						v-model="editData.project"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="测试人员">
					<el-input
						v-model="editData.tester"
						autocomplete="off"
					></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dlgEditI = false">取 消</el-button>
				<el-button type="primary" @click="editIter">确 定</el-button>
			</div>
		</el-dialog>

		<el-dialog title="添加接口" :visible.sync="dlgAddI">
			<el-form :model="addData" label-width="100px" size="small ">
				<el-form-item label="接口名称">
					<el-input
						v-model="addData.name"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="接口描述">
					<el-input
						v-model="addData.desc"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="所属项目ID">
					<el-input
						v-model="addData.project_id"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="所属项目名称">
					<el-input
						v-model="addData.project"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="测试人员">
					<el-input
						v-model="addData.tester"
						autocomplete="off"
					></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dlgAddI = false">取 消</el-button>
				<el-button type="primary" @click="addIter">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import BreadCrumb from '@/components/BreadCrumb'

export default {
	data() {
		return {
			titles: ['接口管理', '接口列表'],
			tableData: [],
			page: 1,
			total: 0,
			size: 10,
			dlgEditI: false,
			editData: {},
			dlgAddI: false,
			addData: {},
		}
	},
	methods: {
		// 获取项目
		async getInterfaces() {
			const r = await this.$req.getInterfaces({
				page: this.page,
				size: this.size,
			})
			if (r.status === 200) {
				this.tableData = r.data.results
				this.total = r.data.count
			} else {
				alert('获取项目失败')
			}
		},
		// 每页数据
		handleSizeChange(newSize) {
			this.size = newSize
			this.page = 1
			this.getInterfaces()
		},
		// 翻页
		handleCurrentChange(newPage) {
			this.page = newPage
			this.getInterfaces()
		},
		// 删除项目
		async deleteI(id) {
			const r = await this.$req.deleteInterface(id)
			if (r.status == 204) {
				this.$message({
					showClose: true,
					message: '删除成功',
					type: 'success',
					duration: 1000,
				})
				this.getInterfaces()
			} else {
				this.$message({
					showClose: true,
					message: '删除失败',
					type: 'error',
				})
			}
		},

		editI(data) {
			console.log('点击编辑', typeof data)
			this.dlgEditI = true
			// 浅拷贝，复制的内存地址,data是一个对象。浅拷贝可以避免取消编辑后，数据不能还原的问题
			this.editData = { ...data }
		},
		// 编辑项目
		async editIter() {
			const r = await this.$req.updateInterface(
				this.editData.id,
				this.editData
			)
			if (r.status == 200) {
				this.getInterfaces()
				this.dlgEditI = false
				this.$message({
					showClose: true,
					message: '修改成功',
					type: 'success',
					duration: 1000,
				})
			} else {
				this.$message({
					showClose: true,
					message: '修改失败',
					type: 'error',
				})
			}
		},
		addI() {
			this.dlgAddI = true
		},
		// 添加项目
		async addIter() {
			const r = await this.$req.addInterface(this.addData)
			if (r.status == 201) {
				this.getInterfaces()
				this.dlgAddI = false
				this.$message({
					showClose: true,
					message: '添加成功',
					type: 'success',
					duration: 1000,
				})
				this.addData = {}
			} else {
				this.$message({
					showClose: true,
					message: '添加失败',
					type: 'error',
				})
			}
		},
	},
	// 数据挂载之后执行的钩子函数
	mounted() {
		this.getInterfaces()
	},
	components:{
		"view-titles":BreadCrumb
	}
}
</script> 

<style  scoped>
.el-breadcrumb {
	margin-bottom: 10px;
}
.el-button {
	margin-bottom: 5px;
}
div ::v-deep .el-card__body {
	padding: 0px;
}

div ::v-deep .el-form-item {
	margin-bottom: 10px;
}
</style>
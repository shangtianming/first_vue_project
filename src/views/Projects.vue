<template>
	<div>
		<!-- <el-breadcrumb separator-class="el-icon-arrow-right">
			<el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item>项目管理</el-breadcrumb-item>
			<el-breadcrumb-item>项目列表</el-breadcrumb-item>
		</el-breadcrumb> -->

		<view-titles :t="titles"></view-titles>

		<el-button type="primary" icon="el-icon-plus" size="small" @click="addP"
			>添加项目</el-button
		>
		<el-card>
			<el-table :data="tableData" style="width: 100%">
				<el-table-column type="index" label="序号"> </el-table-column>
				<el-table-column prop="name" label="项目名称" width="180">
				</el-table-column>
				<el-table-column
					prop="create_time"
					label="创建时间"
					width="180"
				>
				</el-table-column>
				<el-table-column prop="desc" label="描述"> </el-table-column>
				<el-table-column prop="leader" label="负责人">
				</el-table-column>
				<el-table-column prop="tester" label="测试"> </el-table-column>
				<el-table-column fixed="right" label="操作" width="100">
					<template slot-scope="scope">
						<el-button
							@click="editP(scope.row)"
							type="text"
							size="small"
							>编辑</el-button
						>
						<el-button
							type="text"
							size="small"
							@click="deleteP(scope.row.id)"
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

		<el-dialog title="编辑项目" :visible.sync="dlgEditP">
			<el-form :model="editData" label-width="80px" size="small ">
				<el-form-item label="项目名称">
					<el-input
						v-model="editData.name"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="项目描述">
					<el-input
						v-model="editData.desc"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="负责人">
					<el-input
						v-model="editData.leader"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="测试人员">
					<el-input
						v-model="editData.tester"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="研发">
					<el-input
						v-model="editData.programmer"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="发布应用">
					<el-input
						v-model="editData.publish_app"
						autocomplete="off"
					></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dlgEditP = false">取 消</el-button>
				<el-button type="primary" @click="editPro">确 定</el-button>
			</div>
		</el-dialog>

		<el-dialog title="添加项目" :visible.sync="dlgAddP">
			<el-form :model="addData" label-width="80px" size="small ">
				<el-form-item label="项目名称">
					<el-input
						v-model="addData.name"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="项目描述">
					<el-input
						v-model="addData.desc"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="负责人">
					<el-input
						v-model="addData.leader"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="测试人员">
					<el-input
						v-model="addData.tester"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="研发">
					<el-input
						v-model="addData.programmer"
						autocomplete="off"
					></el-input>
				</el-form-item>
				<el-form-item label="发布应用">
					<el-input
						v-model="addData.publish_app"
						autocomplete="off"
					></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dlgAddP = false">取 消</el-button>
				<el-button type="primary" @click="addPro">确 定</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import BreadCrumb from '@/components/BreadCrumb'
export default {
	data() {
		return {
			titles: ['项目管理', '项目列表'],
			tableData: [],
			page: 1,
			total: 0,
			size: 10,
			dlgEditP: false,
			editData: {},
			dlgAddP: false,
			addData: {},
		}
	},
	methods: {
		// 获取项目
		async getProjects() {
			const r = await this.$req.getProjects({
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
		// 切换每页数据条数，事件触发page置为1，再获取数据信息
		handleSizeChange(newSize) {
			this.size = newSize
			this.page = 1
			this.getProjects()
		},
		// 翻页
		handleCurrentChange(newPage) {
			this.page = newPage
			this.getProjects()
		},
		// 删除项目
		async deleteP(id) {
			const r = await this.$req.deleteProject(id)
			if (r.status == 204) {
				this.$message({
					showClose: true,
					message: '删除成功',
					type: 'success',
					duration: 1000,
				})
				this.getProjects()
			} else {
				this.$message({
					showClose: true,
					message: '删除失败',
					type: 'error',
				})
			}
		},
		editP(data) {
			console.log('点击编辑', data)
			this.dlgEditP = true
			// 浅拷贝，复制的内存地址,data是一个对象。浅拷贝可以避免取消编辑后，数据不能还原的问题
			this.editData = { ...data }
		},
		// 编辑项目
		async editPro() {
			const r = await this.$req.updateProject(
				this.editData.id,
				this.editData
			)
			if (r.status == 200) {
				this.getProjects()
				this.dlgEditP = false
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
		addP() {
			this.dlgAddP = true
		},
		// 添加项目
		async addPro() {
			const r = await this.$req.addProject(this.addData)
			if (r.status == 201) {
				this.getProjects()
				this.dlgAddP = false
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
		this.getProjects()
	},
	components: {
		"view-titles": BreadCrumb,
	},
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
<template>
	<div class="case_edit">
		<view-titles :t="titles"></view-titles>
		<el-card>
			<el-divider content-position="left">Api</el-divider>
			<!-- 分栏 -->
			<el-row :gutter="20">
				<el-col :span="4">
					<!-- 选择器.v-model是默认选择内容 -->
					<el-select placeholder="请选择" v-model="options_default">
						<!-- v-for的循环如果有index，必须使用index -->
						<el-option
							v-for="(item, index) in options"
							:key="index"
							:label="item"
							:value="item"
						>
						</el-option>
					</el-select>
				</el-col>
				<el-col :span="8">
					<el-input placeholder="请输入内容" v-model="caseInfo.host">
						<template slot="prepend">Host</template>
					</el-input></el-col
				>
				<el-col :span="8">
					<el-input placeholder="请输入内容" v-model="caseInfo.path">
						<template slot="prepend">接口地址</template>
					</el-input></el-col
				>
				<el-col :span="4">
					<el-button type="primary" icon="el-icon-s-promotion"
						>运行</el-button
					></el-col
				>
			</el-row>
			<el-divider content-position="left">Request</el-divider>
			<!-- 标签页 -->
			<el-tabs type="card">
				<el-tab-pane label="请求头">
					<!-- 分栏，可以v-for使得里面的内容循环 -->
					<el-row
						:gutter="20"
						v-for="(item, index) in caseInfo.headers"
						:key="index"
						style="margin-bottom: 10px"
					>
						<el-col :span="6"
							><div class="grid-content bg-purple">
								<!-- 很多标签都有size属性控制大小 -->
								<el-input
									placeholder="KEY"
									size="mini"
									v-model.lazy="item.key"
								></el-input></div
						></el-col>
						<el-col :span="12"
							><div class="grid-content bg-purple">
								<el-input
									placeholder="VALUE"
									size="mini"
									v-model.lazy="item.value"
								></el-input></div
						></el-col>
						<el-col :span="4"
							><div class="grid-content bg-purple">
								<el-button
									type="danger"
									size="mini"
									@click="delRow(item, 'headers')"
									>删除</el-button
								>
							</div></el-col
						>
					</el-row>
				</el-tab-pane>
				<el-tab-pane label="请求参数">
					<el-tabs>
						<el-tab-pane label="params">
							<el-row
								:gutter="20"
								v-for="(item, index) in caseInfo.params"
								:key="index"
								style="margin-bottom: 10px"
							>
								<el-col :span="6"
									><div class="grid-content bg-purple">
										<!-- 很多标签都有size属性控制大小 -->
										<el-input
											placeholder="KEY"
											size="mini"
											v-model.lazy="item.key"
										></el-input></div
								></el-col>
								<el-col :span="12"
									><div class="grid-content bg-purple">
										<el-input
											placeholder="VALUE"
											size="mini"
											v-model.lazy="item.value"
										></el-input></div
								></el-col>
								<el-col :span="4"
									><div class="grid-content bg-purple">
										<el-button
											type="danger"
											size="mini"
											@click="delRow(item, 'params')"
											>删除</el-button
										>
									</div></el-col
								>
							</el-row></el-tab-pane
						>
						<el-tab-pane label="x-www-form-urlencoded">
							<el-row
								:gutter="20"
								v-for="(item, index) in caseInfo.data"
								:key="index"
								style="margin-bottom: 10px"
							>
								<el-col :span="6"
									><div class="grid-content bg-purple">
										<!-- 很多标签都有size属性控制大小 -->
										<el-input
											placeholder="KEY"
											size="mini"
											v-model.lazy="item.key"
										></el-input></div
								></el-col>
								<el-col :span="12"
									><div class="grid-content bg-purple">
										<el-input
											placeholder="VALUE"
											size="mini"
											v-model.lazy="item.value"
										></el-input></div
								></el-col>
								<el-col :span="4"
									><div class="grid-content bg-purple">
										<el-button
											type="danger"
											size="mini"
											@click="delRow(item, 'data')"
											>删除</el-button
										>
									</div></el-col
								>
							</el-row></el-tab-pane
						>
						<el-tab-pane label="json">
							<!-- 需要改动的有v-model/content/init -->
							<view-editor
								width="100%"
								height="300px"
								:content="caseInfo.json"
								v-model="caseInfo.json"
								:options="{
									enableBasicAutocompletion: true,
									enableSnippets: true,
									enableLiveAutocompletion: true,
									tabSize: 2,
									fontSize: 20,
									showPrintMargin: false,
								}"
								@init="editorInit"
								lang="json"
								theme="chrome"
							></view-editor>
							<el-button
								class="test-class"
								type="primary"
								size="mini"
								@click="jsonFormat"
								>格式化</el-button
							>
						</el-tab-pane>
					</el-tabs>
				</el-tab-pane>
				<el-tab-pane label="响应提取"></el-tab-pane>
				<el-tab-pane label="前置条件">
					<view-editor
						width="100%"
						height="300px"
						:content="caseInfo.setup_shell"
						v-model="caseInfo.setup_shell"
						:options="{
							enableBasicAutocompletion: true,
							enableSnippets: true,
							enableLiveAutocompletion: true,
							tabSize: 2,
							fontSize: 20,
							showPrintMargin: false,
						}"
						@init="editorPy"
						lang="python"
						theme="chrome"
					></view-editor
				></el-tab-pane>
				<el-tab-pane label="数据库校验"></el-tab-pane>
			</el-tabs>
			<el-button type="primary" size="mini" @click="dialogVisible = true"
				>用例详情</el-button
			>
		</el-card>
		<el-dialog title="提示" :visible.sync="dialogVisible" width="50%">
			<view-json
				:data="caseInfo"
				style="height: 420px; overflow-y: scroll"
			/>
		</el-dialog>
	</div>
</template>

<script>
import BreadCrumb from '@/components/BreadCrumb'
// 安装json数据编辑器插件
// https://github.com/chairuosen/vue2-ace-editor
import Editor from 'vue2-ace-editor'
// 安装插件
// https://github.com/zhaoxuhui1122/vue-json-view
import JsonView from 'vue-json-views'

export default {
	data() {
		return {
			dialogVisible: false,
			titles: ['用例管理', '用例编辑'],
			options_default: 'POST',
			options: ['GET', 'POST', 'PUT', 'DELETE'],
			caseInfo: {
				method: '',
				host: '',
				path: '',
				headers: [
					{ key: 'Content-Type', value: 'application/json' },
					{ key: 'Connection', value: 'keep-alive' },
				],
				params: [
					{ key: 'user', value: '白墙' },
					{ key: 'pwd', value: '12345' },
					{ key: '', value: '' },
				],
				data: [
					{ key: 'user', value: '白墙' },
					{ key: 'pwd', value: '12345' },
					{ key: '', value: '' },
				],
				json: '{"a": 1,"b": 2}',
				assert: {},
				setup_shell: '',
			},
		}
	},
	methods: {
		delRow(item, info) {
			// 删除请求头中的行
			console.log(item, info)
			const newHeaders = this.caseInfo[info].filter(function (data) {
				return data !== item
			})
			if (newHeaders.length === 0) {
				newHeaders.push({ key: '', value: '' })
				this.$message({
					showClose: true,
					message: '已清空',
					type: 'success',
				})
			}
			this.caseInfo[info] = newHeaders
		},
		addRow(val) {
			let lastData = val[val.length - 1]
			if (lastData.key || lastData.value) {
				// 增加一行数据
				val.push({ key: '', value: '' })
			}
		},
		editorInit: function () {
			require('brace/ext/language_tools') //language extension prerequsite...
			require('brace/mode/html')
			require('brace/mode/json') //language 这里js改成了json
			require('brace/mode/less')
			require('brace/theme/chrome')
			require('brace/snippets/json') //snippet 这里js改成了json
		},
		editorPy: function () {
			require('brace/ext/language_tools') //language extension prerequsite...
			require('brace/mode/html')
			require('brace/mode/python') //language 这里js改成了json
			require('brace/mode/less')
			require('brace/theme/chrome')
			require('brace/snippets/python') //snippet 这里js改成了json
		},
		jsonFormat() {
			try {
				this.caseInfo.json = JSON.parse(this.caseInfo.json)
				this.caseInfo.json = JSON.stringify(this.caseInfo.json, null, 4)
			} catch {
				this.$message({
					showClose: true,
					message: 'json格式错误',
					type: 'error',
				})
			}
		},
	},
	components: {
		'view-titles': BreadCrumb,
		// json编辑器的组件
		'view-editor': Editor,
		'view-json': JsonView,
	},
	watch: {
		// 监听headers的数据
		'caseInfo.headers': {
			handler: function (val, oldVal) {
				// 监听到数据变化的方法
				this.addRow(val)
			},
			deep: true,
		},
		'caseInfo.params': {
			handler: function (val, oldVal) {
				this.addRow(val)
			},
			deep: true,
		},
		'caseInfo.data': {
			handler: function (val, oldVal) {
				this.addRow(val)
			},
			deep: true,
		},
	},
	mounted() {
		this.addRow(this.caseInfo.headers)
	},
}
</script> 

<style scoped>
.el-divider__text {
	color: rgb(64, 144, 201);
	font-weight: bold;
}

.el-card {
	margin-top: 5px;
}

div ::v-deep .el-tabs__header {
	margin-bottom: 5px;
}

.test-class {
	margin-bottom: 5px;
	margin-top: 5px;
}
</style>
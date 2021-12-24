<template>
	<el-breadcrumb separator-class="el-icon-arrow-right">
		<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		<el-breadcrumb-item v-for="(value,index) in t" :key='index'>{{value}}</el-breadcrumb-item>
	</el-breadcrumb>
</template>

<script>
export default {
	props: ['t'],
}
</script> 

<style  scoped>
</style>
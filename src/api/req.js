import axios from "axios";

// 创建请求对象
const http = axios.create({
    baseURL: 'http://api.keyou.site:8000',
    timeout: 1000,
    validateStatus: function (status) {
        return true
    }
})

// 请求拦截器
http.interceptors.request.use(
    function (config) {
        const token = window.sessionStorage.getItem('token')
        if (token && config.url != "/user/login/") {
            config.headers.Authorization = 'JWT ' + token
        }
        return config
    },
    function (error) {
        return Promise.reject(error)
    }
)

const req = {
    // 登录
    loginApi(data) {
        return http.post('/user/login/', data)
    },

    // 注册
    registerApi(data) {
        return http.post('/user/register/', data)
    },

    // 项目-获取
    getProjects(data) {
        return http.get('/projects/', { params: data })
    },

    // 项目-删除
    deleteProject(id) {
        return http.delete('/projects/' + id + '/')
    },
    // 项目-修改
    updateProject(id, data) {
        return http.put('/projects/' + id + '/', data)
    },
    // 项目-添加
    addProject(data) {
        return http.post('/projects/', data)
    },

    // 接口-获取
    getInterfaces(data) {
        return http.get('/interfaces/', { params: data })
    },

    // 接口-删除
    deleteInterface(id) {
        return http.delete('/interfaces/' + id + '/')
    },
    // 接口-修改
    updateInterface(id, data) {
        return http.put('/interfaces/' + id + '/', data)
    },
    //接口- 添加
    addInterface(data) {
        return http.post('/interfaces/', data)
    }
}

// export用于对外输出本模块（一个文件可以理解为一个模块）变量的接口
// 1、export与export default均可用于导出常量、函数、文件、模块等
// 2、你可以在其它文件或模块中通过import+(常量 | 函数 | 文件 | 模块)名的方式，将其导入，以便能够对其进行使用
// 3、在一个文件或模块中，export、import可以有多个(用花括号)，export default仅有一个(只接一个对象，不用花括号)
// 4、通过export方式导出，在导入时要加{ }，export default则不需要

export default req
// export {req}
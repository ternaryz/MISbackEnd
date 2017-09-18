import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import Home from './views/Home.vue'
import Gogs from './views/Gogs.vue'
import Main from './views/Main.vue'
import Table from './views/nav1/Table.vue'
import Form from './views/nav1/Form.vue'
import user from './views/nav1/user.vue'
import Page4 from './views/nav2/Page4.vue'
import Page5 from './views/nav2/Page5.vue'
import Page6 from './views/nav3/Page6.vue'
import echarts from './views/charts/echarts.vue'
import Repository from './views/repository/index.vue'
import Model from './views/model/index.vue'

let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    //{ path: '/main', component: Main },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'el-icon-menu',
        leaf: true,//只有一个节点
        children: [
            { path: '/repository/index', component: Repository, name: '我的项目' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'el-icon-document',
        leaf: true,//只有一个节点
        children: [
            { path: '/model/index', component: Model, name: '我的模型' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '我的仿真' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'el-icon-share',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '我的分享' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '我的收藏',
        iconCls: 'el-icon-star-on',//图标样式class
        children: [
            // { path: '/main', component: Main, name: '项目', hidden: true },
            { path: '/table', component: Table, name: '项目' },
            { path: '/form', component: Form, name: '模型' },
            { path: '/user', component: user, name: '仿真' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '',
        iconCls: 'el-icon-delete',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '回收站' }
        ]
    },
    {
        path: '/',
        component: Home,
        name: '系统管理',
        iconCls: 'el-icon-setting',//图标样式class
        children: [
            // { path: '/main', component: Main, name: '项目', hidden: true },
            { path: '/table', component: Table, name: '项目管理' },
            { path: '/form', component: Form, name: '组织管理' },
            { path: '/user', component: user, name: '用户管理' },
            { path: '/user', component: user, name: '角色管理' },
            { path: '/user', component: user, name: '权限管理' },
            { path: '/user', component: user, name: '应用监控' },
            { path: '/user', component: user, name: '日志管理' },
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    },
    //gogs
    {
        path: '/',
        component: Gogs,
        name: '',
        iconCls: 'el-icon-menu',
        leaf: true,//只有一个节点
        children: [
            { path: '/repository/index', component: Repository, name: '我的项目' }
        ]
    },
    {
        path: '/',
        component: Gogs,
        name: '',
        iconCls: 'el-icon-document',
        leaf: true,//只有一个节点
        children: [
            { path: '/model/index', component: Model, name: '我的模型' }
        ]
    },
    {
        path: '/',
        component: Gogs,
        name: '',
        iconCls: 'fa fa-address-card',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '我的仿真' }
        ]
    },
    {
        path: '/',
        component: Gogs,
        name: '',
        iconCls: 'el-icon-share',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '我的分享' }
        ]
    },
    {
        path: '/',
        component: Gogs,
        name: '我的收藏',
        iconCls: 'el-icon-star-on',//图标样式class
        children: [
            { path: '/main', component: Main, name: '展示' },
            { path: '/table', component: Table, name: '项目' },
            { path: '/form', component: Form, name: '模型' },
            { path: '/user', component: user, name: '仿真' },
        ]
    },
    {
        path: '/',
        component: Gogs,
        name: '',
        iconCls: 'el-icon-delete',
        leaf: true,//只有一个节点
        children: [
            { path: '/page6', component: Page6, name: '回收站' }
        ]
    },
    {
        path: '/',
        component: Gogs,
        name: '系统管理',
        iconCls: 'el-icon-setting',//图标样式class
        children: [
            // { path: '/main', component: Main, name: '项目', hidden: true },
            { path: '/table', component: Table, name: '项目管理' },
            { path: '/form', component: Form, name: '组织管理' },
            { path: '/user', component: user, name: '用户管理' },
            { path: '/user', component: user, name: '角色管理' },
            { path: '/user', component: user, name: '权限管理' },
            { path: '/user', component: user, name: '应用监控' },
            { path: '/user', component: user, name: '日志管理' },
        ]
    },
];

export default routes;
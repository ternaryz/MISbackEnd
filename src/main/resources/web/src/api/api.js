import axios from 'axios';

let base = '';

export const requestLogin = params => { 
    return axios.post(`${base}/login`, params)
                .then(res => res.data); 
};

export const getUserList = params => { return axios.get(`${base}/api/user/list`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/api/user/listpage`, { params: params }); };

export const removeUser = params => { return axios.get(`${base}/api/user/remove`, { params: params }); };

export const batchRemoveUser = params => { return axios.get(`${base}/api/user/batchremove`, { params: params }); };

export const editUser = params => { return axios.get(`${base}/api/user/edit`, { params: params }); };

export const addUser = params => { return axios.get(`${base}/api/user/add`, { params: params }); };

export const getModelList = params =>{return axios.get(`${base}/model/list`,{params:params }); };

export const getReposList = params =>{return axios.get(`${base}/repository/list`,{params:params }); };

export const getDirectoryList = params =>{return axios.get(`${base}/directory/list`,{params:params});}

export const Api = function (app) {
    /* Create */
    app.post('/api/directory/add', function (req, res) {
        let startNum = 1
        const createUUid = function () {
            return req.body.parent_id ? ++startNum + req.body.parent_id * 1e2 : ++startNum
        }

        res.json({
            status: 1,
            code: 0,
            msg: 'ok',
            data: {
                name: req.body.name,
                id: createUUid(),
                parent_id: req.body.parent_id
            }
        })
    })

    /* Update */
    app.post('/api/directory/update', function (req, res) {
        res.json({
            status: 1,
            code: 0,
            msg: 'ok',
            data: req.body
        })
    })

    /* Read */
    app.get('/directory/list', function (req, res) {
        const parentId = req.query.parent_id || ''
        let startNum = 1
        const createUUid = function () {
            return parentId ? ++startNum + parentId * 1e2 : ++startNum
        }
        res.json({
            status: 1,
            code: 0,
            msg: 'ok',
            data: [
                {name: `JavaScript${parentId > 0 ? parentId : ''}`, id: createUUid(), parent_id: parentId},
                {name: `React${parentId > 0 ? parentId : ''}`, id: createUUid(), parent_id: parentId},
                {name: `Vue${parentId > 0 ? parentId : ''}`, id: createUUid(), parent_id: parentId},
                {name: `Element-UI${parentId > 0 ? parentId : ''}`, id: createUUid(), parent_id: parentId},
                {name: `Ant.Design${parentId > 0 ? parentId : ''}`, id: createUUid(), parent_id: parentId}
            ]
        })
    })

    /* Delete */
    app.post('/api/directory/delete', function (req, res) {
        res.json({
            status: 1,
            code: 0,
            msg: 'ok',
            data: req.body.id
        })
    })
}

module.exports = Api
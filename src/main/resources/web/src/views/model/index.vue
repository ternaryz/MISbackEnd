<template>
    <div class="modelContent">
      <div class="left">
          <!--模型分类树-->
          <kz-tree :data="tree" @node-click="hanldeNodeClick"></kz-tree>        
      </div>
      <div class="right">
          <!--模型列表-->
          <model-list ></model-list>
      </div>
    </div>
</template>

<script>
import kzTree from './directory.vue';
import modelList from './model.vue' ;

export default {
  name: 'ModelContent',
  components: {
    kzTree,
    modelList
  },
  data () {
    return {
      tree: {
        url: {
//          C: '/directory/add',
//          U: '/directory/update',
//          R: '/directory/list',
//          D: '/directory/delete'
            C: '/api/directory/add',
            U: '/api/directory/update',
            R: 'api/directory/list',
            D: '/api/directory/delete'
        }
      }

    }
  },
  methods: {
    hanldeNodeClick (data) {
      //该目录下的模型列表
    }
  }
}
</script>
<style scoped>

.left {
  min-width: 300px;
  float: left;
}


</style>
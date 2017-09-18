<template>
    <section>
      <waterfall
        :align="align"
        :line-gap="300"
        :min-line-gap="100"
        :max-line-gap="300"
        :single-max-width="260"
        :watch="repositories"
        @reflowed="reflowed"
        ref="waterfall"
      >
        <!-- each component is wrapped by a waterfall slot -->
        <waterfall-slot
          v-for="(item, index) in repositories"
          :width="250"
          :height="300"
          :order="index"
          :key="item.index"
          move-class="item-move"
        >
        <!-- :style="item.style" -->
          <div class="RepCard"  :index="item.index">
              <div class="center-aligned">
                <img v-bind:src="item.imageUrl" class="image">
              </div>
              <div class="content">
                  <a href="">
                    <h4>//TODO:项目标题</h4>
                  </a>
                  <small>//TODO:项目说明XXXXXXXXXXXXXXX</small>
              </div>
              <div class="cardButton">
                  <a class="user username" href="/circuitdigest">
                      <img class="avatar round" src="/static/images/avatar-default.png">
                      //TODO:用户名
                  </a>
                  <div class="right-buttons">
                      
                  </div>
              </div>

          </div>
        </waterfall-slot>
      </waterfall>
    </section>
</template>

<script>
  import { getReposList} from '../../api/api'
  //waterfall瀑布流组件
  import Waterfall from 'vue-waterfall/lib/waterfall'
  import WaterfallSlot from 'vue-waterfall/lib/waterfall-slot'

    
  export default {
    data(){
      return {
        filters:{
            name : ""
        },
        align:'left',
        repositories:[],
        isBusy: false
      }
    },
    methods:{
      getRepos: function(){
//        let para ={name: this.filters.name};
//        getReposList(para).then((res)=>{
//          this.repositories = res.data.repositories;
//          //window.alert(this.repositories[0].id+this.repositories[0].name);
//        });
          var _this = this;
          _this.$http.post('/api/model/list')
              .then(function (response) {
                  _this.repositories = response.data.repositories;
          })
          .catch(function (error) {
              console.log(error);
          });
      },
      reflowed: function () {
        this.isBusy = false
      }
    },
    mounted(){
      this.getRepos();
    },
    components:{
      Waterfall,
      WaterfallSlot
    }
}

</script>

<style scoped>
  .RepCard {
    position: absolute;
    top: 10px;
    left: 10px;
    right: 10px;
    bottom: 10px;
    background-color: #FFF;
    border-radius: 5px;
    box-shadow: 0 2px 3px 0 #d4d4d5, 0 0 0 1px #ededed;
    transition: all 1s ease 0s;
    overflow: hidden;
  } 
  .image{
    display: block;
    width: 100%;
    /*需要对高度做限制*/
    max-height: 160px;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    transition: all 1s ease 0s;
  }
  .RepCard .content{
    padding: 6px 15px 6px 15px;
    height: 70px;
    overflow: hidden;
  }
  .RepCard .content h4{
    font-size: 14px;
    overflow: hidden;
    text-overflow: ellipsis;
    word-break: keep-all;
    white-space: nowrap;
    display: block;
    font-weight: bold;
    line-height: 18px;
    margin-top: 7px;
    margin-bottom: 5px;
  }
  .RepCard .content a{
    color: #60BDE8;
    text-decoration: none;
  }
  .RepCard .content small {
    color: #838383;
    padding-bottom: 10px;
    font-size: 85%;
  }
  .RepCard .cardButton {
    margin-top: .5rem;
    padding: .5rem;
    text-align: left;
    background-color: #f8f8f8;
  }
  .username {
    text-transform: capitalize;
    color: #888;
  }
  .avatar.round {
      width: 32px;
      height: 32px;
      border-radius: 50%;
      border: 1px solid rgba(139,191,229,.5);
  }
  .item-move {
    transition: all .5s cubic-bezier(.55,0,.1,1);
    -webkit-transition: all .5s cubic-bezier(.55,0,.1,1);
  }
</style>


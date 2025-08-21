<script lang="ts" setup>

import {ref} from 'vue'
import {useRoute} from 'vue-router'
import {videoPathInfo, videoPathItem} from "./index.ts"

const videoRef = ref(null)
const videoSrc = ref<string>(null)
const videoItemList = ref<videoPathItem[]>([])
const pathList = ref<videoPathItem[]>([])
// 获取当前路由信息
const route = useRoute()

const showVideoDialog = ref<boolean>(false)
const showVideoItem = ref<boolean>(false)

const playVideoFun = () => {
  showVideoDialog.value = false
  showVideoItem.value = false
  videoSrc.value = ""
  const currentPath = route.path
  console.info("path", currentPath)
  for (let key in videoPathInfo) {
    const videoPathMapElement = videoPathInfo[key]
    console.info("key ", key, videoPathMapElement)
    if (currentPath.startsWith(videoPathMapElement.url)) {
      const videoItemListTmp = videoPathMapElement.videoItemList
      if (videoItemListTmp.length === 1) {
        videoSrc.value = videoItemListTmp[0].url
        showVideoItem.value = true
      } else {
        videoItemList.value = videoItemListTmp
      }
      pathList.value = videoPathMapElement.pageList
      showVideoDialog.value = true
      return
    }
  }
  ElMessage.warning("本页面暂时无法提供帮助视频")
}

// 切换播放/暂停状态
const togglePlay = () => {
  const video = videoRef.value
  if (video.paused) {
    video.play()
  } else {
    video.pause()
  }
}

const playVideoItem = (item: videoPathItem) => {
  videoSrc.value = item.url
  console.info("playVideoItem ", videoSrc.value)
  showVideoItem.value = true
  // togglePlay()
}
</script>

<template>
  <div class="notify">

    <svg
      @click="playVideoFun"
      xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
      aria-hidden="true" role="img"
      class="text-[#000000E0] hover:text-gray-400 iconify iconify--gitee icon-question-circle"
      width="20" height="20" preserveAspectRatio="xMidYMid meet" viewBox="0 0 16 16">
      <g fill="none" fill-rule="evenodd">
        <path d="M0 0h16v16H0z" />
        <path
          fill="currentColor"
              d="M8 1a7 7 0 110 14A7 7 0 018 1zm0 1a6 6 0 100 12A6 6 0 008 2zm0 8.5a.8.8 0 110 1.6.8.8 0 010-1.6zm.184-7c1.394 0 2.516.76 2.516 2.085 0 .853-.501 1.445-1.288 1.958-.736.486-.957.729-.966 1.312v.15c0 .283-.147.495-.464.495-.323 0-.483-.199-.483-.482V8.74c0-.778.528-1.294 1.25-1.776.59-.398.999-.751.999-1.383 0-.769-.7-1.255-1.596-1.255-.745 0-1.32.318-1.56 1.158-.069.216-.225.349-.46.349-.28 0-.432-.177-.432-.429 0-.168.041-.37.138-.579.39-.852 1.306-1.325 2.346-1.325z"></path>
      </g>
    </svg>
    <el-dialog title="帮助视频" v-model="showVideoDialog" :destroy-on-close="true" :width="950">
      <div class="video-container">
        <div v-show="!showVideoItem">
          <div class="videoItem" v-for="item in videoItemList" @click="playVideoItem(item)">
            {{ item.name }}
          </div>
        </div>
        <video
          v-if="showVideoItem"
          width="96%" height="550px"
          ref="videoRef"
          class="video-player"
          controls
          autoplay
          muted
          loop
          @click="togglePlay"
        >
          <source :src="videoSrc" type="video/mp4">
          您的浏览器不支持HTML5视频播放。
        </video>
      </div>
      <div>
        <div v-if="pathList.length>0">
          <div class="videoItem" :key="item.name" v-for="item in pathList">
            <a :href="item.url">{{ item.name }}</a>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
.svg-icon {
  font-size: 20px;
  width: 20px;
  height: 20px;

  &:focus {
    outline: none;
  }
}

.video-container {
  text-align: center;
}

.videoItem:before {
  content: "-";
  margin-right: 5px;
}

.videoItem {
  line-height: 30px;
  height: 30px;
  font-size: 17px;
  text-align: left;
  margin-left: 10px;
}
</style>

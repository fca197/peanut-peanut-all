<script setup lang="ts">
import AMapLoader from "@amap/amap-jsapi-loader";

import {DistrictCode, queryDistrictCode} from "@v/DistrictCode/districtCode.ts";
import {postNoResult} from "@@/utils/common-js.ts";

const loadData = ref<boolean>(true);
const gdMap = ref(null);
const loadAMap = () => {
  console.log("loadAMap key", window.gdKey);
  AMapLoader.load({
    plugins: ["AMap.Scale", "AMap.ElasticMarker", "AMap.Geolocation", "AMap.TileLayer", "AMap.Polygon"],
    key: window.gdKey, //申请好的Web端开发者 Key，调用 load 时必填
    version: "2.0", //指定要加载的 JS API 的版本，缺省时默认为 1.4.15
  })
  .then((AMap) => {
    gdMap.value = new AMap.Map("container", {
      zoom: 5,
      center: [108.867694, 34.899997],
      renderOptions: {
        canvasContext: {
          // 启用 willReadFrequently，消除 getImageData 警告
          willReadFrequently: true
        }
      }
    });
  })
  .catch((e) => {
    console.error(e); //加载错误提示
  }).then(r => loadData.value = false);
}

const districtCodeList = ref<DistrictCode[]>([]);
onMounted(async () => {
  queryDistrictCode(undefined).then((list) => {
    districtCodeList.value = list;
    loadBoundary(list[0])
  })
  loadAMap()
  console.info("loadAMap mounted");
})

const districtCodeClick = (data) => {
  loadBoundary(data)
}
const downLoadBoundary = (data) => {
  console.log("downLoadBoundary ", data);
  postNoResult("/districtCodeBoundary/insert", {
    districtCode: data.code,
  }, "获取成功", (r) => {
  })
}

function getRandomLightColor(options = {}) {
  // 默认配置：确保生成浅色的核心参数
  const {
    minHue = 0,
    maxHue = 360,
    minSaturation = 20,
    maxSaturation = 60,
    minLightness = 70,
    maxLightness = 90
  } = options;

  // 生成随机色相（0-360，决定颜色基调）
  const hue = Math.random() * (maxHue - minHue) + minHue;

  // 生成随机饱和度（20%-60%，避免过灰或过鲜艳）
  const saturation = Math.random() * (maxSaturation - minSaturation) + minSaturation;

  // 生成随机明度（70%-90%，核心参数：确保颜色为浅色）
  const lightness = Math.random() * (maxLightness - minLightness) + minLightness;

  // 格式化并返回HSL颜色字符串（保留1位小数）
  return `hsl(${hue.toFixed(1)}, ${saturation.toFixed(1)}%, ${lightness.toFixed(1)}%)`;
}

window.drawPolygon = function (code, data) {
  console.log(code + ": " + data.districtName);
  const polygon = new AMap.Polygon({
    strokeWeight: 1,
    path: data.polyline,
    fillOpacity: 0.4,
    fillColor: getRandomLightColor(),
    strokeColor: '#0091ea'
  });
  gdMap.value.add(polygon);
}

const loadBoundary = (data) => {

  data.children.forEach((item) => {
    // 动态创建 script 标签
    const script = document.createElement('script');
    script.src = "/geoJson/" + item.code + ".js";
// 将 script 标签添加到页面，触发请求
    document.head.appendChild(script);
  })


}

</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" id="gdMapDivParent" shadow="never" v-model="loadData">
      <el-row :gutter="20">
        <el-col :span="4">

          <el-scrollbar style="height: 800px; overflow-y: scroll">
            <el-tree
                node-click="districtCodeClick"
                node-key="code"
                :default-checked-keys="['100000']"
                :data="districtCodeList"
                :default-expanded-keys="['100000']"
                accordion
            >
              <template #default="{ node, data }">
                <div style="width: 100%;display: flex; justify-content: space-between; align-items: center;">
                  <span
                      style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: calc(100% - 100px);"
                      @click="districtCodeClick(data)">{{ data.name }}</span>
                  <span style="float: right;margin-right: 15px;white-space: nowrap;" @click="downLoadBoundary(data)">
                    <View style="width: 14px;font-size: 14px"/></span>
                </div>
              </template>
            </el-tree>
          </el-scrollbar>
        </el-col>
        <el-col :span="20">
          <div id="container" style="height: 800px; width: 100%"></div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<style scoped lang="scss">

</style>

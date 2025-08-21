<script setup lang="ts">

import {HeaderInfo, postResultInfo} from "@@/utils/common-js.ts";
import {useRoute} from 'vue-router';
import Decimal from "decimal.js";
import {ref} from "vue";
import {listGroupBy} from "@@/utils/listUtils.ts";

const tableData = ref<any>({})
const groupTableData = ref<any>({})

// 获取当前路由信息
const route = useRoute();

// 从路由参数中获取id
const id = route.params.id as string;

const saleHeaderList = ref<HeaderInfo []>([
  {
    fieldName: "group",
    showName: "销售特征组",
    width: 600
  },
  {
    fieldName: "value",
    showName: "销售特征组值",
    width: 200
  },
  {
    fieldName: "month",
    showName: "月份",
    width: 100
  }
])
const groupSaleHeaderList = ref<HeaderInfo []>([
  {
    fieldName: "group",
    showName: "销售特征组",
    width: 600
  },
  {
    fieldName: "value",
    showName: "销售特征组值",
    width: 200
  },
  {
    fieldName: "month",
    showName: "月份",
    width: 100
  }
])

onMounted(() => {
  postResultInfo("/apsGoodsForecast/getForecastDataById", {id: id}).then(t => {
    tableData.value = t.data
    const headerList = t.data.headerList.slice(3)
    t.data.headerList = headerList
    for (let i = 1; i < t.data.dataList.length; i++) {
      const item = t.data.dataList[i]
      headerList.forEach(header => {
        const userData = toPercentage(item[header.fieldName] * 1);
        if (item[header.fieldName + "_result"]) {
          const targetData = toPercentage(item[header.fieldName + "_result"] * 1);
          item[header.fieldName] = {
            userData,
            targetData
          }
        } else {
          item[header.fieldName] = userData
        }
      })
    }
    // tableData.value.headerList.slice(1).forEach(h => h.width = 180)
    console.log("tableData.value = t ", tableData.value.headerList)
  }).then(() => {
    postResultInfo("/apsGoodsForecastUserSaleGroupData/queryPageList", {
      queryPage: false,
      data: {forecastId: id}
    }).then(t => {
      groupTableData.value = t.data;
      t.data.dataList.forEach(tt => tt.unKey = tt.year + '' + tt.saleConfigIdList)
      var groupData = listGroupBy(t.data.dataList, "saleConfigIdList");
      console.info("groupData   ", groupData)

      const groupDataList = []


      for (const key in groupData) {
        var datum = groupData[key];
        console.info(" groupData key ", key, datum)

        tableData.value.headerList.forEach(ym => {
          const year = ym.showName.substring(0, 4);
          const month = ym.showName.substring(5);
          console.info("ym ", ym.showName, year, month)

          datum.filter(d => d.year + '' === year).forEach(dt => {
            dt[ym.showName] = {}
            dt[ym.showName]["userData"] = toPercentage(dt["month" + month] * 1)
            dt[ym.showName]["targetData"] = toPercentage(dt["monthResult" + month] * 1)
          })
        })
      }

      console.info("groupData   ", groupTableData.value)

    })
  })
})
const toPercentage = (num: number, decimalPlaces = 2) => {
  return `${new Decimal(num).times(100).toFixed(decimalPlaces)}%`;
}
</script>

<template>
  <div class="app-container">
    <el-card class="search-wrapper" shadow="never">
      <el-divider>预测数据</el-divider>
      <el-tabs stretch>
        <el-tab-pane label="销售配置">
          <el-table
            id="dataTable" :data="tableData.dataList"
            cellpadding="0" cellspacing="0">

            <el-table-column
              v-for="(item,index) in saleHeaderList" :key="index"
              :label="item.showName"
              :prop="item.fieldName" align="center"/>
            <el-table-column
              v-for="(item,index) in tableData.headerList" :key="index"
              :label="item.showName" :width="200"
              :prop="item.fieldName" align="center">
              <template #default="scope">
                <div v-if="scope.$index === 0 ">
                  {{ scope.row[item.fieldName] }}
                </div>
                <div v-else>
                  <span
                    v-if="scope.row[item.fieldName].userData === scope.row[item.fieldName].targetData ">
                    <span> {{ scope.row[item.fieldName].userData }}  </span>/
                    <span style="color: #00bb99"> {{
                        scope.row[item.fieldName].targetData
                      }}  </span>
                  </span>
                  <span
                    v-if="scope.row[item.fieldName].userData < scope.row[item.fieldName].targetData ">
                    <span> {{ scope.row[item.fieldName].userData }}  </span>/
                    <span style="color: #d23131 "> {{
                        scope.row[item.fieldName].targetData
                      }}  </span>
                  </span>

                  <span
                    v-if="scope.row[item.fieldName].userData > scope.row[item.fieldName].targetData ">
                    <span> {{ scope.row[item.fieldName].userData }}  </span>/
                    <span style="color: blue "> {{ scope.row[item.fieldName].targetData }}  </span>
                  </span>
                  /
                  {{
                    new Decimal(scope.row[item.fieldName].userData.replace("%", "")).sub(new Decimal(scope.row[item.fieldName].targetData.replace("%", ""))).toFixed(2)
                  }}%

                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="销售组配置">
          <el-table :data="groupTableData.dataList">
            <el-table-column
              :width="400"
              label="销售组配置" align="center">
              <template #default="scope">
                {{
                  scope.row.saleConfigParentList.map(t => t.valueTmp + '_' + t.label).join(",")
                }}
              </template>
            </el-table-column>
            <el-table-column
              :width="500"
              label="销售配置" align="center">
              <template #default="scope">
                {{
                  scope.row.saleConfigList.map(t => t.valueTmp + '_' + t.label).join(",")
                }}
              </template>
            </el-table-column>
            <el-table-column
              label="月份" align="center">
            </el-table-column>
            <el-table-column
              v-for="(item,index) in tableData.headerList" :key="index"
              :label="item.showName" :width="200"
              :prop="item.fieldName" align="center">
              <template #default="scope">
                <div>
                  <span
                    v-if="scope.row[item.fieldName].userData === scope.row[item.fieldName].targetData ">
                    <span> {{ scope.row[item.fieldName].userData }}  </span>/
                    <span style="color: #00bb99"> {{
                        scope.row[item.fieldName].targetData
                      }}  </span>
                  </span>
                  <span
                    v-if="scope.row[item.fieldName].userData < scope.row[item.fieldName].targetData ">
                    <span> {{ scope.row[item.fieldName].userData }}  </span>/
                    <span style="color: #d23131 "> {{
                        scope.row[item.fieldName].targetData
                      }}  </span>
                  </span>

                  <span
                    v-if="scope.row[item.fieldName].userData > scope.row[item.fieldName].targetData ">
                    <span> {{ scope.row[item.fieldName].userData }}  </span>/
                    <span style="color: blue "> {{ scope.row[item.fieldName].targetData }}  </span>
                  </span>
                  /
                  {{
                    new Decimal(scope.row[item.fieldName].userData.replace("%", "")).sub(new Decimal(scope.row[item.fieldName].targetData.replace("%", ""))).toFixed(2)
                  }}%
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<style scoped lang="scss">

</style>

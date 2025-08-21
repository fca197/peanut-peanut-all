<script lang="ts" setup>
import {gantt} from "dhtmlx-gantt"
import "dhtmlx-gantt/codebase/dhtmlxgantt.css"

const zoomLevel = ref<string>("month")
var hourToStr = gantt.date.date_to_str("%i");

const hourRangeFormat = (step) => {
  return function (date) {
    var intervalEnd = new Date(gantt.date.add(date, step, "minute") - 1)
    return hourToStr(date) + " - " + hourToStr(intervalEnd);
  };
};

const zoomConfig = {
  levels: [
    {
      name: "month",
      label: "月",
      scale_height: 60,
      min_column_width: 20,
      scales: [
        {unit: "year", format: "%Y"},
        {unit: "month", format: "%m"}
      ]
    },
    {
      name: "day",
      label: "天",
      scale_height: 60,
      min_column_width: 30,
      scales: [
        {unit: "month", format: "%Y-%m"},
        {unit: "day", format: "%d"}
      ]
    },
    {
      name: "hour",
      label: "小时",
      scale_height: 60,
      min_column_width: 30,
      scales: [
        {unit: "month", format: "%Y-%m"},
        {unit: "day", format: "%d"},
        {unit: "hour", format: "%H"}
      ]
    },
    {
      name: "minute",
      label: "分钟",
      scale_height: 60,
      min_column_width: 60,
      scales: [
        {unit: "month", format: "%Y-%m"},
        {unit: "day", format: "%d"},
        {unit: "hour", format: "%H"},
        {unit: "minute", step: 30, format: hourRangeFormat(30)}
      ]
    }

  ]
}


const changeZoomLevel = (value: string) => {
  zoomConfig.levels.filter(t => t.name === value)[0].name;
  loadGantt()
}

const loadingData = ref<boolean>(false);
onMounted(() => {
  loadGantt()
})

const loadGantt = () => {
  loadingData.value = true
  gantt.plugins({
    auto_scheduling: true,
    click_drag: true,
    critical_path: true,
    drag_timeline: true,
    fullscreen: true,
    keyboard_navigation: false,
    quick_info: false,
    tooltip: true,
    undo: true,
    grouping: true,
    marker: true,
    multiselect: false,
    overlay: true,
    export_api: true,
  })
  // gantt.config.readonly = false //是否只读
  // gantt.config.lightbox.sections = [] // 清空弹窗内容
  // gantt.config.drag_move = true //允许拖动任务时移动任务
  // gantt.config.auto_types = true //将包含子任务的任务转换为项目，将没有子任务的项目转换回任务
  // gantt.config.open_split_tasks = true//激活列表展开、折叠功能
  gantt.config.xml_date = "%Y.%m.%d" //甘特图时间数据格式
  // gantt.config.show_errors = false //不显示错误信息
  gantt.ext.zoom.init(zoomConfig) //配置初始化扩展
  gantt.i18n.setLocale("cn")
  // gantt.config.work_time = true
  console.info("zoomLevel.value ", zoomLevel.value)
  gantt.ext.zoom.setLevel(zoomLevel.value) //切换到指定的缩放级别x
  // gantt.config.add_column = false //添加符号
  // gantt.config.show_progress = false //显示进度条
  // gantt.config.drag_resize = true //允许拖动任务时调整任务大小
  // gantt.config.drag_links = false //允许拖动任务时调整任务链接
  // gantt.config.order_branch = true //允许拖动任务时保持任务顺序
  // gantt.config.drag_progress = true //允许拖动任务时调整任务进度
  // 设置表头
  gantt.config.columns = [
    {
      name: "text", //tasks参数名
      tree: true, //是否开始tree联级
      width: "170", //宽度 值为string 例如 width:"75" "*" 为auto
      resize: true, //可重置大小
      label: "订单", //标签名
      template: function (obj) {
        return obj.text
      }, //返回值
      align: "left" //对齐方式
    },
    {
      name: "start_date",
      label: "开始时间",
      width: "110",
      align: "left"
    }, {
      name: "end_date",
      label: "结束时间",
      width: "110",
      align: "left"
    }
  ]

  // 设置当天标记
  const dateToStr = gantt.date.date_to_str(gantt.config.task_date)
  const today = new Date(new Date().setHours(0, 0, 0, 0)) // 获取当天零点的时间
  gantt.addMarker({
    start_date: today,
    css: "today",
    text: "今日",
    title: `Today: ${dateToStr(today)}`,
  })
  // 设置颜色
  gantt.templates.task_class = function (start, end, task) {
    if (task.estimate) {
      return "gray_color"
    } else {
      return "red_color"
    }
  }
  gantt.config.work_time = true
  gantt.templates.timeline_cell_class = function (task, date) {
    if (date.getDay() === 0 || date.getDay() === 6) { // 检查是否为周末
      return "weekend-border-bottom " // 返回红色样式
    } else {
      return "" // 其他日期使用默认样式
    }

    const targetDate = new Date(2025, 10, 27) // 2024-12-17
    if (date.getTime() === targetDate.getTime()) {
      return "red_color" // 返回红色样式
    }
    return "" // 其他日期使用默认样式
  }
  gantt.attachEvent("onTaskDblClick", function (id, e) {
    console.info("onTaskDblClick", id, e)
  })
  gantt.config.fit_tasks = true //自动适配任务大小

  gantt.templates.tooltip_text = function (start, end, task) {
    return (
      task.text +
      "<br/><strong>开始:</strong>" +
      gantt.templates.tooltip_date_format(start) +
      "<br/><strong>结束:</strong>" +
      gantt.templates.tooltip_date_format(end)
    )
  }

  gantt.init("gantt_here")

  gantt.parse({
    tasks: [
      {
        id: 1,
        text: "DFB24019",
        progress: 0.8, //完成度
        start_date: "2025-10-06", //开始时间 日月年
        end_date: "2025-10-19",
        projectStatus: "进行中",
        open: true, //默认打开=
        number: "", //显示字段
        person: "韩鸿风",
        post: "BA",
        department: "数字化一部",
        task: "0/20"
      },
      {
        // toolTipsTxt: "任务#101-001",
        id: 21, //任务id
        text: "任务1的撒的防守打法士大夫", //任务名称
        start_date: "2025-04-06", //开始时间 日月年
        end_date: "2025-12-26",
        parent: 1, //父级id
        progress: 1, //完成度
        open: true, //默认打开
        status: "已完成",
      },
      {
        // toolTipsTxt: "任务#101-001",
        id: 22, //任务id
        text: "第二个逾期阶段", //任务名称
        start_date: "2025-12-16", //开始时间 日月年
        end_date: "2025-01-26",
        parent: 1, //父级id
        progress: 1, //完成度
        open: true, //默认打开
        status: "逾期"
      },
      {
        // toolTipsTxt: "任务#101-001",
        id: 23, //任务id
        text: "第三个进行中阶段", //任务名称
        start_date: "2025-01-20", //开始时间 日月年
        end_date: "2025-03-03",
        parent: 1, //父级id
        progress: 0.7, //完成度
        open: true, //默认打开
        status: "进行中"
      },
      {
        id: 2,
        text: "DFB45113",
        progress: 0.7, //完成度
        start_date: "2025-04-16", //开始时间 日月年
        projectStatus: "已完成",
        end_date: "2025-10-25",
        open: true, //默认打开
        number: "哈哈哈" //显示字段
      },
      {
        // toolTipsTxt: "任务#101-001",
        id: 11, //任务id
        text: "项目启动阶段", //任务名称
        start_date: "2025-04-06", //开始时间 日月年
        end_date: "2025-06-06",
        parent: 2, //父级id
        progress: 1, //完成度
        open: true, //默认打开
        status: "已完成"
      },
      {
        // toolTipsTxt: "任务#101-001",
        id: 12, //任务id
        text: "设计阶段", //任务名称
        start_date: "2025-04-20", //开始时间 日月年
        end_date: "2025-06-26",
        parent: 2, //父级id
        progress: 0.9, //完成度
        open: true, //默认打开
        status: "进行中"
      },
      {
        // toolTipsTxt: "任务#101-001",
        id: 13, //任务id
        text: "采购阶段", //任务名称
        start_date: "2025-05-14", //开始时间 日月年
        end_date: "2025-07-26",
        parent: 2, //父级id
        progress: 1, //完成度
        open: true, //默认打开
        status: "未开始"
      }
    ],
    links: [
      {id: "10", source: "11", target: "12", type: "2"},
    ]
  })
  loadingData.value = false

}

const addGattFunction = () => {
  // prevent moving to another sub-branch
  gantt.attachEvent("onAfterTaskUpdate", function (id, task) {
    // any custom logic here
    console.log("onAfterTaskUpdate", id, task)
  });

}

</script>

<template>
  <div style="height:100%; background-color: white" v-loading="loadingData">

    缩放纬度：
    <el-select v-model="zoomLevel" style="width: 200px" @change="changeZoomLevel">
      <el-option
        v-for="l in zoomConfig.levels"
        :key="l.name"
        :value="l.name"
        :label="l.label"
      />
    </el-select>

    <div id="gantt_here" style="  width:100%; height:100%;"></div>
  </div>
</template>


<style>
.weekend-border-bottom {
  background: #bdbbbb;
  color: rgb(255, 255, 255) !important;
}

.blue_color {
  background: blue;
}

.gray_color {
  background: gray;
}

.gantt_cal_qi_controls {
  display: none;
}

.gantt_cal_qi_title {
  display: none;
}
</style>

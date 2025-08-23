<script lang="ts" setup>
import type {FormInstance, FormRules} from "element-plus"
import {useSettingsStore} from "@/pinia/stores/settings"
import {useUserStore} from "@/pinia/stores/user"
import ThemeSwitch from "@@/components/ThemeSwitch/index.vue"
import {Lock, User} from "@element-plus/icons-vue"
import {loginApi} from "./apis"
import md5 from "md5-js/md5"
import Owl from "./components/Owl.vue"
import {useFocus} from "./composables/useFocus"
import {checkCookiesValue, setDeviceId, setKeyValue} from "@@/utils/cache/cookies.ts"

const router = useRouter()

const userStore = useUserStore()

const settingsStore = useSettingsStore()

const {isFocus, handleBlur, handleFocus} = useFocus()

/** 登录表单元素的引用 */
const loginFormRef = ref<FormInstance | null>(null)

/** 登录按钮 Loading */
const loading = ref<boolean>(false)

/** 登录表单数据 */
const loginFormData = reactive({
  username: "18600000000",
  password: "admin",
  code: ""
})

/** 登录表单校验规则 */
const loginFormRules: FormRules = {
  username: [
    {required: true, message: "请输入用户名", trigger: "blur"}
  ],
  password: [
    {required: true, message: "请输入密码", trigger: "blur"},
    {min: 1, max: 16, message: "长度在 1 到 16 个字符", trigger: "blur"}
  ]
}

/** 登录 */
function handleLogin() {
  loginFormRef.value?.validate((valid) => {
    if (!valid) {
      ElMessage.error("表单校验不通过")
      return
    }
    loading.value = true
    const req = {
      loginPhone: loginFormData.username,
      pwd: md5(loginFormData.password).toUpperCase()
    }
    loginApi(req).then(({data}) => {
      setKeyValue("loginPhone", loginFormData.username);
      userStore.setToken(data.token)
      router.push("/")
      window.location.reload();
    }).catch(() => {
    }).finally(() => {
      loading.value = false
    })
  })
}

onMounted(() => {
  setDeviceId()
  const gitBrandCk = "git-brand-ck"
  checkCookiesValue(gitBrandCk, 1000 * 60 * 30, () => {
    ElNotification({
      title: "版本提示",
      message: "当前为develop版本",
      type: "success",
      duration: 3000
    })
  })
})
</script>

<template>
  <div class="login-container">
    <ThemeSwitch v-if="settingsStore.showThemeSwitch" class="theme-switch"/>
    <Owl :close-eyes="isFocus"/>
    <div class="login-card">
      <div class="title">
        <span class="spanAps">门店</span>
        <span class="spanDesc">高级开店系统</span>
      </div>
      <div class="content">
        <el-form ref="loginFormRef" :model="loginFormData" :rules="loginFormRules"
                 @keyup.enter="handleLogin">
          <el-form-item prop="username">
            <el-input
                v-model.trim="loginFormData.username"
                placeholder="用户名"
                type="text"
                tabindex="1"
                :prefix-icon="User"
                size="large"
            />
          </el-form-item>
          <el-form-item prop="password">
            <el-input
                v-model.trim="loginFormData.password"
                placeholder="密码"
                type="password"
                tabindex="2"
                :prefix-icon="Lock"
                size="large"
                show-password
                @blur="handleBlur"
                @focus="handleFocus"
            />
          </el-form-item>
          <el-button :loading="loading" type="primary" size="large" @click.prevent="handleLogin">
            登 录
          </el-button>
          <div style="margin-top: 15px; text-align: right; font-size: 15px">
            浏览器下载： <a
              href="https://www.google.cn/chrome/next-steps.html?installdataindex=empty"
              style="color: #00bb99">Google Chrome</a>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.login-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  min-height: 100%;

  .theme-switch {
    position: fixed;
    top: 5%;
    right: 5%;
    cursor: pointer;
  }

  .login-card {
    width: 480px;
    max-width: 90%;
    border-radius: 20px;
    box-shadow: 0 0 10px #dcdfe6;
    background-color: var(--el-bg-color);
    overflow: hidden;

    .title {
      display: flex;
      justify-content: center;
      align-items: center;
      font-weight: 600;
      line-height: 60px;
      .spanAps {
        height: 40px;
        line-height: 40px;
        color: red;
        font-size: 40px;
        margin-right: 20px;
      }

      .spanDesc {
        margin-top: 20px;
      }
    }

    .content {
      padding: 20px 50px 50px 50px;

      :deep(.el-input-group__append) {
        padding: 0;
        overflow: hidden;

        .el-image {
          width: 100px;
          height: 40px;
          border-left: 0px;
          user-select: none;
          cursor: pointer;
          text-align: center;
        }
      }

      .el-button {
        width: 100%;
        margin-top: 10px;
      }
    }
  }
}
</style>

import type {App} from "vue"
import TableBar from "@/layouts/components/TableBar/index.vue"

export function installTableBar(app: App) {
  app.component("TableBar", TableBar)
}

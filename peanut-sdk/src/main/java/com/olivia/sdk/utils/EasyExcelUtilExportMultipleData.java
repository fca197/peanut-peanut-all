package com.olivia.sdk.utils;

import cn.hutool.core.collection.CollUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class EasyExcelUtilExportMultipleData {

  public String fileName;

  private List<SheetData> sheetDataList;

  public void checkParam() {
    if (StringUtils.isBlank(this.fileName)) {
      throw new RuntimeException("文件名称不能为空");
    }
    if (CollUtil.isEmpty(sheetDataList)) {
      this.sheetDataList = List.of();
    }
    Map<String, Integer> map = new HashMap<>();
    sheetDataList.forEach(t -> {
      Integer index = map.get(t.getSheetName());
      if (index == null) {
        map.put(t.getSheetName(), 1);
      } else {
        map.put(t.getSheetName(), index + 1);
        t.setSheetName(t.getSheetName() + "-" + (index + 1));
      }
    });
  }

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class SheetData {

    private String sheetName;
    private List<SheetHeader> headerList;
    private List<?> dataList;
  }

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class SheetHeader {

    private Integer width = 200;
    private String showName;
    private String fieldName;
//    private List<SheetHeader> childrenList;
  }
}

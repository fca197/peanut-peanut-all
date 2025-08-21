package com.olivia.sdk.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.olivia.sdk.utils.model.UserInfo;
import java.util.List;
import java.util.Random;

/**
 * 生成随机用户信息
 *
 * @author ppp
 * @date 2023-05-12
 */
public class RandomUserUtil {

  /**
   * 复姓出现的几率(0--100)
   */
  private static final int SURNAME_PROBABILITY = 5;
  private static final Random RANDOM = RandomUtil.getRandom();
  private static final String FAMILY_ONE_NAME = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻水云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任"
      + "袁柳鲍史唐费岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅卞齐康伍余元卜顾孟平黄和穆萧尹姚邵湛汪祁毛禹狄米贝明臧计成戴宋茅庞熊纪舒屈项祝董粱杜阮"
      + "席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田胡凌霍万柯卢莫房缪干解应宗丁宣邓郁单杭洪包诸左石崔吉龚程邢滑裴陆荣翁荀羊甄家封芮储靳邴"
      + "松井富乌焦巴弓牧隗山谷车侯伊宁仇祖武符刘景詹束龙叶幸司韶黎乔苍双闻莘劳逄姬冉宰桂牛寿通边燕冀尚农温庄晏瞿茹习鱼容向古戈终居衡步都耿满弘"
      + "国文东殴沃曾关红游盖益桓公晋楚闫";
  private static final String FAMILY_TWO_NAME = "欧阳太史端木上官司马东方独孤南宫万俟闻人夏侯诸葛尉迟公羊赫连澹台皇甫宗政濮阳公冶太叔申屠公孙慕容仲孙钟离长孙宇"
      + "文司徒鲜于司空闾丘子车亓官司寇巫马公西颛孙壤驷公良漆雕乐正宰父谷梁拓跋夹谷轩辕令狐段干百里呼延东郭南门羊舌微生公户公玉公仪梁丘公仲公上"
      + "公门公山公坚左丘公伯西门公祖第五公乘贯丘公皙南荣东里东宫仲长子书子桑即墨达奚褚师吴铭";

  private static final List<String> addressList = List.of("和平路", "东大街", "西大街", "南大街", "北大街", "和平路", "朝阳路", "安边路", "安波路", "安德路", "安汾路",
      "安福路",
      "安国路", "安化路", "安澜路", "宝昌路", "宝联路", "宝林路");

  public static UserInfo getRandomUser() {
    UserInfo userInfo = new UserInfo();
    userInfo.setId(IdUtil.getSnowflakeNextId());
    int sexInt = getRandomSexInt();
    userInfo.setSex(sexInt);
    userInfo.setName(getRandomUserName());
    userInfo.setAge(getRandomAge());
    userInfo.setHeight(getRandomHeight());
    userInfo.setWeight(getRandomWeight());
    userInfo.setPhone(getRandomPhone());
    userInfo.setEmail(getRandomQQEmail());
    StringBuilder sb = new StringBuilder();
    getRandomRegion(sb, "0");
    userInfo.setAddress(
        sb.append(addressList.get(randomInt(addressList.size()))).append(randomInt(999)).append("号").append(randomInt(40)).append("楼").append(randomInt(555))
            .append("室")
            .toString());
//    userInfo.setAddress(addressList.get(randomInt(addressList.size())));
    return userInfo;
  }

  private static void getRandomRegion(StringBuilder sb, String parentCode) {

  }


  /**
   * 生成随机数(最大值限制)
   */
  public static int randomInt(int maxNum) {
    return RANDOM.nextInt(maxNum);
  }


  private static String getUerName(String userNameStr) {
    int bodNameIndexOne = randomInt(userNameStr.length());
    int bodNameIndexTwo = randomInt(userNameStr.length());
    if (randomInt(100) > SURNAME_PROBABILITY) {
      int familyOneNameIndex = randomInt(FAMILY_ONE_NAME.length());
      return FAMILY_ONE_NAME.substring(familyOneNameIndex, familyOneNameIndex + 1) + userNameStr.charAt(bodNameIndexOne) + userNameStr.charAt(bodNameIndexTwo);
    } else {
      int familyTwoNameIndex = randomInt(FAMILY_TWO_NAME.length());
      familyTwoNameIndex = familyTwoNameIndex % 2 == 0 ? familyTwoNameIndex : familyTwoNameIndex - 1;
      return FAMILY_TWO_NAME.substring(familyTwoNameIndex, familyTwoNameIndex + 2) + userNameStr.charAt(bodNameIndexOne) + userNameStr.charAt(bodNameIndexTwo);
    }
  }

  /**
   * 获取女生姓名
   */
  public static String getRandomUserName() {
    String girlName = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧"
        + "璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥"
        + "筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达"
        + "安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽"
        + "晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
    return getUerName(girlName);
  }

  /**
   * 获取随机手机号
   */
  public static String getRandomPhone() {
    int phoneTwoRandomIndex = randomInt(4);
    String phoneTwoNum = "6379";
    return "1" + phoneTwoNum.charAt(phoneTwoRandomIndex) + (100000000 + randomInt(899999999));
  }

  /**
   * 获取随机qq邮箱
   */
  public static String getRandomQQEmail() {
    return ("" + RANDOM.nextLong()).substring(10) + "@qq.com";
  }

  /**
   * 获取随机性别
   */
  public static int getRandomSexInt() {
    return randomInt(2);
  }

  /**
   * 获取随机性别
   */
  public static String getRandomSexStr() {
    return randomInt(2) % 2 == 0 ? "女" : "男";
  }

  /**
   * 获取随机范围数字
   */
  public static int getRandomNum(int min, int max) {
    return min + RANDOM.nextInt(max - min);
  }

  /**
   * 获取随机年龄
   */
  public static int getRandomAge(int min, int max) {
    return getRandomNum(18, 25);
  }

  /**
   * 获取随机年龄(18-35)
   */
  public static int getRandomAge() {
    return getRandomNum(18, 35);
  }

  /**
   * 获取随机身高(140-190)
   */
  public static int getRandomHeight() {
    return getRandomNum(140, 190);
  }

  /**
   * 获取随机身高
   */
  public static int getRandomHeight(int min, int max) {
    return getRandomNum(min, max);
  }

  /**
   * 获取随机身高(50-70)
   */
  public static int getRandomWeight() {
    return getRandomNum(50, 70);
  }

  /**
   * 获取随机体重
   */
  public static int getRandomWeight(int min, int max) {
    return getRandomNum(min, max);
  }

}

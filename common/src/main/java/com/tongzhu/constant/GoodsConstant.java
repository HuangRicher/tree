package com.tongzhu.constant;


/**
 * 道具物品常量类
 * @date 2018年8月10日
 */
public class GoodsConstant {

	/** 金币 **/
	public static final String GOODS_MONEY = "50001";

	/** 钻石 **/
	public static final String GOODS_JEWEL = "50002";

	/** 精力**/
	public static final String GOODS_VIGOUR= "50003";

	/** 经验**/
	public static final String GOODS_EXP= "50004";

	/** 钓鱼券 **/
	//public static final Integer GOODS_VOUCHER = 3;

	/** 雨露  **/
	public static final Integer GOODS_DEW = 4;

	/**阳光**/
	public static final int GOODS_SUNSHINE=5;

	/** 精华 **/
	//public static final Integer GOODS_ESSENCES = 5;

	/** 海螺 **/
	public static final String GOODS_CONCH = "6";

	/** 木头 **/
	public static final Integer GOODS_WOOD = 7;

	/** 双倍金币卡  **/
	public static final Integer GOODS_GOLD_CARD = 8;

	/** 喇叭 **/
	public static final Integer GOODS_HORN = 9;

	/** 小丑鱼 **/
	public static final Integer GOODS_FISH_CLOWN = 10;

	/** 金枪鱼  **/
	public static final Integer GOODS_FISH_TUNAS = 11;

	/** 灯笼鱼 **/
	public static final Integer GOODS_FISH_LANTERN = 12;

	/** 海蝠鱼  **/
	public static final Integer GOODS_FISH_BATS = 13;

	/** 蝴蝶鱼 **/
	public static final Integer GOODS_FISH_BUTTERFLY = 14;

	/** 孔雀鱼 **/
	public static final Integer GOODS_FISH_GUPPY = 15;

	/** 鲨鱼  **/
	public static final Integer GOODS_FISH_SHARK = 16;

	/** 美人鱼 **/
	public static final Integer GOODS_FISH_MERMAID = 17;

	/** 旺财  **/
	public static final Integer GOODS_DOG_MONEY = 18;

	/** 来福 **/
	public static final Integer GOODS_DOG_BLESSINGS = 19;

	/** 金毛  **/
	public static final Integer GOODS_DOG_HAIR = 20;

	/** 藏獒 **/
	public static final Integer GOODS_DOG_MASTIFF = 21;

	/** 带肉牛大骨  **/
	public static final Integer GOODS_MEAT_BONE = 22;

	/** 白煮鸡后腿 **/
	public static final Integer GOODS_DRUMSTICKS = 23;

	/** 黯然销魂肉  **/
	public static final Integer GOODS_GLOOMY_MEAT = 24;

	/** 金蝉脱壳丸 **/
	public static final Integer GOODS_CICADAS_SHELLING = 25;

	public static final String ITEM_SEEDS_28201 = "28201";

	public static final String ITEM_SEEDS_28202 = "28202";

	public static final String ITEM_SEEDS_28203 = "28203";

	public static final String ITEM_SEEDS_28204 = "28204";

	public static final String ITEM_SEEDS_28205 = "28205";

	public static final String ITEM_SEEDS_28206 = "28206";

	public static final String ITEM_SEEDS_28207 = "28207";

	public static final String ITEM_SEEDS_28208 = "28208";

	public static final String ITEM_SEEDS_28209 = "28209";

	public static final String ITEM_SEEDS_28210 = "28210";

	public static final String ITEM_SEEDS_28211 = "28211";

	//小喇叭ID
	public static final String TRUMPET_ID="11601";
    // 999朵花
	public static final Integer FLOWER_999=11904;

	/*public static void main(String[] args) {
		FileOutputStream fop = null;
		File file;
		try {
			file = new File("C:/Users/Administrator/Desktop/a.txt");
			fop = new FileOutputStream(file,true);
			if (!file.exists()) {
				file.createNewFile();
			}
			while (true) {
                Scanner scanner = new Scanner(System.in);
                String s = scanner.nextLine();
                String content = s + "\r\n" + "\r\n";
                byte[] contentInBytes = content.getBytes();
                fop.write(contentInBytes);
                if ("over".equals(s)) {
                    fop.flush();
                    fop.close();
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}

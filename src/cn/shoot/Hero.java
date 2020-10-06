package cn.shoot;

import java.awt.image.BufferedImage;

/** 英雄机: 是飞行物 */
public class Hero extends FlyingObject {
	private int doubleFire; // 火力值
	private int life; // 命
	private BufferedImage[] images; // 可切换的图片数组
	private int index; // 协助图片切换

	/** 构造方法 */
	public Hero() {
		image = ShootGame.hero0; // 图片

		width = image.getWidth(); // 宽
		height = image.getHeight(); // 高
		x = 150; // x:固定的值
		y = 400; // y:固定的值
		doubleFire = 10000; // 默认为0(单倍火力)
		life = 3; // 默认3条命
		images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1 }; // 两张图片切换
		index = 0; // 协助图片切换
	}

	/** 重写step()走步 */
	public void step() { // 10毫秒走一次
		image = images[index++ / 10 % images.length];

		/*
		 * //每100毫秒切换一次 index++; int a = index/10; int b = a%2; image =
		 * images[b];
		 */
		/*
		 * 10M index=1 a=0 b=0 20M index=2 a=0 b=0 30M index=3 a=0 b=0 ... 100M
		 * index=10 a=1 b=1 110M index=11 a=1 b=1 ... 200M index=20 a=2 b=0 ...
		 * 300M index=30 a=3 b=1
		 */
	}

	/** 英雄机发射子弹(生成子弹对象) */
	public Bullet[] shoot() {
		int xStep = this.width / 4; // xStep:1/4英雄机的宽
		int yStep = 20; // yStep:固定的20
		if (doubleFire > 0) { // 双倍
			Bullet[] bs = new Bullet[2]; // 2发子弹
			bs[0] = new Bullet(this.x + 1 * xStep, this.y - yStep); // x:英雄机的x+1/4英雄机的宽
																	// y:英雄机的y-20
			bs[1] = new Bullet(this.x + 3 * xStep, this.y - yStep); // x:英雄机的x+3/4英雄机的宽
																	// y:英雄机的y-20
			doubleFire -= 2; // 发射一次双倍火力，则火力值减2
			return bs;
		} else { // 单倍
			Bullet[] bs = new Bullet[1]; // 1发子弹
			bs[0] = new Bullet(this.x + 2 * xStep, this.y - yStep); // x:英雄机的x+2/4英雄机的宽
																	// y:英雄机的y-20
			return bs;
		}
	}

	/** 英雄机随着鼠标动 x:鼠标的x y:鼠标的y */
	public void moveTo(int x, int y) {
		this.x = x - this.width / 2; // 英雄机的x=鼠标的x-1/2英雄机的宽
		this.y = y - this.height / 2; // 英雄机的y=鼠标的y-1/2英雄机的高
	}

	/** 重写outOfBounds()检查是否越界 */
	public boolean outOfBounds() {
		return false; // 永不越界
	}

	/** 英雄机增命 */
	public void addLife() {
		life++; // 命数增1
	}

	/** 获取英雄机的命 */
	public int getLife() {
		return life; // 返回命数
	}

	/** 英雄机减命 */
	public void subtractLife() {
		life--; // 命数减1
	}

	/** 英雄机增火力 */
	public void addDoubleFire() {
		doubleFire += 40; // 火力值增40
	}

	/** 清空英雄机的火力值 */
	public void clearDoubleFire() {
		doubleFire = 0; // 火力值归零
	}

	/** 英雄机与敌人的碰撞算法 this:英雄机 other:敌人 */
	public boolean hit(FlyingObject other) {
		int x1 = other.x - this.width / 2; // x1:敌人的x-1/2英雄机的宽
		int x2 = other.x + other.width + this.width / 2; // x2:敌人的x+敌人的宽+1/2英雄机的宽
		int y1 = other.y - this.height / 2; // y1:敌人的y-1/2英雄机的高
		int y2 = other.y + other.height + this.height / 2; // y2:敌人的y+敌人的高+1/2英雄机的高
		int x = this.x + this.width / 2; // x:英雄机的x+1/2英雄机的宽
		int y = this.y + this.height / 2; // y:英雄机的y+1/2英雄机的高

		return x >= x1 && x <= x2 && y >= y1 && y <= y2; // x在x1与x2之间，并且，y在y1与y2之间，即为撞上了
	}

}

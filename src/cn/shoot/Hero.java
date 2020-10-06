package cn.shoot;

import java.awt.image.BufferedImage;

/** Ӣ�ۻ�: �Ƿ����� */
public class Hero extends FlyingObject {
	private int doubleFire; // ����ֵ
	private int life; // ��
	private BufferedImage[] images; // ���л���ͼƬ����
	private int index; // Э��ͼƬ�л�

	/** ���췽�� */
	public Hero() {
		image = ShootGame.hero0; // ͼƬ

		width = image.getWidth(); // ��
		height = image.getHeight(); // ��
		x = 150; // x:�̶���ֵ
		y = 400; // y:�̶���ֵ
		doubleFire = 10000; // Ĭ��Ϊ0(��������)
		life = 3; // Ĭ��3����
		images = new BufferedImage[] { ShootGame.hero0, ShootGame.hero1 }; // ����ͼƬ�л�
		index = 0; // Э��ͼƬ�л�
	}

	/** ��дstep()�߲� */
	public void step() { // 10������һ��
		image = images[index++ / 10 % images.length];

		/*
		 * //ÿ100�����л�һ�� index++; int a = index/10; int b = a%2; image =
		 * images[b];
		 */
		/*
		 * 10M index=1 a=0 b=0 20M index=2 a=0 b=0 30M index=3 a=0 b=0 ... 100M
		 * index=10 a=1 b=1 110M index=11 a=1 b=1 ... 200M index=20 a=2 b=0 ...
		 * 300M index=30 a=3 b=1
		 */
	}

	/** Ӣ�ۻ������ӵ�(�����ӵ�����) */
	public Bullet[] shoot() {
		int xStep = this.width / 4; // xStep:1/4Ӣ�ۻ��Ŀ�
		int yStep = 20; // yStep:�̶���20
		if (doubleFire > 0) { // ˫��
			Bullet[] bs = new Bullet[2]; // 2���ӵ�
			bs[0] = new Bullet(this.x + 1 * xStep, this.y - yStep); // x:Ӣ�ۻ���x+1/4Ӣ�ۻ��Ŀ�
																	// y:Ӣ�ۻ���y-20
			bs[1] = new Bullet(this.x + 3 * xStep, this.y - yStep); // x:Ӣ�ۻ���x+3/4Ӣ�ۻ��Ŀ�
																	// y:Ӣ�ۻ���y-20
			doubleFire -= 2; // ����һ��˫�������������ֵ��2
			return bs;
		} else { // ����
			Bullet[] bs = new Bullet[1]; // 1���ӵ�
			bs[0] = new Bullet(this.x + 2 * xStep, this.y - yStep); // x:Ӣ�ۻ���x+2/4Ӣ�ۻ��Ŀ�
																	// y:Ӣ�ۻ���y-20
			return bs;
		}
	}

	/** Ӣ�ۻ�������궯 x:����x y:����y */
	public void moveTo(int x, int y) {
		this.x = x - this.width / 2; // Ӣ�ۻ���x=����x-1/2Ӣ�ۻ��Ŀ�
		this.y = y - this.height / 2; // Ӣ�ۻ���y=����y-1/2Ӣ�ۻ��ĸ�
	}

	/** ��дoutOfBounds()����Ƿ�Խ�� */
	public boolean outOfBounds() {
		return false; // ����Խ��
	}

	/** Ӣ�ۻ����� */
	public void addLife() {
		life++; // ������1
	}

	/** ��ȡӢ�ۻ����� */
	public int getLife() {
		return life; // ��������
	}

	/** Ӣ�ۻ����� */
	public void subtractLife() {
		life--; // ������1
	}

	/** Ӣ�ۻ������� */
	public void addDoubleFire() {
		doubleFire += 40; // ����ֵ��40
	}

	/** ���Ӣ�ۻ��Ļ���ֵ */
	public void clearDoubleFire() {
		doubleFire = 0; // ����ֵ����
	}

	/** Ӣ�ۻ�����˵���ײ�㷨 this:Ӣ�ۻ� other:���� */
	public boolean hit(FlyingObject other) {
		int x1 = other.x - this.width / 2; // x1:���˵�x-1/2Ӣ�ۻ��Ŀ�
		int x2 = other.x + other.width + this.width / 2; // x2:���˵�x+���˵Ŀ�+1/2Ӣ�ۻ��Ŀ�
		int y1 = other.y - this.height / 2; // y1:���˵�y-1/2Ӣ�ۻ��ĸ�
		int y2 = other.y + other.height + this.height / 2; // y2:���˵�y+���˵ĸ�+1/2Ӣ�ۻ��ĸ�
		int x = this.x + this.width / 2; // x:Ӣ�ۻ���x+1/2Ӣ�ۻ��Ŀ�
		int y = this.y + this.height / 2; // y:Ӣ�ۻ���y+1/2Ӣ�ۻ��ĸ�

		return x >= x1 && x <= x2 && y >= y1 && y <= y2; // x��x1��x2֮�䣬���ң�y��y1��y2֮�䣬��Ϊײ����
	}

}

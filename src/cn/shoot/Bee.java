package cn.shoot;

import java.util.Random;

/** С�۷�: �Ƿ���� */
public class Bee extends FlyingObject implements Award {
	private int xSpeed = 1; // x�����ƶ��ٶ�
	private int ySpeed = 2; // y�����ƶ��ٶ�
	private int awardType; // ����������(0��1)

	/** ���췽�� */
	public Bee() {
		image = ShootGame.bee; // ͼƬ
		width = image.getWidth(); // ��
		height = image.getHeight(); // ��
		Random rand = new Random(); // ���������
		x = rand.nextInt(ShootGame.WIDTH - this.width); // x:0��(���ڿ�-С�۷��)֮��������
		y = -this.height; // y:����С�۷�ĸ�
		awardType = rand.nextInt(2); // 0��1֮��������
	}

	/** ��ȡ�������� */
	public int getType() {
		return awardType; // ���ؽ�������
	}

	/** ��дstep()�߲� */
	public void step() {
		x += xSpeed; // x+(���������)
		y += ySpeed; // y+(����)
		if (x >= ShootGame.WIDTH - this.width) { // ��x>=(���ڿ�-�۷��)����xSpeedΪ-1����-1��Ϊ����
			xSpeed = -1;
		}
		if (x <= 0) { // ��x<=0����xSpeedΪ1����1��Ϊ����
			xSpeed = 1;
		}
	}

	/** ��дoutOfBounds()����Ƿ�Խ�� */
	/** ��дoutOfBounds()����Ƿ�Խ�� */
	public boolean outOfBounds() {
		return this.y >= ShootGame.HEIGHT; // С�۷��y>=���ڵĸߣ���ΪԽ����
	}
}
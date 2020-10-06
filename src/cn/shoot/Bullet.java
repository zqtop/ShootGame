package cn.shoot;

/** �ӵ�: �Ƿ����� */
public class Bullet extends FlyingObject {
	private int speed = 3; // �ƶ����ٶ�

	/** ���췽�� �ӵ��ĳ�ʼ��������Ӣ�ۻ��� */
	public Bullet(int x, int y) {
		image = ShootGame.bullet; // ͼƬ
		width = image.getWidth(); // ��
		height = image.getHeight(); // ��
		this.x = x; // x:��Ӣ�ۻ�
		this.y = y; // y:��Ӣ�ۻ�
	}

	/** ��дstep()�߲� */
	public void step() {
		y -= speed; // y-(����)
	}

	/** ��дoutOfBounds()����Ƿ�Խ�� */
	public boolean outOfBounds() {
		return this.y <= -this.height; // �ӵ���y<=�����ӵ��ĸߣ���ΪԽ����
	}
}
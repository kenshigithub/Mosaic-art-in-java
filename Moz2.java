/*--------------------------------------------------------------------------------------
好きな粗さのモザイク画像を生成することができます。ボタンを押してモザイクの粗さ
を変えられます。
----------------------------------------------------------------------------------------*/
import java.awt.*;
import javax.swing.*;

public class Moz2 extends JFrame {
	public Moz2() {
		setSize(1000, 500);		//最初のフレームサイズ
		setTitle("Java Example");	//タイトル
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//コンテナを取得してパネルを貼る
		MyJPanel myJPanel = new MyJPanel();
		Container c = getContentPane();	//コンテナの取得
		c.add(myJPanel);		//パネルを貼る
		setVisible(true);
		setResizable(false);
	}

	/***************** main ****************************/
	public static void main(String[] args) { new Moz2(); }
}
/*--------------------------------------------------------------------------------------
好きな粗さのモザイク画像を生成することができます。ボタンを押してモザイクの粗さ
を変えられます。
----------------------------------------------------------------------------------------*/
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.Image;

public class Moz2 extends JFrame
{
	public Moz2()
	{
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
	public static void main(String[] args)
	{
		new Moz2();
	}

	/********************************************************
		MyJPanel:パネル
	*********************************************************/
	public class MyJPanel extends JPanel implements ActionListener
	{
		JButton Button1, Button2, Button3;
		Image ima1,ima2;
		int width, height, mozsize=4;
		int motodata[];
		int newdata[];
		int mozdata[];
		/************ コンストラクタ ****************/
		public MyJPanel()
		{
			Button1 = new JButton("モザイク2倍");
			Button2 = new JButton("モザイク1/2倍");
			Button3 = new JButton("リセット");
			ima1   = new ImageIcon("sample2.jpg").getImage();
			width  = ima1.getWidth(this);
			height = ima1.getHeight(this);
			motodata = new int[width*height];
			newdata = new int[width*height];
			Button1.addActionListener(this);
			Button2.addActionListener(this);
			Button3.addActionListener(this);
			add(Button1);
			add(Button2);
			add(Button3);
			PixelGrabber pg = new PixelGrabber(ima1, 0, 0, width, height, motodata, 0, width);
			try
			{
				pg.grabPixels();
			}
			catch (InterruptedException e)
			{ System.out.println("エラー"); }
		}

		/*************** paintComponent *******************/
		public void paintComponent(Graphics g)
		{
			int i,j,k,l,r, r1, k1;
			int red, green, blue, S_red, S_green, S_blue, average_red, average_green, average_blue;
			int[] mozdata = new int [mozsize*mozsize];
			g.drawImage(ima1, 0, 0, this);
			System.out.println("mozsize=" + mozsize);
			System.out.println("width=" + width);
			System.out.println("height=" + height);
			System.out.println("width/mozsize=" + width/mozsize);
			System.out.println("height/mozsize=" + height/mozsize);

			for (i = 0; i < width/mozsize ; i++) //to i=61
			{
				for(j = 0; j < height/mozsize ; j++ ) //to j=45 
				{
					for(r = 0; r < mozsize ; r++)
					{
						for(k = 0; k < mozsize ; k++)
						{
						red = (0xff & (motodata[i*mozsize+(j*mozsize+r)*width + k] >> 16));
						green = (0xff & (motodata[i*mozsize+(j*mozsize+r)*width + k] >> 8));
						blue = (0xff & motodata[i*mozsize+(j*mozsize+r)*width + k]);
						mozdata[k+r*mozsize] = (0xff000000 | red << 16 | green << 8 | blue);
						}
						
					}
				

				S_red = (0xff & (mozdata[0] >> 16));
				S_green = (0xff & (mozdata[0] >> 8));
				S_blue = (0xff & mozdata[0]);
				for(l = 1; l < mozsize*mozsize ; l++)
				{
					//System.out.println("l="+ l);
					S_red += (0xff & (mozdata[l] >> 16));
					S_green += (0xff & (mozdata[l] >> 8));
					S_blue += (0xff & mozdata[l]);

				}
				average_red = S_red/(mozsize*mozsize);
				average_green = S_green/(mozsize*mozsize);
				average_blue = S_blue/(mozsize*mozsize);
				for(r1 = 0; r1 < mozsize ; r1++)
				{
				for(k1 = 0; k1 < mozsize ; k1++)
				{
				newdata[i*mozsize+(j*mozsize+r1)*width + k1] = (0xff000000 | average_red << 16 | average_green << 8 | average_blue);
				}
				}
				
				}
			}

			ima2 = createImage(new MemoryImageSource(width, height, newdata, 0, width));
			g.drawImage(ima2, width, 0, this);

		}


		/* イベント発生ごとに呼び出される */
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==Button1)
  			{
				if(mozsize < Math.min(width/2, height/2)+1 )
				mozsize=mozsize*2;

  				repaint();
  			}
			else if(e.getSource()==Button2)
			{
				if(mozsize > 1)
				mozsize=mozsize/2;
  				repaint();
			}
 			else if(e.getSource()==Button3)
			{
				mozsize=1;
				repaint();
			}
		}

     }
}
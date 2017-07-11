package imgs;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.entity.BackGround;

public class Images {
	public static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Image back = tk.createImage(BackGround.class.getResource("/imgs/background.jpg"));
	public static Image rom= tk.createImage(BackGround.class.getResource("/imgs/rom.png"));
	public static Image lam = tk.createImage(BackGround.class.getResource("/imgs/lam.png"));
	public static Image[] myFish = {
		tk.createImage(BackGround.class.getResource("/imgs/my.png")),
		tk.createImage(BackGround.class.getResource("/imgs/my.png")),
		tk.createImage(BackGround.class.getResource("/imgs/my.png")),
		tk.createImage(BackGround.class.getResource("/imgs/my.png"))
	};
	public static Image[] otherFish = {
		tk.createImage(BackGround.class.getResource("/imgs/fisha.png")),
		tk.createImage(BackGround.class.getResource("/imgs/fisha.png")),
		tk.createImage(BackGround.class.getResource("/imgs/fisha.png")),
		tk.createImage(BackGround.class.getResource("/imgs/fisha.png")),
		tk.createImage(BackGround.class.getResource("/imgs/fisha.png"))
	};
	public static Image[] goldFish = {
		tk.createImage(BackGround.class.getResource("/imgs/bad.png")),
		tk.createImage(BackGround.class.getResource("/imgs/bad.png")),
		tk.createImage(BackGround.class.getResource("/imgs/bad.png")),
		tk.createImage(BackGround.class.getResource("/imgs/bad.png")),
		tk.createImage(BackGround.class.getResource("/imgs/bad.png"))
	};

	public static Image welcome = tk.createImage(BackGround.class.getResource("/imgs/fbg.jpg"));
	public static Image selected = tk.createImage(BackGround.class.getResource("/imgs/selected.png"));
	public static Image rbullet = tk.createImage(BackGround.class.getResource("/imgs/98.png"));
	public static Image lbullet = tk.createImage(BackGround.class.getResource("/imgs/99.png"));
	public static Image story = tk.createImage(BackGround.class.getResource("/imgs/story.jpg"));
	public static Image victory =tk.createImage(BackGround.class.getResource("/imgs/victory.jpg"));
	public static Image fail =tk.createImage(BackGround.class.getResource("/imgs/fail.jpg"));
	
	
	public static Image[] hujingImage(){
		ImageFilter imgf1;
		ImageFilter imgf2;
		ImageFilter imgf3;
		int srcWidth;
		int srcHeight;
		Image img1 = null;
		Image img2 = null;
		Image img3 = null;
		try {
			//得到缓存图象
			BufferedImage bi = ImageIO.read(new File((BackGround.class.getResource("/imgs/hujing1.png")).toURI()));
			srcWidth = bi.getWidth();
			srcHeight = bi.getHeight();
			//得到缓存图象的缩放版本
			Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT); 
			//过滤图片的一部分
			imgf1 = new CropImageFilter(0, 98, srcWidth, 95); 
			imgf2 = new CropImageFilter(0, 190, srcWidth, 90); 
			imgf3 = new CropImageFilter(0, 280, srcWidth, 100); 
			img1 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imgf1));	
			img2 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imgf2));
			img3 = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), imgf3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image[] imgz = {img1,img2,img3};
		return imgz;
	}
	
	public static Image hujing1 = tk.createImage(BackGround.class.getResource("/imgs/hujing1.png"));
}

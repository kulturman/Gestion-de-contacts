import java.io.IOException;
import bf.kulturman.controler.CMControler;
import bf.kulturman.model.CMModel;
import bf.kulturman.view.MainWindow;

public class Main 
{
    public static void main(String[] args)  throws IOException
    {
    	CMModel model = new CMModel();
    	CMControler ctrl = new CMControler(model);
        MainWindow view = new MainWindow(430 , 600 , ctrl);
        view.setVisible(true);
		view.setLocationRelativeTo(null);  
		model.addObserver(view);
    }
}

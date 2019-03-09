import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import javafx.scene.control.TextField;
public class Fattura 
{
	public static void createAndShowGUI()
	{
		JFrame jframe  = new JFrame("Modulo fatture");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setLayout(new FlowLayout());
		jframe.setSize(500, 500);
		
		
		GridBagConstraints constrins = new GridBagConstraints();
		JLabel label1 = new JLabel("Iserisci 1");
		JTextField text1 = new JTextField();
		text1.setColumns(20);
		jframe.getContentPane().add(label1);
		jframe.getContentPane().add(text1);
		jframe.setVisible(true);
		
		
	}
	
	
	public static void main(String... args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable ()
				{

					@Override
					public void run() {
						// TODO Auto-generated method stub
						createAndShowGUI();
					}
					
				});
	}
}

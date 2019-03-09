package FatturaPAck;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
public class Fattura {

	private JFrame frame;
	private JTextField txtFieldcontent;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fattura window = new Fattura();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public Fattura() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 537, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Compilatore Fatture");
		lblNewLabel.setBounds(0, 0, 523, 18);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblField = new JLabel("Field1");
		lblField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblField.setBounds(10, 79, 34, 33);
		frame.getContentPane().add(lblField);
		
		txtFieldcontent = new JTextField();
		txtFieldcontent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFieldcontent.setText("field1Content");
		txtFieldcontent.setBounds(54, 87, 166, 19);
		frame.getContentPane().add(txtFieldcontent);
		txtFieldcontent.setColumns(10);
		
		JButton btnGenerate = new JButton("GENERATE");
		btnGenerate.setBounds(360, 346, 121, 21);
		frame.getContentPane().add(btnGenerate);
		
		btnGenerate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final String cust_Name = txtFieldcontent.getText();
				
			}
		});
		
		
	}
	
	
	/**
	 * Getters
	 */
	
	public final String getText() 
	{
		return txtFieldcontent.getText();
	}
}

package test2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class home extends JFrame {

	public static String userLoggedIn = "";
	private JPanel contentPane;
	private JButton createCard;
	private JButton searchCard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					home frame = new home();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public home() {
		System.out.println("User " + userLoggedIn);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		
		//initializing components
		createCard = new JButton();
		searchCard = new JButton();		
		contentPane = new JPanel();
		
		// setting up components
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		createCard.setText("Create A Card");
		createCard.setBounds(150,140, 200,30);
		searchCard.setText("Search A Card");
		searchCard.setBounds(150,190, 200,30);
		
		searchCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new searchcard().setVisible(true);
				
			}
			
			
			
		});
		
		createCard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new createcard().setVisible(true);
				
			}
			
			
		});
		
		contentPane.add(createCard);
		contentPane.add(searchCard);
		
		
	}

}

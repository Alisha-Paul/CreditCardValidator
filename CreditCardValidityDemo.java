import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import projects.CreditCardValidity;

public class CreditCardValidityDemo {

	public static void main(String[] args) {
	
		//Create JFrame object and set size specifications 
		JFrame frameName = new JFrame("Validate Card Number");
		frameName.setSize(550, 400);
		frameName.setLayout(null);
		
		
		//Create JLabel object and set size to display message to user
		JLabel cardValue = new JLabel("Enter Credit Card Number");
		cardValue.setBounds(200, 60, 200, 20);
		
		
		//Create JTextField object and set size to allow user to enter credit card number
		JTextField userVal = new JTextField();
		userVal.setBounds(120, 90, 300, 35);

		
		//Create JButton object and set size
		JButton submitButton = new JButton("Validate Card");
		submitButton.setBounds(120, 160, 300, 50);
		
		
		//Create JLabel object and set size to display message about credit card validity and type
		//Currently not displaying any information. Will update this label after credit card number is inputed
		JLabel lblMsg = new JLabel("");
		lblMsg.setBounds(130, 250, 300, 30);
		Font font1 = new Font("SansSerif", Font.BOLD, 15);
		lblMsg.setFont(font1);
		
		//The order to add all swing objects to the main frame
		frameName.add(cardValue);
		frameName.add(userVal);
		frameName.add(submitButton);
		frameName.add(lblMsg);

		
		//Make frame visible to user
		frameName.setVisible(true);
		
		
		//Call addActionListener function which in turn calls the actionPerformed function
		//		- Stores user input as credit card number
		//		- Display message about card validity and card type
		//		- Catch exception if user value is not a number
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				//Handling exception if user didn't put an integer
			    try {
			    	
			    	//Convert user input to a long integer
			    	long ccNumber = Long.parseLong(userVal.getText());
			    				    	
					//Create a credit card object and call constructor while passing credit card number
			    	CreditCardValidity card1 = new CreditCardValidity(ccNumber);
			    	
			    	//Create string to store message about card validity and card type
					String validityMsg;
					
					//Check if card is valid
					if (card1.isCardValid())
						validityMsg = "Valid - " + card1.getCardType();
					else
						validityMsg = "INVALID CARD NUMBER";
					
					//Update JLabel object's text with information about card validity and card type
					lblMsg.setText(validityMsg);
					
					//Refresh frame after JLabel has been updated
					frameName.validate();
			    }
			    
			    //Catch exception if user does not enter a number
			    catch(NumberFormatException ex) {
			    	lblMsg.setText("Invalid Input Format!");
			    }   
			    
			}
						
		});		

		//Have the frame wait for confirmation before closing
		frameName.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 
		//Call addWindowListener function to provide a confirmation window before closing application
		frameName.addWindowListener( new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        JFrame frame = (JFrame)e.getSource();
		 
		        int result = JOptionPane.showConfirmDialog(frame, "Exit Application?");
		        
		        //If user selects yes, close frame
		        if (result == JOptionPane.YES_OPTION)
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }
		});
		
		frameName.setVisible(true);
	
	}
	
}

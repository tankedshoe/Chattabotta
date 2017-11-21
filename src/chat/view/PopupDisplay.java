package chat.view;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 * The controller for popups in 
 * @author Dane Heaps
 * @version 21/11/17 1.2
 */
public class PopupDisplay
{
	private ImageIcon icon;
	private String windowTitle;
	
	public PopupDisplay()	
	{
		icon = new ImageIcon(getClass().getResource("images/chatbotPPAltTwo.png"));
		windowTitle = "Chattabotta says...";
	}
	
	public void displayText(String message)
	{
		JOptionPane.showMessageDialog(null,  message, windowTitle, JOptionPane.INFORMATION_MESSAGE, icon);
	}
	
	public String collectResponse(String question)
	{
		String answer = "";
		
		answer += JOptionPane.showInputDialog(null, question, windowTitle, JOptionPane.PLAIN_MESSAGE, icon, null, "");
		
		return answer;
	}
}

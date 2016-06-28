package MCEntityAnimator.gui.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import MCEntityAnimator.distribution.ServerAccess;

public class LoginGUI extends JFrame
{
	
	private static final long serialVersionUID = 6032906317630465138L;

	private static final String[] users = new String[]{"joe", "kurt", "root"};
	
	private static final Map<String, char[]> passwords = new HashMap<String, char[]>();
	
	
	private static final char[] joePassword = "dabigjoe".toCharArray();
	private static final char[] kurtPassword = "projectxykurt".toCharArray();
	private static final char[] rootPassword = "root".toCharArray();
	private JPasswordField passwordField;
	
	public LoginGUI()
	{
		super("Login");
		
		passwords.put("joe", joePassword);
		passwords.put("kurt", kurtPassword);
		passwords.put("root", rootPassword);
		
		final JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		mainPanel.setPreferredSize(new Dimension(400, 250));
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1;
		c.insets = new Insets(10,10,10,10);
		
		c.gridy = 0;
		c.gridwidth = 1;
		mainPanel.add(new JLabel("Login as: "),c);
		c.gridx = 1;
		final JComboBox<String> userSelect = new JComboBox<String>(users);
		mainPanel.add(userSelect, c);
		
		c.gridy = 1;
		c.gridx = 0;
		mainPanel.add(new JLabel("Password: "),c);
		c.gridx = 1;
		passwordField = new JPasswordField();
		mainPanel.add(passwordField, c);
		
		c.gridy = 2;
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String username = (String) userSelect.getSelectedItem();
				char[] password = passwords.get(username);
				if(Arrays.equals(passwordField.getPassword(), password))
				{
					dispose();
					try 
					{
						ServerAccess.username = username;
						ServerAccess.downloadAll();
						ServerAccess.gui = new FileGUI();
					} 
					catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(mainPanel, "Incorrect password.");
			}
		});
		mainPanel.add(loginButton,c);
		
		c.gridy = 3;
		JButton registerButton = new JButton("New User? Register here");
		registerButton.setEnabled(false);
		registerButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		mainPanel.add(registerButton,c);
		
		setContentPane(mainPanel);
		pack();
		setVisible(true);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);

	}

	
}

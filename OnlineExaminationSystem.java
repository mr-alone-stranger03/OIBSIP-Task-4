import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class OnlineExaminationSystem extends JFrame implements ActionListener,Runnable {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton updateProfileButton;
    private JButton takeTestButton;
    private JButton logoutButton,next,submit;
	JRadioButton rb1,rb2,rb3;
	ButtonGroup btngrp;
	JTextArea ta;
	JTextField tf;
	JPanel loginPanel,updateProfilePanel,takeTestPanel,btnPanel,logoutPanel, quiz;
    private JLabel timerLabel, question, welcomeLabel;
	static OnlineExaminationSystem tts;
    int cn = 0;
	
	String[] questionsAndAnswers = {
            "What is the capital of France? ",
            "What is the largest planet in our solar system?",
            "What is the smallest country in the world?",
            "What is the largest country in the world?",
            "What is the highest mountain in the world?"
        };
	String answers[][] = {{"A.Russia", "B.Britain", "C.Paris"},{"A.Earth", "B.Saturn","C.Jupiter"},{"Japan","B.Vatican City","C.Singapore"},{"A.Russia","B.India","C.U.S.A"},{"A.Kili Manjaro","B.Mount Everest","C.Konda devarakonda"}};
	String ansArray[] = {"C.Paris", "C.Jupiter", "B.Vatican City", "A.Russia", "B.Mount Everest"};
    public OnlineExaminationSystem() {
        setTitle("Online Examination System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 1, 2, 2));
        
        welcomeLabel = new JLabel("Welcome to the Online Examination System!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel);
		
		question = new JLabel(questionsAndAnswers[0]);
		question.setHorizontalAlignment(SwingConstants.CENTER);
		
        
        loginPanel = new JPanel(new FlowLayout());
        JLabel usernameLabel = new JLabel("Username: ");
        loginPanel.add(usernameLabel);
        usernameField = new JTextField(20);
        loginPanel.add(usernameField);
        JLabel passwordLabel = new JLabel("Password: ");
        loginPanel.add(passwordLabel);
        passwordField = new JPasswordField(20);
        loginPanel.add(passwordField);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
        add(loginPanel);
        
        updateProfilePanel = new JPanel(new FlowLayout());
        updateProfileButton = new JButton("Update Profile");
        updateProfileButton.addActionListener(this);
        updateProfilePanel.add(updateProfileButton);
        add(updateProfilePanel);
        
        takeTestPanel = new JPanel(new FlowLayout());
        takeTestButton = new JButton("Take Test");
        takeTestButton.addActionListener(this);
        takeTestPanel.add(takeTestButton);
        add(takeTestPanel);

        timerLabel = new JLabel();
        
        logoutPanel = new JPanel(new FlowLayout());
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        logoutPanel.add(logoutButton);
		
		submit = new JButton("Submit");
		submit.addActionListener(this);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            login();
			remove(loginPanel);
        } else if (e.getSource() == updateProfileButton) {
            updateProfile();
			remove(updateProfilePanel);
        } else if (e.getSource() == takeTestButton) {
			Thread t = new Thread(tts);
			remove(loginPanel);
			remove(updateProfilePanel);
			remove(takeTestPanel);
			t.start();
			takeTest();
        } else if (e.getSource() == logoutButton) {
				dispose();
                setVisible(false);
        } if(e.getSource()==submit)
			{
				if(cn == questionsAndAnswers.length - 1){
					dispose();
					setVisible(false);
				}
				String en="";
				if(rb1.isSelected())
				en=rb1.getText();
				if(rb2.isSelected())
				en=rb2.getText();
				if(rb3.isSelected())
				en=rb3.getText();
				if(en.equals(ansArray[cn]))
				JOptionPane.showMessageDialog(null,"Right Answer");
				else
				JOptionPane.showMessageDialog(null,"Wrong Answer");
			
				cn++;
				question.setText(questionsAndAnswers[cn]);
				rb1.setText(answers[cn][0]);
				rb2.setText(answers[cn][1]);
				rb3.setText(answers[cn][2]);
				
			}
    }
    
    public void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login successful.");
            updateProfileButton.setEnabled(true);
            takeTestButton.setEnabled(true);
            logoutButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }
    
    public void updateProfile() {
        JOptionPane.showMessageDialog(this, "Profile updated successfully.");
    }
	
	public void takeTest() {
		
		quiz = new JPanel();
		ta = new JTextArea();
		rb1 = new JRadioButton(answers[cn][0]);
		rb2 = new JRadioButton(answers[cn][1]);
		rb3 = new JRadioButton(answers[cn][2]);
		add(question);
		btnPanel = new JPanel();
		btnPanel.add(rb1);
		btnPanel.add(rb2);
		btnPanel.add(rb3);
		add(btnPanel);
		btngrp = new ButtonGroup();
		btngrp.add(rb1);
		btngrp.add(rb2);
		btngrp.add(rb3);
		add(submit);
		add(logoutPanel);
		
	}
    
	
	public void run(){
        add(timerLabel);
		int min = 4;
		int sec = 59;
			while(sec > 0){
				try{
					Thread.sleep(1000);
					timerLabel.setText("Time Remaining:" + min + " : "+ sec);
				}
				catch(InterruptedException ie){}
				sec--;
				if(sec==0 && min != 0){
					min--;
					sec = 59;
				}
				if(min == 0){
					dispose();
					setVisible(false);
				}
			}
			
		}
	
	public static void main(String args[]) {
		tts = new OnlineExaminationSystem();
	}
}                




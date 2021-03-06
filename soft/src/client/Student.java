package client;


import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dao.DAO;

import java.awt.Color;
import java.awt.Font;


public class Student extends JFrame implements ActionListener, WindowListener{

   JFrame frame;
   BufferedImage img = null;
 
   imageReceiver ir = new imageReceiver();
   JButton logoutbutton;
   JButton QA_button;
   JButton screenshare;
   JButton chatting;
   JButton enter;
   JLabel student;
     
   Mymouse aa = new Mymouse();
   int student_number;  
  
   private static boolean listener=true;  
   
   public static String Ipconfig ()throws Exception{
	   
	   MulticastReceiver mr = new MulticastReceiver();
	   String IP_ADDRESS = null;
	   InetAddress ipAddress = null;
	   ipAddress = InetAddress.getLocalHost() ;
           
	   StringTokenizer st = new StringTokenizer(ipAddress.getHostAddress(), ".");
	   String[] temp = new String[3];
       
	   for(int i=0 ;i<3;i++){
		   temp[i]="";
	   }
       
	   for(int i=0 ;i<3;i++){
		   temp[i]= st.nextToken();
	   }
       
	   IP_ADDRESS = temp[0] + "." + temp[1] + "." + temp[2] + "." + "255";
	   System.out.println(IP_ADDRESS);
       
	   return IP_ADDRESS;
   }

   public static void run(String args[]) throws Exception{
	   MulticastReceiver mr = new MulticastReceiver();
	   mr.main(null);
   }
 
   public Student(){
	   setTitle("Student Screen");
	   setBounds(300, 70, 1350, 838);
	   setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   setLayout(null);
    
	   //이미지 받아오기
	   try{
		   img = ImageIO.read(new File("img/학생화면.png"));
	   }catch(IOException e){
		   System.out.println("이미지를 불러오지 못했습니다.");
		   System.exit(0);
	   }
      

	   JLayeredPane layeredpane =new JLayeredPane();
	   layeredpane.setBounds(0, 0, 1380, 900);
	   layeredpane.setLayout(null);
      
	   Mypanel panel = new Mypanel();
	   panel.setBounds(0, 0, 1380, 900);      
   
	   student_number = Login.id;
	   System.out.println(Login.id);
      
	   student = new JLabel (student_number + "");
	   student.setFont(new Font("HYPost", Font.PLAIN, 60));
	   student.setForeground(Color.WHITE);
	   student.setBounds(20, 750, 400, 50);
      
	   layeredpane.add(student);
      
	   enter = new JButton(new ImageIcon("img/enter.png"));
	   enter.setBounds(1060, 575 ,263, 145);
	   enter.setBorder(BorderFactory.createEmptyBorder());
      
	   layeredpane.add(enter);
     
	   enter.addActionListener(this);
	   enter.addMouseListener(aa);  
      
       logoutbutton = new JButton(new ImageIcon("img/로그아웃학생.png"));//안에 이미지 넣어주기
       logoutbutton.setBounds(678, 736, 328, 63);
       logoutbutton.setBorder(BorderFactory.createEmptyBorder());
       layeredpane.add(logoutbutton);
       logoutbutton.addActionListener(this);
       logoutbutton.addMouseListener(aa);
      
       QA_button = new JButton(new ImageIcon("img/QA학생.png"));//안에 이미지 넣어주기
       QA_button.setBounds(1022, 736, 328, 63);
       QA_button.setBorder(BorderFactory.createEmptyBorder());
       QA_button.addActionListener(this);
       QA_button.addMouseListener(aa);
       layeredpane.add(QA_button);
      
       screenshare = new JButton(new ImageIcon("img/screenshare학생.png"));//안에 이미지 넣어주기
       screenshare.setBounds(181, 286, 461, 146);
       screenshare.setBorder(BorderFactory.createEmptyBorder());
       layeredpane.add(screenshare);
       screenshare.addMouseListener(aa);
       screenshare.addActionListener(this);

       chatting = new JButton(new ImageIcon("img/chatting학생.png"));//안에 이미지 넣어주기
       chatting.setBounds(696, 286, 461, 146);
       chatting.setBorder(BorderFactory.createEmptyBorder());
       layeredpane.add(chatting);
       chatting.addMouseListener(aa);
       chatting.addActionListener(this);
      
       addWindowListener(this);
   
       layeredpane.add(panel);//패널1을 레이아웃에 넣기
      
       add(layeredpane);
       setVisible(true);
   }
   
   class Mypanel extends JPanel{
	   public void paint(Graphics g){
		   g.drawImage(img, 0, 0, null);
	   }
   }
   
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == screenshare)
      { 
    	  try {
    		  imageReceiver ir = new imageReceiver();
    		  ir.start();
    	  } catch (Exception e2) {
    		  // TODO Auto-generated catch block
    		  e2.printStackTrace();
    	  }
      }
      if(e.getSource() == chatting)
      { 
    	  try {
    		  client_frame cf = new client_frame();
    		  cf.main(null);
    	  } catch (Exception e1) {
    		  // TODO Auto-generated catch block
    		  e1.printStackTrace();
    	  }
      }
      if(e.getSource() == logoutbutton)
      { 
    	  JOptionPane.showMessageDialog(null, "시스템을 종료 합니다.");
          new DAO().disconnect(student_number);
          System.exit(0);
      }
      if(e.getSource() == QA_button)
      { 
    	  Q_A.main(null);
      }
      if(e.getSource() == enter)
      { 
          Enter ent = new Enter();
          ent.start();
      }
   }
   
   class Mymouse implements MouseListener{
	   public void mouseEntered(MouseEvent e){

		   if(e.getSource() == chatting){           
			   chatting.setIcon(new ImageIcon("img/chatting학생반전.png"));
		   }
        
           if(e.getSource() == screenshare){            
        	   screenshare.setIcon(new ImageIcon("img/screenshare학생반전.png"));
           }

           if(e.getSource() == logoutbutton){            
        	   logoutbutton.setIcon(new ImageIcon("img/로그아웃학생반전.png"));          
           }

           if(e.getSource() == QA_button){           
        	   QA_button.setIcon(new ImageIcon("img/QA학생반전.png"));
           }

           if(e.getSource() == enter)
           {
        	   enter.setIcon(new ImageIcon("img/enter반전.png"));
           }
	   }

	   @Override
	   public void mouseClicked(MouseEvent arg0) {
		   // TODO Auto-generated method stub
	   }

	   @Override
	   public void mouseExited(MouseEvent e) {
		   // TODO Auto-generated method stub
	   
		   if(e.getSource() == chatting){           
			   chatting.setIcon(new ImageIcon("img/chatting학생.png"));
		   }
	    
	       if(e.getSource() == screenshare){            
	    	   screenshare.setIcon(new ImageIcon("img/screenshare학생.png"));
	       }

	       if(e.getSource() == logoutbutton){            
	    	   logoutbutton.setIcon(new ImageIcon("img/로그아웃학생.png"));          
	       }

	       if(e.getSource() == QA_button){           
	    	   QA_button.setIcon(new ImageIcon("img/QA학생.png"));
	       }

	       if(e.getSource() == enter)
	       {
	           enter.setIcon(new ImageIcon("img/enter.png"));
	       }
  
	   }

	   @Override
	   public void mousePressed(MouseEvent arg0) {
	      // TODO Auto-generated method stub
	      
	   }

	   @Override
	   public void mouseReleased(MouseEvent arg0) {
	      // TODO Auto-generated method stub
	      
	   }
   }

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("exit");
		new DAO().disconnect(student_number);
		System.exit(0);
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
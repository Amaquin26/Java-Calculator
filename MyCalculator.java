/***
   MyCalculator using Java GUI (Graphical User Interface)
**/
import java.awt.*;      //positioning, set color, set size,frame, etc...
import java.awt.event.*; //capture the GUI, usually initiated by the human user
import javax.swing.*;   //look and feel of the GUI (Graphical User Interface)

public class MyCalculator implements ActionListener{
   //define the primary container of the widget (window gadgets i.e. TextField,Label,Button,etc...)
   JFrame frame = new JFrame(); 
   //define a container for the JButtons
   JPanel buttonContainer = new JPanel(new GridLayout(4,4));
   //define the result display
   JTextField txtResult = new JTextField("0."); 
   //define the buttons
   JButton[] numBtn = new JButton[10];
   JButton[] funcBtn = {new JButton("*"),new JButton("/"),new JButton("+"),new JButton("-"),new JButton("AC"),new JButton("=")};
   //
   String result="";
   StringBuffer sb = new StringBuffer();
   InfixToPostfix itf;
   
   public MyCalculator(){
      Font font = new Font("Arial",Font.PLAIN,30);
      
      //set the result textfield
      txtResult.setFont(font);
      txtResult.setMargin(new Insets(10, 10, 10, 10));
      txtResult.setHorizontalAlignment(JTextField.RIGHT);
      txtResult.setEditable(false);
      
      //iterate the numBtn array to create number buttons from 0 to 9
      for(int i = 0; i < numBtn.length;i++){
         numBtn[i] = new JButton(i+"");
         numBtn[i].setFont(font);
         numBtn[i].setForeground(Color.BLUE);
         numBtn[i].addActionListener(this);
      }
      
      //iterate the funcBtn array to setup the buttons
      for(int i = 0; i < funcBtn.length;i++){
            funcBtn[i].setFont(font);
            
            //set specific color for AC button which is at index 4
            Color color = (i == 4)?Color.RED:Color.BLUE;
           
            funcBtn[i].setForeground(color);
            funcBtn[i].addActionListener(this);
      }
      
      //Add buttons, for the first 3 rows of button
      int numIndex = 1; //starts with number 1
      int funcIndex = 0; //starts with the multiply button
      for(int i = 1; i <= 12;i++){ 
         if(i == 4 || i == 8 || i == 12)
            buttonContainer.add(funcBtn[funcIndex++]);
         else
            buttonContainer.add(numBtn[numIndex++]);    
      }
      
      //add the buttons located at the bottom row
      buttonContainer.add(funcBtn[4]); buttonContainer.add(numBtn[0]); buttonContainer.add(funcBtn[5]); buttonContainer.add(funcBtn[3]);
      
      frame.setLayout(new BorderLayout()); //set the layout of this container
      //add components to the frame
      frame.add(txtResult,BorderLayout.NORTH);
      frame.add(buttonContainer,BorderLayout.CENTER);
      //
      frame.setResizable(false); //do not the frame to be resized
      frame.setSize(400,400); //set the container size (400(horizontal)pixel x 400(vertical) pixel)
      frame.setLocationRelativeTo(null); //positioning of the container in the screen
      frame.setTitle("AMAQUIN, Brayl C. BSIT-2"); //set the title of the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //dispose the JFrame and terminate the program
      frame.setVisible(true);//display the container
   }
   
   public void actionPerformed(ActionEvent e){
      String action = e.getActionCommand(); //will capture the label of the button
      sb.append(action);
      result = sb.toString();
         switch(action){
            case "AC": 
               result="0.";
               txtResult.setText(result); 
               sb=new StringBuffer();
               break;
            case "=":  
               itf = new InfixToPostfix(txtResult.getText().trim());
               sb=new StringBuffer();
               sb.append(itf.compute());
               result = itf.compute()+"";
         }
      txtResult.setText(result);
   }
      
   static public void main(String ...args){
      new MyCalculator();
   }
}//end of class
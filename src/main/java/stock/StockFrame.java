package stock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import auth.LoginFrame;

public class StockFrame extends JFrame implements ActionListener{
    JFrame frame =new JFrame();
    JButton logoutButton = new JButton("LOGOUT");
    ItemList itemList = new ItemList();
    
 
    public StockFrame() {
        //load sample data
        Furniture chair = new Furniture(10,"teacher chair","description","wood");
        Furniture table = new Furniture(20,"teacher table","description","wood");
        this.itemList.addFurnitures(chair);
        this.itemList.addFurnitures(table);
        Electronic fan = new Electronic(50,"table fan","fan description","AC");
        Electronic tv = new Electronic(5,"smart tv","tv description","AC");
        this.itemList.addElectronics(fan);
        this.itemList.addElectronics(tv);

        //frame setup
        frame.setLayout(null);
        frame.setTitle("MAIN PAGE");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds(250, 100, 850, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        //addComponentsToFrame
        frame.add(logoutButton);

        //set location and size
        logoutButton.setBounds(100, 100, 100, 100);

        // addActionEvent
        logoutButton.addActionListener(this);
     

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            frame.dispose();
            new LoginFrame().setVisible(true);
          
      }
    }


}
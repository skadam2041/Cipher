import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageOperation {

    public static int Key;
    public static boolean isEncrypted;
    public static  void SetKey(int k){
        Key = k;
        isEncrypted = true;
    }

    public  static  void operate(int key){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file = fileChooser.getSelectedFile();

        //fileInputStream class
        try{
            FileInputStream fis = new FileInputStream(file);
            byte [] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data){
                System.out.println(b);
                data[i] = (byte) (b^key);
                i++;
            }

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null,"Done");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }



    public static void main(String[] args) {
        System.out.println("testing");
        JFrame f = new JFrame();
        f.setTitle("Cipher");
        f.setSize(500,500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //setting font
        Font font = new Font("Roboto",Font.BOLD,25);

        //creating textarea
        JTextField textField = new JTextField( "Set Key to Encrypt" , 20);
        textField.setFont(font);
        //creating button
        JButton button1 = new JButton();
        button1.setText("Encrypt");
        button1.setFont(font);

        button1.addActionListener(e->{
            if(isEncrypted == false) {
                String encryptedKey = textField.getText();
                int temp = Integer.parseInt(encryptedKey);
                SetKey(temp);
                operate(temp);
            }
            else{
                JOptionPane.showMessageDialog(null,"Already Encrpted");
            }
        });


        JTextField textField1 = new JTextField( "Provide Key to Decrypt" , 20);
        textField1.setFont(font);
        //creating button
        JButton button2 = new JButton();
        button2.setText("Decrypt");
        button2.setFont(font);

        button2.addActionListener(e->{

            if(isEncrypted == true ) {

                String Decrypted = textField1.getText();
                int temp = Integer.parseInt(Decrypted);
                if(temp == Key){
                    operate(temp);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Wrong Key!");
                }

            }
            else{
                JOptionPane.showMessageDialog(null,"Encrypt First!");
            }
        });



        f.setLayout(new FlowLayout());

        f.add(textField);
        f.add(button1);
        f.add(textField1);
        f.add(button2);

        f.setVisible(true);

    }
}

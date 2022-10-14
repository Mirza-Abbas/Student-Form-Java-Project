import org.json.simple.JsonObject; 
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

class StudentForm extends JFrame implements ActionListener{

    JsonObject Object1;     //JsonObject to insert details in Json File

    JLabel LabelName, LabelRollNo, LabelBatch, LabelSection, LabelGender, LabelQualification, LabelAddress, LabelCountry;

    JTextField Name, RollNO, Batch, Section;

    JRadioButton MaleButton, FemaleButton;

    ButtonGroup GenderGroup;  // for Male & Female Button

    JCheckBox Matric, Intermediate, Graduate, PostGraduate;

    JTextArea Address;

    JComboBox Country;

    JButton SaveButton, PrintButton, Database, Fetch;

    JOptionPane MessagePanel;

    String[] Countries={"Pakistan", "India", "China", "Iran", "Saudi Arab"}; //For Country jcombobox

    String Gender, Qualification;   //to save gender & qualification details in string

    public StudentForm(){   //Constructor

        JPanel P1=new JPanel(null);     //JPanel Settings
        P1.setBackground(Color.cyan);

        setSize(450, 450);          //Jframe settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setTitle("Student Registration Form");
        getContentPane().setBackground(Color.cyan);

        RollNO= new JTextField(25);     //JTextFields settings
        Name = new JTextField(25);
        Batch = new JTextField(25);
        Section = new JTextField(25);

        LabelName = new JLabel("Name: ");           //JLabels Settings
        LabelRollNo = new JLabel("Roll No:");
        LabelBatch = new JLabel("Batch: ");
        LabelSection = new JLabel("Section: ");
        LabelGender = new JLabel("Gender:");
        LabelQualification=new JLabel("Qualification:");
        LabelAddress =new JLabel("Address:");
        LabelCountry=new JLabel("Country:");

        SaveButton = new JButton("Save");       //JButtons Settings
        PrintButton = new JButton("Print");
        Database = new JButton("Database");
        Fetch = new JButton("Fetch");

        MaleButton=new JRadioButton("Male");            //JRadioButtons Settings
        MaleButton.setBackground(Color.CYAN);
        FemaleButton=new JRadioButton("Female");
        FemaleButton.setBackground(Color.CYAN);

        Matric=new JCheckBox("Matric");                 //JCheckBoxSettings
        Matric.setBackground(Color.CYAN);
        Intermediate=new JCheckBox("Intermediate");
        Intermediate.setBackground(Color.CYAN);
        Graduate=new JCheckBox("Graduate");
        Graduate.setBackground(Color.CYAN);
        PostGraduate=new JCheckBox("PostGraduate");
        PostGraduate.setBackground(Color.CYAN);

        Address=new JTextArea();                //JTextArea Settings
        Address.setLineWrap(true);

        Country=new JComboBox(Countries);       //JComboBox Settings

        MessagePanel=new JOptionPane();       //JOptionPane Initialization  

        GenderGroup = new ButtonGroup();     //ButtonGroup Initialization

        //Components' Location Setting
        LabelName.setBounds(30, 20, 50, 20);  
        P1.add(LabelName);
        Name.setBounds(110, 20, 100, 20);
        P1.add(Name);
        LabelRollNo.setBounds(30, 50, 50, 20);
        P1.add(LabelRollNo);
        RollNO.setBounds(110, 50, 100, 20);
        P1.add(RollNO);
        LabelBatch.setBounds(30, 80, 50, 20);
        P1.add(LabelBatch);
        Batch.setBounds(110, 80, 100, 20);
        P1.add(Batch);
        LabelSection.setBounds(30, 110, 50, 20);
        P1.add(LabelSection);
        Section.setBounds(110, 110, 100, 20);
        P1.add(Section);
        LabelGender.setBounds(30, 140, 50, 20);
        P1.add(LabelGender);
        MaleButton.setBounds(110, 140, 100, 20);
        P1.add(MaleButton);
        FemaleButton.setBounds(230, 140, 100, 20);
        P1.add(FemaleButton);
        LabelQualification.setBounds(30, 170, 75, 20);
        P1.add(LabelQualification);
        Matric.setBounds(110, 170, 100, 20);
        P1.add(Matric);
        Intermediate.setBounds(230, 170, 100, 20);
        P1.add(Intermediate);
        Graduate.setBounds(110, 200, 100, 20);
        P1.add(Graduate);
        PostGraduate.setBounds(230, 200, 110, 20);
        P1.add(PostGraduate);
        LabelAddress.setBounds(30, 240, 50, 20);
        P1.add(LabelAddress);
        Address.setBounds(110, 240, 150, 50);
        P1.add(Address);
        LabelCountry.setBounds(30,310, 50, 20);
        P1.add(LabelCountry);
        Country.setBounds(110, 310, 100, 20);
        P1.add(Country);
        SaveButton.setBounds(30, 350, 70, 20);
        P1.add(SaveButton);
        PrintButton.setBounds(110, 350, 70, 20);
        P1.add(PrintButton);
        Database.setBounds(190, 350, 90, 20);
        P1.add(Database);
        Fetch.setBounds(290, 350, 70, 20);
        P1.add(Fetch);
        setContentPane(P1);

        GenderGroup.add(MaleButton);      //GenderGroup Settings
        GenderGroup.add(FemaleButton);

        SaveButton.addActionListener(this);         //Adding ActionListener to buttons
        PrintButton.addActionListener(this);
        Database.addActionListener(this);
        Fetch.addActionListener(this);
        setVisible(true);

    }

    void save(){ // method for when save button is selected

        Writer write;       //Writer object to write in file
        
        try {

            write = new FileWriter("Task.json");
            write.write(Object1.toString());
            write.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    void set(){     //method to set Gender & Qualification

        { //Gender Selection
            
            if(MaleButton.isSelected()) {       // When Male Button's Selected
                
                Gender=MaleButton.getText();
            } else {                            //else Female Button's Selected
                
                Gender=FemaleButton.getText();
            }
        }

        { //Qualification Selection
            if(Matric.isSelected()){    //When Matric is Selected
            
                Qualification=Matric.getText();
            }
            if(Intermediate.isSelected()){        //When Intermediate is Selected                    
            
                Qualification=Intermediate.getText();
            }
            if(Graduate.isSelected()){      //When Graduate is Selected
             
                Qualification=Graduate.getText();
            }
            if(PostGraduate.isSelected()){     //When PostGraduate is Selected                       
            
                Qualification=PostGraduate.getText();
            }
        }
    }

    void Insert(){      //Method to insert details to Database

        String Driver="com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/StudentForm_db";
        String uname = "___";      //Replace with Database Name
        String pass="___";      //Replace with Database Password 

        //Databse Query to insert details to database
        String Query="Insert into Student_details Values ('"+Name.getText()+"','"+RollNO.getText()+"','"+Batch.getText()+"','"+Gender+"','"+Section.getText()+"','"+Qualification+"','"+Address.getText()+"','"+Countries[Country.getSelectedIndex()]+"');";

        try {

            Class.forName(Driver);
            Connection conn=DriverManager.getConnection(url, uname, pass);      //to establish connection with database server

            System.out.println("Database Connected");       //Shows that connection's established

            Statement s=conn.createStatement();
            s.executeUpdate(Query);             //to execute query

            s.close();

            MessagePanel.showMessageDialog(null, "Data Inserted Succesfully");
            //Shows DialogueBox Message that data has been inserted successfully

        } catch (Exception e) {

            System.out.println("SQLException: " + e.getMessage());
        }
    }

    void Fetch(){       //Method to Fetch data from Database

        String Driver="com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/StudentForm_db";   
        String uname = "___";      //Replace with Database Name
        String pass="___";      //Replace with Databse Password 

        //Databse Query to Fetch Data From database
        String Query="Select * From Student_details where Std_Name = '" + Name.getText() + "' AND RollNo = '" + RollNO.getText() + "' AND Batch = '" + Batch.getText() + "' AND Gender = '" + Gender + "' AND Section = '" + Section.getText() + "' AND Qualification = '" + Qualification + "' AND Address = '" + Address.getText() + "' AND Country = '" + Countries[Country.getSelectedIndex()] + "'";

        try {

            Class.forName(Driver);
            Connection conn=DriverManager.getConnection(url, uname, pass);      //to establish connection with database server

            Statement s=conn.createStatement();
            ResultSet rs = s.executeQuery(Query);       //to execute query

            //loop to fetch data from databse, store and diplay it
            while(rs.next()) {

                String TempName = rs.getString(1);      //Stores data in string
                String TempROllNo = rs.getString(2);
                String TempBatch = rs.getString(3);
                String TempSec = rs.getString(4);
                String TempGen = rs.getString(5);
                String TempQualif = rs.getString(6);
                String TempAdd = rs.getString(7);
                String TempCountry = rs.getString(8);
                
                this.dispose();         //Closes First JFrame

                JFrame F2=new JFrame();     // will open new Jframe to display details
                JPanel P2=new JPanel(null);  //New JPanel

                F2.setLayout(null);         //JFrame Settings
                F2.setBounds(0, 0, 500, 300);  
                F2.setVisible(true);  
                F2.setTitle("Student Registration Form");   

                P2.setBackground(Color.CYAN);  //JPanel Settings

                //Componens' Location Setting
		        LabelName.setBounds(0, 0, 50, 20);  
		        P2.add(LabelName);
                LabelName = new JLabel(TempName);
		        LabelName.setBounds(50, 0, 500, 20);
                P2.add(LabelName);
		        F2.setContentPane(P2);

                LabelRollNo.setBounds(0, 30, 50, 20);
                P2.add(LabelRollNo);
                LabelRollNo = new JLabel(TempROllNo);
                LabelRollNo.setBounds(50, 30, 500, 20);
                P2.add(LabelRollNo);

                LabelBatch.setBounds(0, 60, 50, 20);
                P2.add(LabelBatch);
                LabelBatch = new JLabel(TempBatch);
                LabelBatch.setBounds(50, 60, 500, 20);
                P2.add(LabelBatch);

                LabelSection.setBounds(0, 90, 50, 20);
                P2.add(LabelSection);
                LabelSection = new JLabel(TempSec);
                LabelSection.setBounds(50, 90, 500, 20);
                P2.add(LabelSection);

                LabelGender.setBounds(00, 120, 50, 20);
                P2.add(LabelGender);
                LabelGender=new JLabel(TempGen);
                LabelGender.setBounds(50, 120, 500, 20);
                P2.add(LabelGender);

                LabelQualification.setBounds(00, 150, 80, 20);
                P2.add(LabelQualification);
                LabelQualification=new JLabel(TempQualif);
                LabelQualification.setBounds(80, 150, 500, 20);
                P2.add(LabelQualification);

                LabelAddress.setBounds(00, 180, 60, 20);
                P2.add(LabelAddress);
                LabelAddress=new JLabel(TempAdd);
                LabelAddress.setBounds(55, 180, 500, 20);
                P2.add(LabelAddress);

                LabelCountry.setBounds(00, 210, 50, 20);
                P2.add(LabelCountry);
                LabelCountry=new JLabel(TempCountry);
                LabelCountry.setBounds(50, 210, 500, 20);
                P2.add(LabelCountry);

                F2.setDefaultCloseOperation(EXIT_ON_CLOSE);

            }
            s.close();
            rs.close();

        } catch (Exception e) {

            System.out.println("SQLException: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        set();  //will set student details
        Writer writer;  // For JSON
        Object1 = new JsonObject();

        Object1.put("Name ", Name.getText());       //Puts details in JSON File
        Object1.put("RollNo ", RollNO.getText());
        Object1.put("Batch ", Batch.getText());
        Object1.put("Section ", Section.getText());
        Object1.put("Gender ", Gender);
        Object1.put("Qualification ", Qualification);
        Object1.put("Address ", Address.getText());
        Object1.put("Country ", Countries[Country.getSelectedIndex()]);

        if(e.getSource()==SaveButton) {     // when save button is selected

            try {

                save();  //to write in JSON File
                MessagePanel.showMessageDialog(null, "Details Saved");
                //shows message that details are saved 

            } catch (Exception a) {

                a.printStackTrace();
            }
        }
        else if(e.getSource()==PrintButton) {   // when Print button is selected

            save();     //method to write in JSON File
            this.dispose();        //Closes First JFrame

            JFrame F2=new JFrame();     // will open new Jframe to display details
            JPanel P2=new JPanel(null);  //New JPanel

            F2.setLayout(null);             //JFrame Settings
            F2.setBounds(0, 0, 500, 300);
            F2.setVisible(true);
            F2.setTitle("Student Registration Form"); 

            P2.setBackground(Color.CYAN); //JPanel Settings

            //Components' Location Setting
		    LabelName.setBounds(0, 0, 50, 20);  
		    P2.add(LabelName);
            LabelName = new JLabel(Name.getText());
		    LabelName.setBounds(50, 0, 500, 20);
            P2.add(LabelName);
		    F2.setContentPane(P2);

            LabelRollNo.setBounds(0, 30, 50, 20);
            P2.add(LabelRollNo);
            LabelRollNo = new JLabel(RollNO.getText());
            LabelRollNo.setBounds(50, 30, 500, 20);
            P2.add(LabelRollNo);

            LabelBatch.setBounds(0, 60, 50, 20);
            P2.add(LabelBatch);
            LabelBatch = new JLabel(Batch.getText());
            LabelBatch.setBounds(50, 60, 500, 20);
            P2.add(LabelBatch);

            LabelSection.setBounds(0, 90, 50, 20);
            P2.add(LabelSection);
            LabelSection = new JLabel(Section.getText());
            LabelSection.setBounds(50, 90, 500, 20);
            P2.add(LabelSection);

            LabelGender.setBounds(00, 120, 50, 20);
            P2.add(LabelGender);
            LabelGender=new JLabel(Gender);
            LabelGender.setBounds(50, 120, 500, 20);
            P2.add(LabelGender);

            LabelQualification.setBounds(00, 150, 80, 20);
            P2.add(LabelQualification);
            LabelQualification=new JLabel(Qualification);
            LabelQualification.setBounds(80, 150, 500, 20);
            P2.add(LabelQualification);

            LabelAddress.setBounds(00, 180, 60, 20);
            P2.add(LabelAddress);
            LabelAddress=new JLabel(Address.getText());
            LabelAddress.setBounds(55, 180, 500, 20);
            P2.add(LabelAddress);

            LabelCountry.setBounds(00, 210, 50, 20);
            P2.add(LabelCountry);
            LabelCountry=new JLabel(Countries[Country.getSelectedIndex()]);
            LabelCountry.setBounds(50, 210, 500, 20);
            P2.add(LabelCountry);

            F2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        else if(e.getSource()==Database) {      //When Databse Button is Selected

            Insert();
        }
        else if(e.getSource()==Fetch) {     //When Fetch Button is Selected
            
            Fetch();
        }
    }
}

public class StudentFormDemo {      //Main Class
    public static void main(String[] args) throws SQLException{     //Main Method

        StudentForm Form1 = new StudentForm();      //StudentForm Object will auto call constructor
    }
}

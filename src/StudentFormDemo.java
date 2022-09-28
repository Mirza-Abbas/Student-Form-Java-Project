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
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.Writer;

class StudentForm extends JFrame implements ActionListener{

    JsonObject Object1;

    JTextField Name, RollNO, Batch, Section;

    JLabel LabelName, LabelRollNo, LabelBatch, LabelSection, LabelGender, LabelQualification, LabelAddress, LabelCountry;

    JButton SaveButton, PrintButton;

    JRadioButton MaleButton, FemaleButton;

    JOptionPane MessagePanel;

    JCheckBox Matric, Intermediate, Graduate, PostGraduate;

    JTextArea Address;

    String[] Countries={"Pakistan", "India", "China", "Iran", "Saudi Arab"};
    JComboBox Country;
    
    ButtonGroup GenderGroup;  // for Male & Female Button

    public StudentForm(){   //Constructor

        setSize(350, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setTitle("Student Registration Form");
        getContentPane().setBackground(Color.cyan);

        RollNO= new JTextField(25);
        Name = new JTextField(25);
        Batch = new JTextField(25);
        Section = new JTextField(25);

        LabelName = new JLabel("Name: ");
        LabelRollNo = new JLabel("Roll No:");
        LabelBatch = new JLabel("Batch: ");
        LabelSection = new JLabel("Section: ");
        LabelGender = new JLabel("Gender:");
        LabelQualification=new JLabel("Qualification:");
        LabelAddress =new JLabel("Address:");
        LabelCountry=new JLabel("Country:");

        SaveButton = new JButton("Save");
        PrintButton = new JButton("Print");

        MaleButton=new JRadioButton("Male");
        FemaleButton=new JRadioButton("Female");

        Matric=new JCheckBox("Matric");
        Intermediate=new JCheckBox("Intermediate");
        Graduate=new JCheckBox("Graduate");
        PostGraduate=new JCheckBox("PostGraduate");

        Address=new JTextArea(6, 15);

        Country=new JComboBox(Countries);

        MessagePanel=new JOptionPane();

        GenderGroup = new ButtonGroup();

        add(LabelName);
        add(Name);
        add(LabelRollNo);
        add(RollNO);
        add(LabelBatch);
        add(Batch);
        add(LabelSection);
        add(Section);
        add(LabelGender);
        add(MaleButton);
        add(FemaleButton);
        add(LabelQualification);
        add(Matric);
        add(Intermediate);
        add(Graduate);
        add(PostGraduate);
        add(LabelAddress);
        add(Address);
        add(LabelCountry);
        add(Country);
        add(SaveButton);
        add(PrintButton);

        GenderGroup.add(MaleButton);      
        GenderGroup.add(FemaleButton);

        SaveButton.addActionListener(this);
        PrintButton.addActionListener(this);
        setVisible(true);
    }

    void save(){ // method for when save button is selected
        Writer write;
        try {
            write = new FileWriter("Task.json");
                write.write(Object1.toString());
                write.close();
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Writer writer;  // For JSON
        Object1 = new JsonObject();

        Object1.put("Name ", Name.getText());
        Object1.put("RollNo ", RollNO.getText());
        Object1.put("Batch ", Batch.getText());
        Object1.put("Section ", Section.getText());
        Object1.put("Address ", Address.getText());
        Object1.put("Country ", Countries[Country.getSelectedIndex()]);

        { //Gender Selection
            if(MaleButton.isSelected()) {       // When Male Button's Selected
                Object1.put("Gender ", MaleButton.getText());
            } else {                            //else Female Button's Selected
                Object1.put("Gender ", FemaleButton.getText());
            }
        }

        { //Qualification Selection
            if(Matric.isSelected()){    //When Matric is Selected
                Object1.put("Qualification ", Matric.getText());
            }
            if(Intermediate.isSelected()){        //When Intermediate is Selected                    
                Object1.put("Qualification ", Intermediate.getText());
            }
            if(Graduate.isSelected()){      //When Graduate is Selected
                Object1.put("Qualification ", Graduate.getText());
            }
            if(PostGraduate.isSelected()){     //When PostGraduate is Selected                       
                Object1.put("Qualification ", PostGraduate.getText());
            }
        }

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
            this.dispose();        //Closes First Frame

            JFrame F2=new JFrame();     // will open new frame to display details
            JPanel P2=new JPanel(null);  //New Panel

            F2.setLayout(null);     //sets Frame Layout to null
            F2.setBounds(0, 0, 500, 300);  //sets Frame size
            F2.setVisible(true);  //sets Frame Visibility
            F2.setTitle("Student Registration Form");   //sets Frame Title
            P2.setBackground(Color.CYAN);  //sets Frame color

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
            LabelGender=new JLabel(Object1.getString("Gender "));
            LabelGender.setBounds(50, 120, 500, 20);
            P2.add(LabelGender);

            LabelQualification.setBounds(00, 150, 80, 20);
            P2.add(LabelQualification);
            LabelQualification=new JLabel(Object1.getString("Qualification "));
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
        
    }
}

public class StudentFormDemo {
    public static void main(String[] args) {
        StudentForm Form1 = new StudentForm();
    }
}

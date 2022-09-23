import org.json.simple.JsonObject; 
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.Writer;

class StudentForm extends JFrame implements ActionListener{

    JTextField Name, RollNO, Batch, Section;

    JLabel LabelName, LabelRollNo, LabelBatch, LabelSection, LabelGender;

    JButton SaveButton, PrintButton;

    JRadioButton MaleButton, FemaleButton;

    JOptionPane MessagePanel;

    ButtonGroup Group1;  // for Male & Female Button

    public StudentForm(){   //Constructor

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        

        RollNO= new JTextField(25);
        Name = new JTextField(25);
        Batch = new JTextField(25);
        Section = new JTextField(25);

        LabelName = new JLabel("Name: ");
        LabelRollNo = new JLabel("Roll No:");
        LabelBatch = new JLabel("Batch: ");
        LabelSection = new JLabel("Section: ");
        LabelGender = new JLabel("Gender:");

        SaveButton = new JButton("Save");
        PrintButton = new JButton("Print");

        MaleButton=new JRadioButton("Male");
        FemaleButton=new JRadioButton("Female");

        MessagePanel=new JOptionPane();

        Group1 = new ButtonGroup();

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
        add(SaveButton);
        add(PrintButton);

        Group1.add(MaleButton);      
        Group1.add(FemaleButton);

        SaveButton.addActionListener(this);
        PrintButton.addActionListener(this);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Writer writer;  // For JSON
        JsonObject Object1 = new JsonObject();

        Object1.put("Name", Name.getText());
        Object1.put("RollNo: ", RollNO.getText());
        Object1.put("Batch: ", Batch.getText());
        Object1.put("Section: ", Section.getText());

        if(MaleButton.isSelected()) {       // When Male Button's Selected
            Object1.put("Gender: ", MaleButton.getText());
        } else {                            //else Female Button's Selected
            Object1.put("Gender: ", FemaleButton.getText());
        }

        if(e.getSource()==SaveButton) {     // when save button is selected
            try {
                writer = new FileWriter("Task.json");
                writer.write(Object1.toString());
                try{
                    if(MaleButton.isSelected()) {
                        Object1.put("Gender: ", MaleButton.getText());
                    }
                    else {
                        Object1.put("Gender: ", FemaleButton.getText());
                    }
                }
                catch (Exception i){
                    i.printStackTrace();
                }

                writer.close();
                
                MessagePanel.showMessageDialog(null, "Details Saved");
                //shows message that details are saved 
            } catch (Exception a) {
                a.printStackTrace();
            }

        }
        else if(e.getSource()==PrintButton) {   // when Print button is selected
            
            this.dispose();

            JFrame F2=new JFrame();     // will open new frame to display details
            F2.setSize(300,300);
            F2.setVisible(true);
            F2.setLayout(new FlowLayout(FlowLayout.LEFT));
            
            

            F2.add(LabelName);
            LabelName = new JLabel(Name.getText());
           
            F2.add(LabelName);
            F2.add(LabelRollNo);
            LabelRollNo = new JLabel(RollNO.getText());
            F2.add(LabelRollNo);
            F2.add(LabelBatch);
            LabelBatch = new JLabel(Batch.getText());
            F2.add(LabelBatch);
            F2.add(LabelSection);
            LabelSection = new JLabel(Section.getText());
            F2.add(LabelSection);

            F2.add(LabelGender);
            if(MaleButton.isSelected()){
                LabelGender=new JLabel("Male");
            }
            else{
                LabelGender=new JLabel("Female");
            }
            F2.add(LabelGender);
            F2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        
    }
}

class StudentFormDemo {
    public static void main(String[] args) {

        StudentForm Form1 = new StudentForm();

    }
}

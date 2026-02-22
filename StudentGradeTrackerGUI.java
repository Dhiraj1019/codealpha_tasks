import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

class Student {
    String name;
    int marks;
    Student(String name, int marks){
        this.name=name;
        this.marks=marks;
    }
}

public class StudentGradeTrackerGUI extends JFrame {

    ArrayList<Student> students=new ArrayList<>();
    JTextField nameField, marksField;
    DefaultTableModel model;
    JLabel stats;

    StudentGradeTrackerGUI(){

        setTitle("🎓 Student Grade Tracker - CodeAlpha");
        setSize(750,520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== Colors =====
        Color bg=new Color(25,25,35);
        Color card=new Color(40,40,55);
        Color accent=new Color(0,180,150);

        JPanel main=new JPanel(new BorderLayout(15,15));
        main.setBackground(bg);
        main.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ===== TITLE =====
        JLabel title=new JLabel("Student Grade Tracker",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,26));
        title.setForeground(Color.WHITE);
        main.add(title,BorderLayout.NORTH);

        // ===== INPUT PANEL =====
        JPanel input=new JPanel();
        input.setBackground(card);
        input.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(accent,2),
                " Add Student ",
                0,0,new Font("Segoe UI",Font.BOLD,16),Color.WHITE));

        nameField=new JTextField(12);
        marksField=new JTextField(5);

        JButton add=new JButton("Add");
        JButton report=new JButton("Show Report");

        styleButton(add,accent);
        styleButton(report,new Color(70,130,255));

        input.add(label("Name"));
        input.add(nameField);
        input.add(label("Marks"));
        input.add(marksField);
        input.add(add);
        input.add(report);

        main.add(input,BorderLayout.CENTER);

        // ===== TABLE =====
        String cols[]={"Student","Marks"};
        model=new DefaultTableModel(cols,0);
        JTable table=new JTable(model);

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));
        table.setSelectionBackground(accent);

        JScrollPane scroll=new JScrollPane(table);
        main.add(scroll,BorderLayout.SOUTH);

        // ===== STATS =====
        stats=new JLabel("Average: 0   Highest: 0   Lowest: 0",
                SwingConstants.CENTER);
        stats.setForeground(Color.WHITE);
        stats.setFont(new Font("Segoe UI",Font.BOLD,16));
        main.add(stats,BorderLayout.PAGE_END);

        add(main);

        // ===== BUTTON ACTIONS =====
        add.addActionListener(e->addStudent());
        report.addActionListener(e->showReport());

        setVisible(true);
    }

    JLabel label(String text){
        JLabel l=new JLabel(text+": ");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI",Font.BOLD,14));
        return l;
    }

    void styleButton(JButton b,Color c){
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,14));
    }

    void addStudent(){
        try{
            String name=nameField.getText();
            int m=Integer.parseInt(marksField.getText());
            students.add(new Student(name,m));
            model.addRow(new Object[]{name,m});
            nameField.setText("");
            marksField.setText("");
        }catch(Exception e){
            JOptionPane.showMessageDialog(this,"Enter valid marks!");
        }
    }

    void showReport(){
        if(students.size()==0)return;

        int total=0;
        int high=students.get(0).marks;
        int low=students.get(0).marks;

        for(Student s:students){
            total+=s.marks;
            high=Math.max(high,s.marks);
            low=Math.min(low,s.marks);
        }

        double avg=(double)total/students.size();
        stats.setText("Average: "+avg+
                "   Highest: "+high+
                "   Lowest: "+low);
    }

    public static void main(String[] args){
        new StudentGradeTrackerGUI();
    }
}
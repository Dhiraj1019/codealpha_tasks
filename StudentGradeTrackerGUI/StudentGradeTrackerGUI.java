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

    ArrayList<Student> students = new ArrayList<>();
    JTextField nameField, marksField;
    DefaultTableModel model;
    JLabel stats;

    StudentGradeTrackerGUI(){

        setTitle("🎓 Student Grade Tracker - CodeAlpha");
        setSize(800,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color bg = new Color(30,30,45);
        Color card = new Color(45,45,65);
        Color accent = new Color(0,170,140);

        JPanel main = new JPanel(new BorderLayout(10,10));
        main.setBackground(bg);
        main.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // ===== TITLE =====
        JLabel title = new JLabel("Student Grade Tracker",SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI",Font.BOLD,28));
        title.setForeground(Color.WHITE);
        main.add(title,BorderLayout.NORTH);

        // ===== INPUT PANEL =====
        JPanel input = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        input.setBackground(card);

        nameField = new JTextField(12);
        marksField = new JTextField(5);

        JButton addBtn = new JButton("Add");
        JButton reportBtn = new JButton("Report");
        JButton listBtn = new JButton("Names");

        styleButton(addBtn, accent);
        styleButton(reportBtn, new Color(70,130,255));
        styleButton(listBtn, new Color(255,140,0));

        input.add(label("Name"));
        input.add(nameField);
        input.add(label("Marks"));
        input.add(marksField);
        input.add(addBtn);
        input.add(reportBtn);
        input.add(listBtn);

        main.add(input,BorderLayout.CENTER);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"Student","Marks"},0);
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI",Font.PLAIN,14));
        table.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,15));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(" Student Records "));
        main.add(scroll,BorderLayout.SOUTH);

        // ===== STATS =====
        stats = new JLabel("No Data Yet",SwingConstants.CENTER);
        stats.setForeground(Color.WHITE);
        stats.setFont(new Font("Segoe UI",Font.BOLD,16));
        main.add(stats,BorderLayout.PAGE_END);

        add(main);

        addBtn.addActionListener(e->addStudent());
        reportBtn.addActionListener(e->showReport());
        listBtn.addActionListener(e->showStudentNames());

        setVisible(true);
    }

    JLabel label(String t){
        JLabel l = new JLabel(t+": ");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Segoe UI",Font.BOLD,14));
        return l;
    }

    void styleButton(JButton b, Color c){
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("Segoe UI",Font.BOLD,14));
    }

    // ===== ADD STUDENT =====
    void addStudent(){
        String name = nameField.getText().trim();

        if(name.isEmpty()){
            JOptionPane.showMessageDialog(this,"Enter student name!");
            return;
        }

        try{
            int m = Integer.parseInt(marksField.getText().trim());

            students.add(new Student(name,m));
            model.addRow(new Object[]{name,m});

            nameField.setText("");
            marksField.setText("");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this,"Enter valid marks!");
        }
    }

    // ===== SHOW REPORT =====
    void showReport(){
        if(students.isEmpty()){
            JOptionPane.showMessageDialog(this,"No student data!");
            return;
        }

        int total=0;
        Student high=students.get(0);
        Student low=students.get(0);

        for(Student s:students){
            total+=s.marks;
            if(s.marks>high.marks) high=s;
            if(s.marks<low.marks) low=s;
        }

        double avg=(double)total/students.size();

        stats.setText("Average: "+String.format("%.2f",avg)+
                " | Highest: "+high.name+
                " | Lowest: "+low.name);

        JOptionPane.showMessageDialog(this,
                "📊 STUDENT REPORT\n\n"+
                "Total Students: "+students.size()+
                "\nAverage Marks: "+String.format("%.2f",avg)+
                "\nHighest: "+high.name+" ("+high.marks+")"+
                "\nLowest: "+low.name+" ("+low.marks+")");
    }

    // ===== SHOW NAMES ONLY =====
    void showStudentNames(){
        if(students.isEmpty()){
            JOptionPane.showMessageDialog(this,"No student data!");
            return;
        }

        StringBuilder list=new StringBuilder("👨‍🎓 Student Names:\n\n");
        int i=1;
        for(Student s:students){
            list.append(i++).append(". ").append(s.name).append("\n");
        }

        JOptionPane.showMessageDialog(this,list.toString());
    }

    public static void main(String[] args){
        new StudentGradeTrackerGUI();
    }
}

    public static void main(String[] args){
        new StudentGradeTrackerGUI();
    }
}

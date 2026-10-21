import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class eess extends JFrame {

    JTextField Text;
    JButton ADD, Delete, Mark, ClearALL, EDIT;
    JPanel panel1, panel2;
    DefaultListModel<String> model;
    JList<String> list;
    JScrollPane Jscrollpane;
    JLabel comp;
    int y = 0;

    public eess() {

        setTitle("TO DO LIST Manager");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Text = new JTextField(20);

        ADD = new JButton("ADD");
        Delete = new JButton("DELETE");
        Mark = new JButton("MARK");
        ClearALL = new JButton("CLEAR ALL");
        EDIT = new JButton("EDIT");

        panel1 = new JPanel();
        comp = new JLabel();

        panel1.add(Text);
        panel1.add(ADD);

        add(panel1, BorderLayout.NORTH);

        panel2 = new JPanel();

        panel2.add(Delete);
        panel2.add(Mark);
        panel2.add(ClearALL);
        panel2.add(EDIT);
        panel2.add(comp);

        add(panel2, BorderLayout.SOUTH);

        model = new DefaultListModel<>();
        list = new JList<>(model);

        Jscrollpane = new JScrollPane(list);

        ADD.setBackground(Color.green);
        Delete.setBackground(Color.RED);
        Mark.setBackground(Color.yellow);
        EDIT.setBackground(Color.gray);
        ClearALL.setBackground(Color.orange);
        list.setBackground(Color.LIGHT_GRAY);

        add(Jscrollpane, BorderLayout.CENTER);

        // ADD
        ADD.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String task = Text.getText();

                if (!task.isEmpty()) {

                    model.addElement(task);
                    Text.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                        Jscrollpane,
                        "you must enter The task to ADD"
                    );
                }

                complete();
            }
        });

        // DELETE
        Delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();

                if (index != -1) {

                    String task = model.get(index);

                    if (task.endsWith(" ✔️")) {
                        y--;
                    }

                    model.remove(index);
                    complete();

                } else {

                    JOptionPane.showMessageDialog(
                        Jscrollpane,
                        "you must select task to delete"
                    );
                }
            }
        });

        // MARK
        Mark.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();

                if (index != -1) {

                    String task = model.get(index);

                    if (task.endsWith(" ✔️")) {

                        y--;

                        model.set(
                            index,
                            task.replace(" ✔️", "")
                        );

                    } else {

                        y++;

                        model.set(
                            index,
                            task + " ✔️"
                        );
                    }

                    complete();
                }
            }
        });

        // CLEAR ALL
        ClearALL.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int message = JOptionPane.showConfirmDialog(
                    Jscrollpane,
                    "Are you sure you want to clear all tasks?"
                );

                if (message == JOptionPane.YES_OPTION) {

                    model.clear();
                    y = 0;
                    complete();
                }
            }
        });

        // EDIT
        EDIT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int index = list.getSelectedIndex();

                if (index != -1) {

                    String fromlist = list.getSelectedValue();
                    String FromTextfield = Text.getText();

                    if (FromTextfield.isEmpty()) {

                        Text.setText(
                            fromlist.replace(" ✔️", "")
                        );

                    } else {

                        model.set(index, FromTextfield);
                        Text.setText("");

                        if (fromlist.endsWith(" ✔️")) {
                            y--;
                        }

                        complete();
                    }

                } else {

                    JOptionPane.showMessageDialog(
                        Jscrollpane,
                        "you must select task to edit"
                    );
                }
            }
        });

        complete();
        setVisible(true);
    }

    public void complete() {

        int total = model.getSize();

        if (total == 0) {

            comp.setText("tasks complete : 0%");
            comp.setForeground(Color.red);

        } else {

            int percent = (y * 100) / total;

            if (y == total) {
                comp.setForeground(Color.green);
            } else {
                comp.setForeground(Color.red);
            }

            comp.setText(
                "tasks complete : " + percent + "%"
            );
        }
    }

    public static void main(String[] args) {

        new eess();
    }
}
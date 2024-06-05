import java.awt.*;
import java.awt.event.*;
public class AWTSimpleCalculator extends Frame implements ActionListener {
    // Components
    private TextField num1, num2, result;
    private Button addButton, subButton, mulButton, divButton, clrButton;
    private Label label1, label2, label3;

    public AWTSimpleCalculator() {
        // Set up the frame
        setLayout(new GridLayout(6, 2));

        // Initialize components
        label1 = new Label("Number 1:");
        label2 = new Label("Number 2:");
        label3 = new Label("Result:");
        num1 = new TextField();
        num2 = new TextField();
        result = new TextField();
        result.setEditable(false);
        
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        clrButton = new Button("Clear");

        // Add components to the frame
        add(label1);
        add(num1);
        add(label2);
        add(num2);
        add(label3);
        add(result);
        add(addButton);
        add(subButton);
        add(mulButton);
        add(divButton);
        add(clrButton);

        // Add action listeners
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        clrButton.addActionListener(this);

        // Frame settings
        setTitle("AWT Simple Calculator");
        setSize(300, 200);
        setVisible(true);
        
        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double n1 = Double.parseDouble(num1.getText());
            double n2 = Double.parseDouble(num2.getText());
            double res = 0;

            if (e.getSource() == addButton) {
                res = n1 + n2;
            } else if (e.getSource() == subButton) {
                res = n1 - n2;
            } else if (e.getSource() == mulButton) {
                res = n1 * n2;
            } else if (e.getSource() == divButton) {
                if (n2 != 0) {
                    res = n1 / n2;
                } else {
                    result.setText("Error: Div by 0");
                    return;
                }
            } else if (e.getSource() == clrButton) {
                num1.setText("");
                num2.setText("");
                result.setText("");
                return;
            }

            result.setText(String.valueOf(res));
        } catch (NumberFormatException ex) {
            result.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        new AWTSimpleCalculator();
    }
}

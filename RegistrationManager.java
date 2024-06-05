import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class RegistrationManager extends Frame implements ActionListener {
    // Components for user input
    private TextField nameField, emailField;
    private CheckboxGroup genderGroup;
    private Checkbox male, female, other;
    private Choice countryChoice;
    private TextArea displayArea;
    private Button submitButton, displayButton, saveButton;

    // List to store registration data
    private List<Registrant> registrants = new ArrayList<>();

    public RegistrationManager() {
        // Set up the frame
        setLayout(new GridLayout(7, 2));

        // Initialize components
        Label nameLabel = new Label("Name:");
        nameField = new TextField();

        Label emailLabel = new Label("Email:");
        emailField = new TextField();

        Label genderLabel = new Label("Gender:");
        genderGroup = new CheckboxGroup();
        male = new Checkbox("Male", genderGroup, true);
        female = new Checkbox("Female", genderGroup, false);
        other = new Checkbox("Other", genderGroup, false);

        Label countryLabel = new Label("Country:");
        countryChoice = new Choice();
        countryChoice.add("USA");
        countryChoice.add("Canada");
        countryChoice.add("UK");
        countryChoice.add("Australia");

        displayArea = new TextArea();
        displayArea.setEditable(false);

        submitButton = new Button("Submit");
        displayButton = new Button("Display");
        saveButton = new Button("Save to File");

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(genderLabel);
        add(male);
        add(new Label());
        add(female);
        add(new Label());
        add(other);
        add(countryLabel);
        add(countryChoice);
        add(submitButton);
        add(displayButton);
        add(saveButton);
        add(displayArea);

        // Add action listeners
        submitButton.addActionListener(this);
        displayButton.addActionListener(this);
        saveButton.addActionListener(this);

        // Frame settings
        setTitle("Registration Manager");
        setSize(400, 300);
        setVisible(true);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            submitRegistration();
        } else if (e.getSource() == displayButton) {
            displayRegistrations();
        } else if (e.getSource() == saveButton) {
            saveRegistrationsToFile();
        }
    }

    private void submitRegistration() {
        String name = nameField.getText();
        String email = emailField.getText();
        String gender = genderGroup.getSelectedCheckbox().getLabel();
        String country = countryChoice.getSelectedItem();

        Registrant registrant = new Registrant(name, email, gender, country);
        registrants.add(registrant);

        // Clear fields after submission
        nameField.setText("");
        emailField.setText("");
        genderGroup.setSelectedCheckbox(male);
        countryChoice.select(0);
    }

    private void displayRegistrations() {
        StringBuilder sb = new StringBuilder();
        for (Registrant registrant : registrants) {
            sb.append(registrant).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private void saveRegistrationsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("registrations.txt"))) {
            for (Registrant registrant : registrants) {
                writer.println(registrant);
            }
            displayArea.setText("Data saved to registrations.txt");
        } catch (IOException ex) {
            displayArea.setText("Error saving data: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new RegistrationManager();
    }
}

class Registrant {
    private String name;
    private String email;
    private String gender;
    private String country;

    public Registrant(String name, String email, String gender, String country) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Gender: " + gender + ", Country: " + country;
    }
}

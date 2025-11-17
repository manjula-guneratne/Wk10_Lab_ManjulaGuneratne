package application;
	
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.layout.Priority;
import javafx.scene.layout.ColumnConstraints;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		UserDao userDao = new UserDao();
		
		primaryStage.setTitle("Week10_Lab: Manjula Guneratne");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		//To expand the textfields
		ColumnConstraints col0 = new ColumnConstraints();
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setHgrow(Priority.ALWAYS);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setHgrow(Priority.ALWAYS);

		grid.getColumnConstraints().addAll(col0, col1, col2, col3);

		Text scenetitle = new Text("Employment Application");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		Text personInfoSubtitle = new Text("Personal Information");
		personInfoSubtitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		grid.add(personInfoSubtitle, 0, 1, 2, 1);

		Label fullNameLabel = new Label("Full Name:");
		grid.add(fullNameLabel, 0, 2);

		TextField fullnameTextField = new TextField();
		GridPane.setHgrow(fullnameTextField, Priority.ALWAYS);
		fullnameTextField.setMaxWidth(Double.MAX_VALUE);
		//grid.add(fullnameTextField, 1, 1);
		grid.add(fullnameTextField, 1, 2, 3, 1);  // span 3 columns

		Label currentAddressLabel = new Label("Current Address:");
		grid.add(currentAddressLabel, 0, 3);

		TextField currentAddressTextField = new TextField();
		GridPane.setHgrow(currentAddressTextField, Priority.ALWAYS);
		currentAddressTextField.setMaxWidth(Double.MAX_VALUE);
		//grid.add(currentAddressTextField, 1, 2);
		grid.add(currentAddressTextField, 1, 3, 3, 1);  // span 3 columns
		
		Label contactNumberLabel = new Label("Contact Number:");
		grid.add(contactNumberLabel, 0, 4);

		TextField contactNumberTextField = new TextField();
		grid.add(contactNumberTextField, 1, 4);

		Label emailLabel = new Label("Email Address:");
		grid.add(emailLabel, 2, 4);
		
		TextField emailTextField = new TextField();
		grid.add(emailTextField, 3, 4);

		Label educationlevelLabel = new Label("Highest Educational Attainment:");
		grid.add(educationlevelLabel, 0, 5);

		TextField educationlevelTextField = new TextField();
		grid.add(educationlevelTextField, 1, 5);
		
		Label genderLabel = new Label("Gender:");
		grid.add(genderLabel, 2, 5);
		
		//Radio buttons
		HBox gridForRadioButtons = new HBox(10);
		RadioButton maleButton = new RadioButton("Male");
		RadioButton femaleButton = new RadioButton("Female");
		RadioButton otherButton = new RadioButton("Other");		
		//Toggle group
		ToggleGroup group = new ToggleGroup();
		maleButton.setToggleGroup(group);
		femaleButton.setToggleGroup(group);
		otherButton.setToggleGroup(group);		
		// Assigning values to Radio buttons
		maleButton.setUserData("Yes");
		femaleButton.setUserData("No");
		otherButton.setUserData("Not sure");
		
		//Adding buttons to the VBox
		gridForRadioButtons.getChildren().addAll(maleButton,femaleButton,otherButton);
		//Add VBox to your GridPane
		grid.add(gridForRadioButtons, 3, 5, 2, 1);
		
		Text empEligibiltySubtitle = new Text("Employment Eligibility");
		empEligibiltySubtitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		grid.add(empEligibiltySubtitle, 0, 6, 2, 1);
		
		Label dateLabel = new Label("Date Available:");
		grid.add(dateLabel, 0, 7);

		DatePicker date = new DatePicker();
		date.setPromptText("mm/dd/yyyy");
		grid.add(date, 1, 7);
		
		//Get the selected date
		LocalDate selectedDate = date.getValue();
		
		Label desiredPositionLabel = new Label("Desired Position:");
		grid.add(desiredPositionLabel, 2, 7);
		
		TextField desiredPositionTextField = new TextField();
		grid.add(desiredPositionTextField, 3, 7);
		
		Label desiredSalaryLabel = new Label("Desired Salary:");
		grid.add(desiredSalaryLabel, 0, 8);
		
		TextField desiredSalaryTextField = new TextField();
		GridPane.setHgrow(desiredSalaryTextField, Priority.ALWAYS);
		desiredSalaryTextField.setMaxWidth(Double.MAX_VALUE);
		grid.add(desiredSalaryTextField, 1, 8, 3,1);
		
		Label legalAuthLabel = new Label("Are you legally autherized to work in the country?");
		grid.add(legalAuthLabel, 0, 9, 2 ,1);
		
		//Radio buttons
		HBox legalAuthRadioButtons = new HBox(10);
		RadioButton yesLegalAuthButton = new RadioButton("Yes");
		RadioButton noLegalAuthButton = new RadioButton("No");
		
		//Toggle group
		ToggleGroup legalAuthGroup = new ToggleGroup();
		yesLegalAuthButton.setToggleGroup(legalAuthGroup);
		noLegalAuthButton.setToggleGroup(legalAuthGroup);
	
		// Assigning values to Radio buttons
		yesLegalAuthButton.setUserData("Yes");
		noLegalAuthButton.setUserData("No");
		
		//Adding buttons to the VBox
		legalAuthRadioButtons.getChildren().addAll(yesLegalAuthButton,noLegalAuthButton);
		//Add VBox to your GridPane
		grid.add(legalAuthRadioButtons, 0, 10, 2, 1);
				
		Label relWoringLabel = new Label("Are you legally autherized to work in the country?");
		grid.add(relWoringLabel, 0, 11, 2 ,1);
		
		//Radio buttons
		HBox relWoringRadioButtons = new HBox(10);
		RadioButton yesRelWoringButton = new RadioButton("Yes");
		RadioButton noRelWoringButton = new RadioButton("No");
		
		//Toggle group
		ToggleGroup relWoringGroup = new ToggleGroup();
		yesRelWoringButton.setToggleGroup(relWoringGroup);
		noRelWoringButton.setToggleGroup(relWoringGroup);
	
		// Assigning values to Radio buttons
		yesRelWoringButton.setUserData("Yes");
		noRelWoringButton.setUserData("No");
		
		//Adding buttons to the VBox
		relWoringRadioButtons.getChildren().addAll(yesRelWoringButton,noRelWoringButton);
		//Add VBox to your GridPane
		grid.add(relWoringRadioButtons, 0, 12, 2, 1);
		
		Label explainfurtherLabel = new Label("If yes, please explain further:");
		grid.add(explainfurtherLabel, 0, 13);
		
		TextField explainfurtherTextField = new TextField();
		GridPane.setHgrow(explainfurtherTextField, Priority.ALWAYS);
		explainfurtherTextField.setMaxWidth(Double.MAX_VALUE);
		grid.add(explainfurtherTextField, 1, 13, 3,1);					

		Button saveButton = new Button("Save");
		HBox hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		HBox.setHgrow(saveButton, Priority.ALWAYS);
		saveButton.setMaxWidth(Double.MAX_VALUE);		
		
		hBox.getChildren().add(saveButton);
		grid.add(hBox, 0, 15, 2, 1);
		
		saveButton.setOnAction(actionEvent -> {
		    String fullName = fullnameTextField.getText().trim();
		    String currentAddress = currentAddressTextField.getText().trim();
		    String contactNumber = contactNumberTextField.getText().trim();
		    String email = emailTextField.getText().trim();
		    String highestEducation = educationlevelTextField.getText().trim();
		    String gender = group.getSelectedToggle() != null ? group.getSelectedToggle().getUserData().toString() : "";
		    LocalDate dateAvailable = date.getValue();
		    String desiredPos = desiredPositionTextField.getText().trim();
		    String desiredSalary = desiredSalaryTextField.getText().trim();
		    String legalWorkAuth = legalAuthGroup.getSelectedToggle() != null ? legalAuthGroup.getSelectedToggle().getUserData().toString() : "";
		    String relWorkingHere = relWoringGroup.getSelectedToggle() != null ? relWoringGroup.getSelectedToggle().getUserData().toString() : "";
		    String furtherExplanation = explainfurtherTextField.getText().trim();

		    // Create User object
		    User user = new User();
		    user.setFullName(fullName);
		    user.setCurrentAddress(currentAddress);
		    user.setContactNumber(contactNumber);
		    user.setEmailAddress(email);
		    user.setHighestEducation(highestEducation);
		    user.setGender(gender);
		    user.setDateAvailable(dateAvailable != null ? dateAvailable.toString() : "");
		    user.setDesiredPos(desiredPos);
		    user.setDesiredSalary(desiredSalary);
		    user.setLegalWorkAuth(legalWorkAuth);
		    user.setRelWorkingHere(relWorkingHere);
		    user.setFurtherExplanation(furtherExplanation);

		    // Save user
		    try {
				userDao.saveUser(user);
				this.alert("Save", "Successful!", AlertType.INFORMATION);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.alert("Error", "Failed!", AlertType.ERROR);
			}

		    // Optional: show confirmation
		    System.out.println("User saved successfully!");
		});
		
		
		Scene scene = new Scene(grid, 750, 600);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

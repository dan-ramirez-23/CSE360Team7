package termproj;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TermProj extends Application {

	private final int WIDTH = 550;
	private final int HEIGHT = 400;
	private static Stage stage;
	private ArrayList<User> userList = new ArrayList<>();// created to make sure
															// there are no null
															// pointer
															// exceptions
	private ArrayList<Doctor> doctorList = new ArrayList<>();
	private UserManager um = new UserManager(userList);

	public void start(Stage primaryStage) throws Exception {
		//hardcode();
		stage = primaryStage;
		/*
		 * FXMLLoader loader = new FXMLLoader();
		 * loader.setLocation(getClass().getResource("LoginPane.fxml"));
		 * LoginPage startPage = new LoginPage();
		 * loader.setController(startPage); Parent root = loader.load();
		 */
		Parent root = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
		primaryStage.setTitle("Pediatric Office System");
		primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
		primaryStage.show(); // Display the stage
	}

	public void changeScene(String fxml, String username) throws IOException {
		// hardcode();
		um.readAllUsers();// idk why we need to read them twice, but we do
		FXMLLoader loader = new FXMLLoader();
		Pages pageController = null;
		if (fxml.equals("NursePane.fxml")) {
			pageController = new NursePage(username, um.getUserList(), um);// needs
																			// to
																			// change
																			// depending
																			// on
																			// the
																			// page
																			// required
		} else if (fxml.equals("DoctorPane.fxml")) {
			pageController = new DoctorPage(username, um.getUserList(), um);
		} else if (fxml.equals("PatientPane.fxml")) {
			pageController = new PatientPage(username, um.getUserList(), um);
		}

		loader.setLocation(getClass().getResource(fxml));
		loader.setController(pageController);
		Parent pane = loader.load();

		// Parent pane = loader.load(getClass().getResource(fxml));
		stage.setScene(new Scene(pane, WIDTH + 300, HEIGHT + 150));
		// stage.getScene().setRoot(pane);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public void hardcode() {

		ArrayList<Patient> allPats = new ArrayList<Patient>();
		ArrayList<Patient> docPats = new ArrayList<Patient>();

		String[] im = { "Chicken Pox", "COVID-19" };
		String[] per = { "Marijuana" };
		String[] med = { "N/A" };
		String[] hi = { "Just a boss ass bitch" };
		String[] ef = { "N/A" };
		String[] alrg = { "Cats", "Broke Bitches", "Anything that isnt money" };
		String[] hc = { "He is to real" };
		int numOfDoctors = doctorList.size();

		Patient pat1 = new Patient("Another", "Patient", 002, 102, "06/21/99",
				"9726584598", "booty@gmail.com", "Walgreens", "anotherPat",
				"password", "Insure", im, per, med, hi, ef, "blaze it", 69, 192,
				420, 200, alrg, hc);

		Patient pat2 = new Patient("Sebastian", "Diaz", 001, 293, "03/16/00",
				"6023915618", "ass@gmail.com", "CVS", "sdiazagu", "password",
				"insurance company", im, per, med, hi, ef, "Stay up cuzzo",
				289.2, 6.7, 98.2, 170.3, alrg, hc);

		um.addUserToList(pat2);
		um.addUserToList(pat1);

		Doctor doc1 = new Doctor("Hannah", "Kaufman", "hjkaufma", "password",
				102);
		Doctor doc2 = new Doctor("Audrey", "Wong", "awong24", "password", 293);
		Nurse nur1 = new Nurse("Jackson", "Carrion", "jtcarrio", "password",
				900);
		Nurse nur2 = new Nurse("Dan", "Ramirez", "darami14", "password", 032);

		doc1.addPatient(pat2);
		doc2.addPatient(pat1);

		for (int i = 0; i < um.getUserList().size(); i++) {// add all patients
															// to all nurses
			if (um.getUserList().get(i).getUserType().equals("Patient")) {
				nur1.addPatient((Patient) um.getUserList().get(i));
				nur2.addPatient((Patient) um.getUserList().get(i));
			}
		}

		um.addUserToList(doc1);
		um.addUserToList(nur1);
		um.addUserToList(doc2);
		um.addUserToList(nur2);

		PatientMessage msg1 = new PatientMessage("test message body",
				"test message subject", "jtcarrio");
		PatientMessage msg2 = new PatientMessage("test message 2 body",
				"test message 2 subject", "hjkaufma");
		PatientMessage msg3 = new PatientMessage("test message 3 body",
				"test message 3 subject", "awong24");
		nur2.addMessage(msg1);
		nur2.addMessage(msg2);
		nur2.addMessage(msg3);

		um.writeAllUsers();// testing with writing to file
	}

}

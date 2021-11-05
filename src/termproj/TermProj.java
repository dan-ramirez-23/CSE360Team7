package termproj;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
	
public class TermProj extends Application {

	   private final int WIDTH = 550;
	   private final int HEIGHT = 400;
	   private static Stage stage;

	   public void start(Stage primaryStage) throws Exception
	   {
	      stage = primaryStage;
		  /*FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(getClass().getResource("LoginPane.fxml"));
		  LoginPage startPage = new LoginPage();
		  loader.setController(startPage);
		  Parent root = loader.load();*/
		  Parent root = FXMLLoader.load(getClass().getResource("LoginPane.fxml"));
		  primaryStage.setTitle("Pediatric Office System");
		  primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
	      primaryStage.show(); // Display the stage
	   }

	   
	   public void changeScene(String fxml, String username) throws IOException {
		   FXMLLoader loader = new FXMLLoader();
		   Pages pageController = null;
		   if(fxml.equals("NursePane.fxml")) {
			   pageController = new NursePage(username);//needs to change depending on the page required
		   }
		   else if (fxml.equals("DoctorPane.fxml")) {
			   pageController = new DoctorPage(username);
		   }
		   else if (fxml.equals("PatientPane.fxml")) { 
			   pageController = new PatientPage(username);
		   }
		   
		   
		   loader.setLocation(getClass().getResource(fxml));
		   loader.setController(pageController);
		   Parent pane = loader.load();
		   
		   //Parent pane = loader.load(getClass().getResource(fxml));
		   stage.setScene(new Scene(pane, WIDTH + 300, HEIGHT + 150));
		   //stage.getScene().setRoot(pane);
	   }
	   
	   public static void main(String[] args)
	   {
	      Application.launch(args);
	   }
	

}

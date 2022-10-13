package application.main;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			writeToLog("Welcome !!!");
			Parent root = FXMLLoader.load(getClass().getResource("/application/frame/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/utils/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Student Form");
			primaryStage.setMaximized(true);
			primaryStage.show();
		} catch (Exception e) {
			writeToLog("In Main Method", e);
		}
	}

	private static Logger logger = Logger.getLogger("MyLog");
	static {
		FileHandler fh = null;
		try {
			fh = new FileHandler(System.getProperty("user.dir") + System.getProperty("file.separator") + "LogFile.log",
					true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
			logger.info("Logging Inilialized!!!");
		} catch (SecurityException | IOException e) {
			logger.log(Level.WARNING, " Logging Exception ", e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void writeToLog(String message) {
		logger.info(message);
	}

	public static void writeToLog(String message, Exception exception) {
		logger.log(Level.WARNING, message, exception);
	}
}

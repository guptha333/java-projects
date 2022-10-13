package application.helper;

import javafx.scene.control.Alert;

public class Helper {
	private Helper() {
	}

	public static void showAlert(Alert.AlertType alertType, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
	}
}

package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane rootPane = new BorderPane();
        GridPane gp = new GridPane();
        rootPane.setCenter(gp);
        gp.setVgap(5);
        gp.setHgap(5);
        rootPane.prefWidth(700);
        rootPane.prefHeight(400);
        gp.prefWidth(400);
        gp.prefHeight(400);
        Label title = new Label("Conversions Available:");
        RadioButton cel1 = new RadioButton("Celsius to Fahrenheit");
        RadioButton cel2 = new RadioButton("Celsius to Kelvin");
        RadioButton fah1 = new RadioButton("Fahrenheit to Celsius");
        RadioButton fah2 = new RadioButton("Fahrenheit to Kelvin");
        RadioButton kel1 = new RadioButton("Kelvin to Celsius");
        RadioButton kel2 = new RadioButton("Kelvin to Fahrenheit");
        RadioButton load = new RadioButton("Last Calculation");
        Label inputSize = new Label("Temperature Value:");
        TextField inputText = new TextField();

        inputText.setOnAction((ActionEvent inputText1) -> {
            String inputText2 = inputText.getText();
            double inputText3 = Double.parseDouble(inputText2);
            System.out.println(inputText3);
        });

        Button convert = new Button("Convert");
        ToggleGroup tg = new ToggleGroup();

        cel1.setToggleGroup(tg);
        cel1.setSelected(true);
        cel2.setToggleGroup(tg);
        fah1.setToggleGroup(tg);
        fah2.setToggleGroup(tg);
        kel1.setToggleGroup(tg);
        kel2.setToggleGroup(tg);
        load.setToggleGroup(tg);

        gp.add(inputSize, 0, 0);
        gp.add(inputText, 1, 0);
        gp.add(title, 0, 1);
        gp.add(cel1, 0, 2);
        gp.add(cel2, 0, 3);
        gp.add(fah1, 0, 4);
        gp.add(fah2, 0, 5);
        gp.add(kel1, 0, 6);
        gp.add(kel2, 0, 7);
        gp.add(load, 0, 8);
        gp.add(convert, 0, 9);

        convert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent convert1) {
                try {
                	String hist="";
                	int flag=0;
                	
                	if (cel1.isSelected()) {
                		double value = Integer.parseInt(inputText.getText());
                		double fah=ctof(value);
                		hist=value + " C = " + fah + " F";
                	}
                
                	else if (cel2.isSelected()) {
                		double value = Integer.parseInt(inputText.getText());
                		double kel=ctok(value);
                		hist=value + " C = " + kel + " K";
                	}
                
                	else if (fah1.isSelected()) {
                		double value = Integer.parseInt(inputText.getText());
                		double cel=ftoc(value);
                		hist=value + " F = " + cel + " C";
                	}
                
                	else if (fah2.isSelected()) {
                		double value = Integer.parseInt(inputText.getText());
                		double kel=ftok(value);
                		hist=value + " F = " + kel + " K";
                	}
                
                	else if (kel1.isSelected()) {
                		double value = Integer.parseInt(inputText.getText());
                		double cel=ktoc(value);
                		hist=value + " K = " + cel + " C";
                	}
                
                	else if (kel2.isSelected()) {
                		double value = Integer.parseInt(inputText.getText());
                		double fah=ktof(value);
                		hist=value + " K = " + fah + " F";
                	}
                	
                	else if (load.isSelected()) {
                		History("load");
                		flag=1;
                	}
                	
                	if (flag==0) {
                		Alert alert = new Alert(Alert.AlertType.INFORMATION);
                		alert.setTitle("Conversion");
                		alert.setHeaderText("Conversion completed!");
                		alert.setContentText(hist);
                		alert.showAndWait();
                	}
                		
                	if (!load.isSelected()) {
                		History(hist);
                	}
                
                }
                
                catch(Exception e) {
                	Alert alert_error = new Alert(Alert.AlertType.INFORMATION);
                    alert_error.setTitle("Error!");
                    alert_error.setContentText("Invalid input. Please give a numeric value of at most nine digits.");
                    alert_error.showAndWait();
                }
            }
        });
        Scene scene = new Scene(rootPane, 500, 350);
        primaryStage.setTitle("Temperature Converter Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
//insertion sort

    public static double ctof(double cel) {
    	return (cel*9/5)+32;
    }

    public static double ctok(double cel) {
    	return cel+273;
    }
    
    public static double ftoc(double fah) {
    	return (fah-32)*5/9;
    }

    public static double ftok(double fah) {
    	return ftoc(fah)+273;
    }
    
    public static double ktoc(double kel) {
    	return kel-273;
    }

    public static double ktof(double kel) {
    	return ktoc(kel)*9/5+32;
    }
    
    public static void History(String line) {
		  try {
			  String path="C:\\Users\\prana\\eclipse-workspace\\trial\\src\\com\\javaintern\\me\\calchistory";
			  File f=new File(path);
			  Scanner s=new Scanner(f);
			
			  if(line=="load") {
				  Alert alert = new Alert(Alert.AlertType.INFORMATION);
				  alert.setTitle("History");
				
				  if(s.hasNextLine()) {
	            	alert.setHeaderText("Your last calculation was:");
	            	String data = s.nextLine();
	            	alert.setContentText(data);
	            	alert.showAndWait();
				  }
				
				  else {
					  alert.setHeaderText("No calculation history is available.");
	            	alert.showAndWait();
				  }
				
			  }
			
			  else {
				  PrintStream out = new PrintStream(new FileOutputStream(path));
				  out.print(line);
				  out.close();
			  }
			
			  s.close();
		}
		catch(FileNotFoundException fnf) {
			  Alert alert_error2 = new Alert(Alert.AlertType.INFORMATION);
			  alert_error2.setTitle("Error");
			  alert_error2.setHeaderText("Error");
        alert_error2.setContentText("The file that records the tool history is missing! Please create it.");
        alert_error2.showAndWait();
		  }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

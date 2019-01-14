package move.circle;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class MoveCircle extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        int spaceWidth = 300;
        int spaceHeight = 300;
        Group circles = new Group();
        
        Button clear = new Button("Clear");
        Button resetColor = new Button("Reset Background");
        
        Pane field = new Pane(circles);
        ToolBar tools = new ToolBar(resetColor, clear);
        VBox space = new VBox(field, tools);
        Scene scene = new Scene(space, Color.CORAL);
        field.setMinSize(spaceWidth, spaceHeight);
        field.setStyle("-fx-background-color: transparent;");
        space.setStyle("-fx-background-color: transparent;");
                
        circles.getChildren().add(makeCircle(spaceWidth/2, spaceHeight/2, circles));
        field.addEventFilter(MouseEvent.MOUSE_CLICKED, mouse -> {
            if (!circles.contains(mouse.getX(), mouse.getY())) {
                if (mouse.getButton() == MouseButton.SECONDARY) 
                    scene.setFill(randomColor());
                else if (mouse.getButton() == MouseButton.PRIMARY)
                    circles.getChildren().add(makeCircle(mouse.getX(), mouse.getY(), circles));
            }
        });
        
        resetColor.setOnAction(e -> scene.setFill(Color.CORAL));
        clear.setOnAction(e -> circles.getChildren().clear());
        
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Circle fun!");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * @return A randomly generated Color object
     */
    public static Color randomColor(){
        return Color.color(Math.random(), Math.random(), Math.random());
    }
    
    /**
     * 
     * @param x the X coordinate on the pane to place the circle
     * @param y the Y coordinate on the pane to place the circle
     * @param group the group the circle will belong to
     * @return the newly created circle object
     */
    public static Circle makeCircle(double x, double y, Group group){
        Circle circle = new Circle(x, y, 20, randomColor());
        circle.setOnMouseDragged(mouse -> {
            if (mouse.getButton() == MouseButton.PRIMARY) {
                circle.setCenterX(mouse.getX());
                circle.setCenterY(mouse.getY());
            }
            circle.toFront();
            group.toBack();
        });
        circle.setOnMouseClicked(mouse -> {
            if (mouse.getButton() == MouseButton.SECONDARY)
                circle.setFill(randomColor());
            else if (mouse.getButton() == MouseButton.MIDDLE)
                group.getChildren().remove(circle);
            circle.toFront();
            group.toBack();
        });
        circle.addEventFilter(ScrollEvent.ANY, scroll -> {
            circle.setRadius(circle.getRadius() + scroll.getDeltaY() / 5);
            if (circle.getRadius() < 10) {
                circle.setRadius(10);
            }
            circle.toFront();
            group.toBack();
        });
        return circle;
    }
}
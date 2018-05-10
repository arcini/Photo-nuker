import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File; //describes locations in your file system
import java.io.FileReader; // this connects to a file
import java.io.FileWriter; //this is unbuffered IO (only one char at a time, expensive system calls and also slow) - going to the store for one egg at a time
import java.io.BufferedReader; //this does buffered IO. (draws stuff out of a sector of hard memory that has been moved to RAM) - buying a carton of eggs at the store and getting ur eggs from the fridge
import java.io.BufferedWriter; //
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.image.Image;
public class PhotoNuker extends Application
{
    private MenuBar mbar;
    public PhotoNuker()
    {
        mbar = new MenuBar();
    }
    @Override
    public void init()
    {
    }

    @Override
    public void start(Stage primary)
    {

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                gc1.fillOval(e.getX(),e.getY(),20,20);
            }
        });


        BorderPane bp = new BorderPane();
        bp.setTop(mbar);
        makeMenus();
        Scene s = new Scene(bp, 500, 500);
        primary.setScene(s);
        primary.setTitle("PhotoNuker Java Final Project");
        primary.show();
    }
    private void makeMenus() {
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open...");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As...");


        Menu stickerMenu = new Menu("Stickers");

        Image openIcon = new Image(getClass().getResourceAsStream("B.png"));
        ImageView openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  bItem = new MenuItem("");
        bItem.setGraphic(openView);
        bItem.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));

        openIcon = new Image(getClass().getResourceAsStream("100.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  hundredItem = new MenuItem("");
        hundredItem.setGraphic(openView);
        hundredItem.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));

        openIcon = new Image(getClass().getResourceAsStream("fire.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  fItem = new MenuItem("");
        fItem.setGraphic(openView);
        fItem.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));

        openIcon = new Image(getClass().getResourceAsStream("laughingemoji.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  lItem = new MenuItem("");
        lItem.setGraphic(openView);
        lItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));





        /*newItem.setOnAction( e -> {
            if(!windowContentsSaved) {
                rescueWindow();
            }
            rescueWindow();
        });

        saveItem.setOnAction( e -> {
            if(selectedFile == null) {
                FileChooser fc = new FileChooser();
                fc.setTitle("Save as...");
                selectedFile = fc.showSaveDialog(primary);

            } try {
                pukeFile();
                primary.setTitle("Nitpad: " + selectedFile.getAbsolutePath());
                windowContentsSaved = true;
            } catch(IOException ex) {
                System.err.printf("IOException occured\n");
            }

        });
        saveAsItem.setOnAction( e -> {
            FileChooser fc = new FileChooser();
            fc.setTitle("Save as...");
            selectedFile = fc.showSaveDialog(primary);
            if (selectedFile != null) {
                try {
                    primary.setTitle("Nitpad: " + selectedFile.getAbsolutePath());
                    pukeFile();
                    windowContentsSaved = true;
                } catch(IOException ex) {
                    System.err.printf("IOException occured\n");
                }
            }
        });
        openItem.setOnAction( e -> {
            //rescues the window
            //File chooser window ppops up
            //user chooses file
            //user hits cancel, do nothing
            //if user opens file, put in textarea and display path to file in title bar
            if(!windowContentsSaved) {
                rescueWindow();
            }
            FileChooser fc = new FileChooser();
            fc.setTitle("Open File");
            selectedFile = fc.showOpenDialog(primary);
            if(selectedFile != null) {
                primary.setTitle("Nitpad: " + selectedFile.getAbsolutePath());
                try {
                    String s = hooverFile();
                    ta.setText(s);
                    windowContentsSaved = true;
                } catch (FileNotFoundException ex) {
                    System.err.printf("File %s cannot be opened\n", selectedFile.getName());
                } catch(IOException ex) {
                    System.err.printf("IOException occured\n");
                }
            }
        });*/

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction( e -> Platform.exit());
        mbar.getMenus().addAll(fileMenu, stickerMenu);
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, quitItem);
        stickerMenu.getItems().addAll(bItem, fItem, hundredItem, lItem);
    }
    @Override
    public void stop()
    {
    }
}

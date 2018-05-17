import javafx.application.Application;
import javafx.scene.Group;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File; //describes locations in your file system
import java.io.FileReader; // this connects to a file
import java.io.FileWriter; //this is unbuffered IO (only one char at a time, expensive system calls and also slow) - going to the store for one egg at a time
import java.io.BufferedReader; //this does buffered IO. (draws stuff out of a sector of hard memory that has been moved to RAM) - buying a carton of eggs at the store and getting ur eggs from the fridge
import java.io.BufferedWriter; //
import java.io.FileNotFoundException;
import javafx.stage.FileChooser;
import java.io.IOException;
import javafx.scene.control.TextArea;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import java.util.Optional;
import java.util.OptionalInt;
import java.io.Console;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.effect.ColorAdjust;
import java.net.MalformedURLException;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;


public class PhotoNuker extends Application
{
    private boolean windowContentsSaved;
    private Optional<Image> currentSticker;
    private OptionalInt currentSize;
    private MenuBar mbar;
    private Canvas c;
    private GraphicsContext pen;
    private Stage primary;
    private File selectedFile;
    public PhotoNuker()
    {
      mbar = new MenuBar();
      c = new Canvas(1000,1000);
      pen = c.getGraphicsContext2D();
      currentSize = OptionalInt.of(25);
      selectedFile = null;
      windowContentsSaved = true;
    }


    @Override
    public void init()
    {
    }

    @Override
    public void start(Stage primary)
    {
        BorderPane bp = new BorderPane();
        StackPane centerPane = new StackPane();
        bp.setTop(mbar);
        bp.setCenter(centerPane);
        c.setWidth(centerPane.getWidth());
        c.setHeight(centerPane.getHeight());
        centerPane.getChildren().add(c);
        makeMenus();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        primary.setX(0);
        primary.setY(0);
        primary.setWidth(bounds.getWidth());
        primary.setHeight(bounds.getHeight());

        Scene s = new Scene(bp);
        this.primary = primary;
        primary.setScene(s);
        primary.setTitle("PhotoNuker Java Final Project");

        primary.show();


        c.setOnMouseClicked( e -> {
            if (windowContentsSaved) {
                windowContentsSaved = false;
            }

            if(currentSticker!=null && currentSticker.isPresent()) {
              System.out.println("pen="+pen);
                pen.drawImage(currentSticker.get(),0,0,currentSticker.get().getWidth(),currentSticker.get().getWidth(),e.getX()-currentSize.getAsInt()/2, e.getY()-currentSize.getAsInt()/2,currentSize.getAsInt(),currentSize.getAsInt());
            }
        });
    }

    private void rescueWindow() {

    }
    private void nuke(){
        //commenting to test other code


      /*Image image = new Image(c);
      ImageView imageview = new ImageView(image);
      ColorAdjust colorAdjust = new ColorAdjust();
      colorAdjust.setConrast(.4);
      colorAdjust.setHue(-.5);
      colorAdjust.setBrightness(.9);
      colorAdjust.setSaturation(.5);
      imageView.setEffect(colorAdjust);
      Group root = new Group(imageview);
      Scene scene = new Scene(root,c.setWidth,c.setHeight);
      stage.setScene(scene);
      stage.show();*/
    }
    private void makeMenus() {


        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open...");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem saveAsItem = new MenuItem("Save As...");
        MenuItem filterItem = new MenuItem("Filters");
        newItem.setOnAction( e ->
        {

        });

        openItem.setOnAction( e ->
        {
            //File chooser window pops up
            FileChooser fc = new FileChooser();
            fc.setTitle("Open File");
            FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
            fc.getExtensionFilters().add(ext);

            //User choose file.
            selectedFile = fc.showOpenDialog(primary);
            if(selectedFile != null)
            {
                primary.setTitle("FileShower: " + selectedFile.getAbsolutePath());
                try {
                    System.out.println(selectedFile.toURI().toURL().toString());
                    Optional<Image> backgroundImg = Optional.of(new Image(selectedFile.toURI().toURL().toString()));
                    c.setWidth(backgroundImg.get().getWidth());
                    c.setHeight(backgroundImg.get().getHeight());
                    pen.clearRect(0, 0, c.getWidth(), c.getHeight());
                    pen.drawImage(backgroundImg.get(), 0, 0, backgroundImg.get().getWidth(), backgroundImg.get().getHeight());
                } catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    System.out.println("malformed url");
                }
            }
        });
        filterItem.setOnAction( e ->
        {

        });





        Menu stickerMenu = new Menu("Stickers");


        Image openIcon = new Image(getClass().getResourceAsStream("B.png"));
        ImageView openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  bItem = new MenuItem("");
        bItem.setGraphic(openView);
        bItem.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));

        bItem.setOnAction( e -> {
          currentSticker = Optional.of(new Image(getClass().getResourceAsStream("B.png")));
        });

        openIcon = new Image(getClass().getResourceAsStream("100.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  hundredItem = new MenuItem("");
        hundredItem.setGraphic(openView);
        hundredItem.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.CONTROL_DOWN));

        hundredItem.setOnAction( e -> {
          currentSticker = Optional.of(new Image(getClass().getResourceAsStream("100.png")));
        });

        openIcon = new Image(getClass().getResourceAsStream("fire.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  fItem = new MenuItem("");
        fItem.setGraphic(openView);
        fItem.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));


        fItem.setOnAction( e -> {
          currentSticker = Optional.of(new Image(getClass().getResourceAsStream("fire.png")));
        });


        openIcon = new Image(getClass().getResourceAsStream("laughingemoji.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  lItem = new MenuItem("");
        lItem.setGraphic(openView);
        lItem.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));


        lItem.setOnAction( e -> {
          currentSticker = Optional.of(new Image(getClass().getResourceAsStream("laughingemoji.png")));
        });

        openIcon = new Image(getClass().getResourceAsStream("ok.png"));
        openView = new ImageView(openIcon);
        openView.setFitWidth(15);
        openView.setFitHeight(15);
        MenuItem  OItem = new MenuItem("");
        OItem.setGraphic(openView);
        OItem.setAccelerator(new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_DOWN));

        OItem.setOnAction( e -> {
          currentSticker = Optional.of(new Image(getClass().getResourceAsStream("ok.png")));
        });




        Menu sizeMenu = new Menu("Size");
                MenuItem smallSize = new MenuItem("15 px");
                smallSize.setOnAction(e -> {
                  currentSize = OptionalInt.of(15);
                });

                MenuItem mediumSize = new MenuItem("25 px");
                mediumSize.setOnAction(e -> {
                  currentSize = OptionalInt.of(25);
                });

                MenuItem largeSize = new MenuItem("50 px");
                largeSize.setOnAction(e -> {
                  currentSize = OptionalInt.of(50);
                });

                MenuItem extraLargeSize = new MenuItem("100 px");
                extraLargeSize.setOnAction(e -> {
                  currentSize = OptionalInt.of(100);
                });





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
                mbar.getMenus().addAll(fileMenu, stickerMenu, sizeMenu);
                sizeMenu.getItems().addAll(smallSize, mediumSize, largeSize, extraLargeSize);
                fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, quitItem);
                stickerMenu.getItems().addAll(bItem, fItem, hundredItem, lItem, OItem);
    }

    @Override
    public void stop()
    {
    }
}

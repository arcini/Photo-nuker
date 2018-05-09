import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class PhotoNuker extends Application
{
    public PhotoNuker()
    {
    }
    @Override
    public void init()
    {
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

        newItem.setOnAction( e -> {
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
        });

        MenuItem quitItem = new MenuItem("Quit");
        quitItem.setOnAction( e -> Platform.exit());
        mbar.getMenus().addAll(fileMenu, stickerMenu);
        fileMenu.getItems().addAll(newItem, openItem, saveItem, saveAsItem, quitItem);
        stickerMenu.getItems().addAll(bItem);
    }
    @Override
    public void start(Stage primary)
    {
        primary.setTitle("PhotoNuker");
        primary.show();
    }
    @Override
    public void stop()
    {
    }
}

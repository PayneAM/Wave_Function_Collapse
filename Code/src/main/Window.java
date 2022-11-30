import Model.SimpleTileModel;
import TileData.TileDictionary;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Window implements ActionListener {
    private int imgSize = 32;
    private int width = 20;
    private int height = 20;
    private boolean paint = false;
    private String FileName = "";

    private String[] options = new String[]{
            "Octal_2-Edge",
            "Straight_2-Edge",
            "Angled_2-Corner",
            "Angled_Alt_2-Corner",
            "Test_Tile_2-Edge",
            "Test_Tile_2-Corner",
            "Test_Tile_3-Edge",
    };

    private final TileDictionary tileDictionary = new TileDictionary();

    private final JFrame primaryWindow = new JFrame();
    private final JFrame pictureWindow = new JFrame();
    private JPanel primaryPanel = new JPanel();
    private JPanel picturePanel = new JPanel();
    private JLabel label1 = new JLabel("IMG SIZE");
    private JLabel label2 = new JLabel("WIDTH");
    private JLabel label3 = new JLabel("HEIGHT");
    private JSlider widthSlider = new JSlider(JSlider.HORIZONTAL);
    private JSlider heightSlider = new JSlider(JSlider.HORIZONTAL);
    private JSlider imgSlider = new JSlider(JSlider.HORIZONTAL);
    private JComboBox<String> dropDownTile = new JComboBox(options);
    private JButton generate = new JButton();

    public Window() throws IOException {
        InitPrimary();
        InitPrimaryWindowObj();
    }

    private void InitPrimary() {
        primaryWindow.setTitle("WaveFunctionCollapse");
        primaryWindow.setSize(300, 400);
        primaryWindow.setResizable(false);
        primaryWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        primaryPanel.setLayout(new BoxLayout(primaryPanel, BoxLayout.Y_AXIS));
        primaryPanel.add(Box.createRigidArea(new Dimension(1, 3)));
        primaryWindow.add(primaryPanel);
    }
    private void InitPrimaryWindowObj()
    {
        primaryPanel.add(label1);
        imgSlider.setMinimum(8);
        imgSlider.setMaximum(64);
        imgSlider.setMinorTickSpacing(2);
        imgSlider.setMajorTickSpacing(14);
        imgSlider.setPaintLabels(true);
        imgSlider.setPaintTicks(true);
        imgSlider.setSnapToTicks(true);
        primaryPanel.add(imgSlider);

        primaryPanel.add(label2);
        widthSlider.setMinimum(2);
        widthSlider.setMaximum(128);
        widthSlider.setMinorTickSpacing(2);
        widthSlider.setMajorTickSpacing(32);
        widthSlider.setPaintLabels(true);
        widthSlider.setPaintTicks(true);
        widthSlider.setSnapToTicks(true);
        primaryPanel.add(widthSlider);

        primaryPanel.add(label3);
        heightSlider.setMinimum(2);
        heightSlider.setMaximum(128);
        heightSlider.setMinorTickSpacing(2);
        heightSlider.setMajorTickSpacing(32);
        heightSlider.setPaintLabels(true);
        heightSlider.setPaintTicks(true);
        heightSlider.setSnapToTicks(true);
        primaryPanel.add(heightSlider);

        dropDownTile.setMaximumSize(dropDownTile.getPreferredSize());
        dropDownTile.setAlignmentX(Component.CENTER_ALIGNMENT);
        primaryPanel.add(dropDownTile);

        generate.setText("Generate");
        generate.addActionListener(this);
        generate.setAlignmentX(Component.CENTER_ALIGNMENT);
        primaryPanel.add(generate);

        primaryWindow.setVisible(true);
    }

    private void InitPictureWindow()
    {
        pictureWindow.setTitle("WaveFunctionCollapse");
        pictureWindow.setLocation(500, 0);
        pictureWindow.setSize(imgSize * width + imgSize, imgSize * height + imgSize);
        pictureWindow.setResizable(false);

        picturePanel.setLayout(null);
        pictureWindow.add(picturePanel);
    }
    private void GenerateCollapsedGrid() throws IOException {
        InitPictureWindow();
        InitPictureWindowObj();
    }

    public void InitPictureWindowObj() throws IOException {
        String fileName = FileName;
        SimpleTileModel simpleTileModel = new SimpleTileModel(width, height, tileDictionary.WangTileDir.get(fileName));
        int[][] tiles = simpleTileModel.Run();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Image image = ImageIO.read(tileDictionary.WangTileDir.get(fileName)[tiles[x][y]].imgFile);
                picturePanel.add(initJLabel(x * imgSize, y * imgSize, imgSize, new ImageIcon(image)));

            }
        }
        picturePanel.repaint();
        pictureWindow.setVisible(true);
    }

    private JLabel initJLabel(int x, int y , int imgSize, ImageIcon image) {
        ImageIcon icon = new ImageIcon(image.getImage().getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT));
        JLabel tile = new JLabel(icon, JLabel.CENTER);
        tile.setBounds(x, y, imgSize, imgSize);
        return tile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        picturePanel.removeAll();
        pictureWindow.dispose();
        width = widthSlider.getValue();
        height = heightSlider.getValue();
        imgSize = imgSlider.getValue();
        FileName = dropDownTile.getSelectedItem().toString();
        try {
            GenerateCollapsedGrid();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Window window = new Window();
    }
}

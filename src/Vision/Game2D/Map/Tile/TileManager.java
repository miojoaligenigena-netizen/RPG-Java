package Vision.Game2D.Map.Tile;

import Vision.Game2D.Main.GamePanel;
import Vision.Game2D.Main.Model;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;

public class TileManager implements Model{
    private enum tileType { 
        ERROR(0), GRASS(1), BRICK(4), WATER(5);

        private final int id;
        tileType(int id) { this.id = id; }
        public int getId() { return id; }
        
        public static tileType fromId(int id) {
            for (tileType type : values()) {
                if (type.getId() == id) return type;
            }
            return ERROR;
        }
    }
    private final GamePanel gp;
    private final Map<tileType, Tile> tile = new EnumMap<>(tileType.class);
    private int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
        getTileImage();
        mapReader("/Model/Maps/map.txt");
    }

    public void getTileImage() {
        
        tile.put(tileType.ERROR, new Tile());
        tile.get(tileType.ERROR).setModel(setupImage("/Model/Standard_templates/error.png", gp));
        
        tile.put(tileType.GRASS, new Tile());
        tile.get(tileType.GRASS).setModel(setupImage("/Model/Tiles/grass.png", gp));
        
        tile.put(tileType.BRICK, new Tile());
        tile.get(tileType.BRICK).setModel(setupImage("/Model/Tiles/brick.png", gp));
        
        tile.put(tileType.WATER, new Tile());
        tile.get(tileType.WATER).setModel(setupImage("/Model/Tiles/water.png", gp));
    }

    public void draw(Graphics2D g) {
        for (int col = 0; col < gp.getMaxScreenCol(); col++) {
            for (int row = 0; row < gp.getMaxScreenRow(); row++) {
                int tileId = mapTileNum[col][row];
                tileType type = tileType.fromId(tileId);
                addTile(type, col, row, g);
            }
        }
    }
    
    public void addTile(tileType tileType, int x, int y, Graphics2D g) {
        g.drawImage(tile.get(tileType).getModel(), x * gp.getTileSize(), y * gp.getTileSize(), gp.getTileSize(), gp.getTileSize(), null);
    }
    
    private void mapReader(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            if (is == null) {
                System.err.println("Map file not found: " + mapPath);
                return;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            while (row < gp.getMaxScreenRow()) {
                String lineStr = br.readLine();
                if (lineStr == null) break; 
                String[] numbers = lineStr.split(" ");

                for (int col = 0; col < gp.getMaxScreenCol(); col++) {
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    }
                }
                row++;
            }
            br.close();

        } catch (Exception e) {
            System.err.println("Map not found: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

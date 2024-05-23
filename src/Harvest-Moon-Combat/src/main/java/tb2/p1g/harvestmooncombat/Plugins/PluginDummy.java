package tb2.p1g.harvestmooncombat.Plugins;

import java.io.File;

public class PluginDummy implements MuatInterface, SimpanInterface {

    @Override
    public boolean tryReadFile(String path) {
        // Implementation of file existence check
        File file = new File(path);
        return file.exists();
    }

    @Override
    public void loadGameState(String path) {
        // Implementation of loading game state
        System.out.println("Loading game state from " + path);
    }

    @Override
    public void loadPlayer(String path, int player) {
        // Implementation of loading player data
        System.out.println("Loading player " + player + " from " + path);
    }

    @Override
    public void saveData(Object data, String filePath) {
        // Implementation of saving data
        System.out.println("Saving data to " + filePath);
    }
}

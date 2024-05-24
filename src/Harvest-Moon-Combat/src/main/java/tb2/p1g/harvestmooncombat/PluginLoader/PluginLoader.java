package tb2.p1g.harvestmooncombat.PluginLoader;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import tb2.p1g.harvestmooncombat.Models.*;

public class PluginLoader {
    private static List<Class<? extends MuatInterface>> muatPlugins = new ArrayList<>();
    private static List<Class<? extends SimpanInterface>> simpanPlugins = new ArrayList<>();
    private static List<String> pluginNameList = new ArrayList<>();
    public static List<Object> loadedObjectList = new ArrayList<>();
    public static String filePath;

    public static void loadPlugins(String pluginDir) throws Exception {
        File dir = new File(pluginDir);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".jar"));
        if (files == null) return;
        for (File file : files) {
            loadPlugin(file);
        }
    }

    public static void loadPlugin(File file) throws Exception {
        URL jarUrl = file.toURI().toURL();
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl})) {
            // Ambil nama JAR tanpa ekstensi
            String jarName = file.getName().replaceAll("\\.jar$", "");

            // Ganti titik dengan garis miring sebagai pemisah paket
            String packageName = "org.example"; // Sesuaikan dengan struktur paket Anda
            String className = packageName + "." + jarName;
            Class<?> pluginClass = classLoader.loadClass(className);

            if (MuatInterface.class.isAssignableFrom(pluginClass)) {
                muatPlugins.add((Class<MuatInterface>) pluginClass);
            }
            if (SimpanInterface.class.isAssignableFrom(pluginClass)) {
                simpanPlugins.add((Class<SimpanInterface>) pluginClass);
            }
            pluginNameList.add(getNamaPlugin(muatPlugins.size() - 1,filePath));
            System.out.println("Plugin " + (getNamaPlugin(muatPlugins.size() - 1,filePath)) + " Berhasil masuk ke plugin data!" );
        }
    }

    public static List<Class<? extends MuatInterface>> getMuatPlugins() {
        return muatPlugins;
    }

    public static List<Class<? extends SimpanInterface>> getSimpanPlugins() {
        return simpanPlugins;
    }

    public static List<String> getPluginNameList() {
        return pluginNameList;
    }

    public static void displayPluginNames() {
        if (pluginNameList.isEmpty()) {
            System.out.println("Tak ada plugin!");
        } else {
            for (String s : pluginNameList) {
                System.out.println(s);
            }
        }
    }

    public static String getNamaPlugin(int idplugin, String directoryPath) {
        try {
            Class<? extends MuatInterface> pluginClass = muatPlugins.get(idplugin);
            Object pluginObject = pluginClass.getDeclaredConstructor(String.class).newInstance(directoryPath);

            Field field = pluginClass.getDeclaredField("namaPlugin");
            field.setAccessible(true);
            String namaPlugin = (String) field.get(pluginObject);

            return namaPlugin;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public static void saveWithPlugins(int idplugin, String filepath) throws Exception {
        SimpanInterface pluginObject = (SimpanInterface) simpanPlugins.get(idplugin).getDeclaredConstructor(String.class).newInstance(filepath);
        pluginObject.writeSavePlayer(GameManager.getInstance().getPlayerOne());
        pluginObject.writeSavePlayer(GameManager.getInstance().getPlayerTwo());
        StateSave stateSave = new StateSave(GameManager.getInstance().getTurnNumber(),Toko.produkToko);
        pluginObject.writeGameState(stateSave);

    }

    public static void loadObjectWithPlugins(int idplugin,String filepath) throws  Exception{
        MuatInterface pluginObject = (MuatInterface) muatPlugins.get(idplugin).getDeclaredConstructor(String.class).newInstance(filepath);
        Object loadedObject = null;
        if(filepath.contains("Player")){
            loadedObject = pluginObject.loadPlayerPlugin(filepath);

        }else{//SaveState
            loadedObject = pluginObject.loadStatePlugin(filepath);
        }
        loadedObjectList.add(loadedObject);

    }
    public static void loadObjects() {
        for (Object o : loadedObjectList) {
            if (o instanceof StateSave) {
                StateSave st = (StateSave) o;
                GameManager.getInstance().setTurnNumber(st.getTurn());
                Toko.produkToko = st.getToko();
            }
            if (o instanceof Player) {
                Player player = (Player) o;
                if (player.getNama().equals("Player1")) {
                    GameManager.getInstance().getPlayers().set(0, player);
                } else if (player.getNama().equals("Player2")) {
                    GameManager.getInstance().getPlayers().set(1, player);
                } else {
                    System.out.println("Nama player tidak sesuai");
                }
                // Additional processing for Player object
            }

        }
    }

}

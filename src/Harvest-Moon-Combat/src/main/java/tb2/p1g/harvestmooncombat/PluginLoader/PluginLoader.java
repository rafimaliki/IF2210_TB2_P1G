package tb2.p1g.harvestmooncombat.PluginLoader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import tb2.p1g.harvestmooncombat.Models.MuatInterface;
import tb2.p1g.harvestmooncombat.Models.SimpanInterface;

public class PluginLoader {
    private List<MuatInterface> muatPlugins = new ArrayList<>();
    private List<SimpanInterface> simpanPlugins = new ArrayList<>();

    public void loadPlugins(String pluginDir) throws Exception {
        File dir = new File(pluginDir);

        File[] files = dir.listFiles((d, name) -> name.endsWith(".jar"));
        if (files == null) return;

        for (File file : files) {
            loadPlugin(file);
        }
    }

    private void loadPlugin(File file) throws Exception {
        URL jarUrl = file.toURI().toURL();
        try (URLClassLoader classLoader = new URLClassLoader(new URL[]{jarUrl}, MuatInterface.class.getClassLoader())) {
            // load plugin.properties di .jar
            try (InputStream input = classLoader.getResourceAsStream("plugin.properties")) {
                if (input == null) {
                    throw new IOException("No plugin.properties file found in " + file.getName());
                }


                Properties properties = new Properties();
                properties.load(input);
                //cek kalo ada pluginClass di plugin.properties
                String className = properties.getProperty("pluginClass");

                if (className == null) {
                    throw new IOException("No 'pluginClass' property found in " + file.getName());
                }

                Class<?> clazz = classLoader.loadClass(className);

                //Cek kalo semua class yang ditemukan ngeimplementasi MuatInterface
                if (MuatInterface.class.isAssignableFrom(clazz)) {
                    Constructor<?> constructor = clazz.getConstructor();
                    MuatInterface muatPlugin = (MuatInterface) constructor.newInstance();
                    muatPlugins.add(muatPlugin);
                }

                //Cek kalo semua class yang ditemukan ngeimplementasi SimpanInterface
                if (SimpanInterface.class.isAssignableFrom(clazz)) {
                    Constructor<?> constructor = clazz.getConstructor();
                    SimpanInterface simpanPlugin = (SimpanInterface) constructor.newInstance();
                    simpanPlugins.add(simpanPlugin);
                }

            }
        }
    }

    public List<MuatInterface> getMuatPlugins() {
        return muatPlugins;
    }

    public List<SimpanInterface> getSimpanPlugins() {
        return simpanPlugins;
    }
}
